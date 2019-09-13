package com.soda.machine.api.config;

import java.util.List;
import java.util.TimeZone;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan("com.soda.machine")
public class SodaMachineSpringConfig implements WebMvcConfigurer {

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(SodaMachineConfig.getInstance().getProperty("database.url"));
		driverManagerDataSource.setUsername(SodaMachineConfig.getInstance().getProperty("database.user.name"));
		driverManagerDataSource.setPassword(SodaMachineConfig.getInstance().getProperty("database.password"));
		driverManagerDataSource.setDriverClassName(SodaMachineConfig.getInstance().getProperty("driver.class.name"));
		return driverManagerDataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(dataSource());
		return template;
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter() {
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		objectMapper.setTimeZone(TimeZone.getDefault());
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
		return mappingJackson2HttpMessageConverter;
	}

	@Bean
	public Docket sodaMachineApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.soda.machine.api")).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Soda Machine Api").version("1.0.0").build();
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET", "PUT", "POST", "DELETE");
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		WebMvcConfigurer.super.configureMessageConverters(converters);
		converters.add(mappingJacksonHttpMessageConverter());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}