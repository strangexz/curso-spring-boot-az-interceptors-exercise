package hn.com.jf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySources({ @PropertySource("classpath:config.properties") })
public class Config implements WebMvcConfigurer {
	@Autowired
	@Qualifier("calendarInterceptor")
	private HandlerInterceptor calendar;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(calendar).addPathPatterns("/**");
	}

}
