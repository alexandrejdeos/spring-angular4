package br.com.ajsantos.web.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ajsantos
 * @date 2017-06-07
 */

@RestController
@RequestMapping(value = "/service", headers = "Accept=application/json")
public class OrionRestController {
	
	@GetMapping(value =  { "/teste" })
	public @ResponseBody ResponseEntity<String> teste (@RequestParam(value = "login") String login) {
		return ResponseEntity.ok().body("Funcionou! " + login);

	}
}
