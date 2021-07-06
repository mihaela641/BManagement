package bg.infosys.interns.bmanagement.ws.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.infosys.interns.bmanagement.ws.config.ControllerConfig;
import bg.infosys.interns.bmanagement.ws.dto.CountryDTO;
import bg.infosys.interns.bmanagement.ws.service.EnumService;

@RestController
@RequestMapping(ControllerConfig.ENUM_URL)
public class EnumController {

	private EnumService service;
	
	public EnumController(EnumService service) {
		this.service = service;
	}
	
	@GetMapping("/countries")
	public List<CountryDTO>getAll(){
		return this.service.getAllCountries();
	}
}
