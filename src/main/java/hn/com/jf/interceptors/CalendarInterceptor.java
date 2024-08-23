package hn.com.jf.interceptors;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CalendarInterceptor implements HandlerInterceptor {
	@Value("${config.calendar.open}")
	private Integer open;

	@Value("${config.calendar.close}")
	private Integer close;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		
		if (hour >= open && hour < close) {
			StringBuilder message = new StringBuilder("Bienvenidos al horario de atención al Cliente");
			message.append(", atendemos desde las ");
			message.append(open);
			message.append("hrs. hasta las");
			message.append(close);
			message.append("hrs.");
			message.append("Gracias por su visita!");
			
			request.setAttribute("message", message.toString());
			return true;
		}
		
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

}
