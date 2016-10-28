package com.pracdoc.application;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.pracdoc.configuration.CoreConfig;

//comment below class if you want to create jar file and want to test on tomcat
//	 server running on terminal

//uncomment the below text while creating war file
public class PracDocInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext container) throws ServletException {

		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		// ctx.register(Application.class);
		ctx.register(CoreConfig.class);
		ctx.setServletContext(container);

		ServletRegistration.Dynamic servlet = container.addServlet(
				"dispatcher", new DispatcherServlet(ctx));

		servlet.setLoadOnStartup(0);
		servlet.addMapping("/");

		// un-comment it for IOnic framework
		//
		// FilterRegistration.Dynamic fr1 = container.addFilter("corsFilter",
		// new SimpleCORSFilter());
		// fr1.setInitParameter("encoding", "UTF-8");
		// fr1.setInitParameter("forceEncoding", "true");
		// fr1.addMappingForUrlPatterns(null, false, "/api/client/login");
		// fr1.addMappingForUrlPatterns(null, false, "/api/admin/login");
		// fr1.addMappingForUrlPatterns(null, false, "/*");

	}

}
