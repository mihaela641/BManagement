package bg.infosys.interns.bmanagement.ws.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bg.infosys.interns.bmanagement.ws.config.ControllerConfig;

@RestController
@RequestMapping(ControllerConfig.SAMPLE_URL)
public class SampleController {
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public String getSampleMessage() {
		return "Hello, Business interns!";
	}
}
