package com.lichee.core.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

/**
 * @author lichee 
 */
@Aspect
@Service
public class ValidatorAop {

	@Autowired
	private Validator validator;

	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	private void controllerValid() {
	}

	@Around("controllerValid()")
	public Object aroundExec(ProceedingJoinPoint pjp) throws Throwable {

		MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
		Method method = methodSignature.getMethod();
		Annotation[][] argAnnotations = method.getParameterAnnotations();
		String[] argNames = methodSignature.getParameterNames();
		Object[] args = pjp.getArgs();
		HttpServletRequest request = getHttpServletRequest(args);
		for (int i = 0; i < args.length; i++) {
			if (hasValidAnnotations(argAnnotations[i])) {
				String ret = validateArg(args[i], argNames[i], request);
				if (ret != null) {
					return ret;
				}
			}
		}
		return pjp.proceed(args);
	}

	private HttpServletRequest getHttpServletRequest(Object[] args) {
		
		for (int i = 0; i < args.length; i++) {
			if (HttpServletRequest.class.isInstance(args[i])) {
				return (HttpServletRequest) args[i];
			}
		}
		return null;
	}

	private boolean hasValidAnnotations(Annotation[] annotations) {

		if (annotations.length < 1) {
			return false;
		}
		for (Annotation annotation : annotations) {
			if (Valid.class.isInstance(annotation)) {
				return true;
			}
		}
		return false;
	}

	
	private String validateArg(Object arg, String argName,
			HttpServletRequest request) {
		
		BindingResult result = getBindingResult(arg, argName);
		validator.validate(arg, result);
		if (result.hasErrors()) {
			request.setAttribute("entity", argName);
			return "manager/error/error";
		}
		return null;
	}

	private BindingResult getBindingResult(Object target, String targetName) {
		
		return new BeanPropertyBindingResult(target, targetName);
	}

}