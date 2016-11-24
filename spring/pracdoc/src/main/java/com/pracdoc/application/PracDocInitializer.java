package com.pracdoc.application;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.pracdoc.configuration.HibernetConfig;
import com.pracdoc.configuration.SimpleCORSFilter;

//comment below class if you want to create jar file and want to test on tomcat
//	 server running on terminal

//uncomment the below text while creating war file
@ComponentScan(basePackages = { "com.pracdoc" })
public class PracDocInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext container) throws ServletException {

		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		// ctx.register(Application.class);
		// ctx.setConfigLocation("eu.kielczewski.example.config");
		ctx.register(HibernetConfig.class);
		ctx.setServletContext(container);
		// ctx.setServletContext(servletContext);

		// Manage the lifecycle of the root application context
		container.addListener(new ContextLoaderListener(ctx));

		ServletRegistration.Dynamic servlet = container.addServlet(
				"dispatcher", new DispatcherServlet(ctx));

		servlet.setLoadOnStartup(0);
		servlet.addMapping("/");

		FilterRegistration.Dynamic fr1 = container.addFilter("corsFilter",
				new SimpleCORSFilter());
		 fr1.setInitParameter("encoding", "UTF-8");
		 fr1.setInitParameter("forceEncoding", "true");
		 fr1.addMappingForUrlPatterns(null, false, "/*");

	}

}
