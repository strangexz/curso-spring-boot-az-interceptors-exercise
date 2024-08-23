package hn.com.jf.interceptors;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("calendarInterceptor")
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
		System.out.println("open = " + open);
		System.out.println("close = " + close);
		System.out.println("hour = " + hour);

		if (hour >= open && hour < close) {
			StringBuilder message = new StringBuilder("Bienvenidos al horario de atención al Cliente");
			message.append(", atendemos desde las ");
			message.append(open);
			message.append("hrs. hasta las ");
			message.append(close);
			message.append("hrs. Gracias por su visita!");

			request.setAttribute("message", message.toString());
			return true;
		}

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> json = new HashMap<>();
		StringBuilder message = new StringBuilder("Fuera del horario de atención! ");

		message.append("Visitenos desde las ");
		message.append(open);
		message.append("hrs. Hasta las ");
		message.append(close);
		message.append("hrs. Gracias por su visita!");

		json.put("date", new Date().toString());
		json.put("message", message.toString());

		response.setContentType("application/json");
		response.setStatus(401);
		response.getWriter().write(mapper.writeValueAsString(json));

		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

}
