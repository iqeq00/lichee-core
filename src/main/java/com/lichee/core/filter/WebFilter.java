package com.lichee.core.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 * web filter
 * 
 * @author lichee
 */
public class WebFilter extends ConfigurableSiteMeshFilter {

	@Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		
		builder.addExcludedPath("/common/*");
		builder.addExcludedPath("/manager/login");
		builder.addDecoratorPath("/manager/*","/WEB-INF/layouts/manager/manager.jsp");
		builder.addExcludedPath("/customer/login/*");
		builder.addExcludedPath("/customer/register/*");
		builder.addExcludedPath("/customer/commentInfo/comment");
		builder.addExcludedPath("/customer/order/detail");
		builder.addExcludedPath("/customer/order/refund");
		builder.addExcludedPath("/customer/order/refundView");
		builder.addDecoratorPath("/customer/*","/WEB-INF/layouts/customer/customer.jsp");
    }

}
