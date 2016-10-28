package com.pracdoc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.pracdoc.models.DrManagementModelForPatient;
import com.pracdoc.models.UserManagementModel;

@Configuration
public class CoreConfig {
	@Bean
	public JdbcTemplate dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/prac_doc_db");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public UserManagementModel getUserManagementModel() {
		return new UserManagementModel();
	}

	@Bean
	public DrManagementModelForPatient getDrManagementModelForPatient() {
		return new DrManagementModelForPatient();
	}

}
