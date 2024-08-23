package hn.com.jf.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

	@GetMapping("/foo")
	public ResponseEntity<?> foo() {
		Map<String, Object> data = new HashMap<>();
		data.put("title", "Bienvenido al Sistema de Atención!");
		data.put("date", new Date());

		return ResponseEntity.ok(data);
	}
}
