package com.lichee.core.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 * manager filter
 * 
 * @author lichee
 */
public class CdzwFilter extends ConfigurableSiteMeshFilter {

	@Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		
		builder.addExcludedPath("/common/*");
		builder.addExcludedPath("/mobile/*");
		builder.addExcludedPath("/manager/login");
		builder.addDecoratorPath("/manager/*","/WEB-INF/layouts/manager/manager.jsp");
    }

}
