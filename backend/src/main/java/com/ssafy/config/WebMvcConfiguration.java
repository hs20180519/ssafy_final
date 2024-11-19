package com.ssafy.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@MapperScan(basePackages = {"com.ssafy.**.mapper"})
public class WebMvcConfiguration implements WebMvcConfigurer {

	//    @Autowired
	//    private AdminInterceptor adminInterceptor;

	//    @Override
	//    public void addInterceptors(InterceptorRegistry registry) {
	//        registry.addInterceptor(adminInterceptor).addPathPatterns("/admin/*");
	//    }

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/")
			.setViewName("index");
	}
	//
	//	@Override
	//	public void addCorsMappings(CorsRegistry registry) {
	//		registry.addMapping("**")
	//			.allowedOrigins("http://localhost:8080") // 클라이언트
	//			.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
	//			//			.allowedHeaders("*")
	//			.allowCredentials(true);
	//	}

}
