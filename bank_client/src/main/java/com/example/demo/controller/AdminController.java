package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path = "/api/v1/admin")
public class AdminController {

	@Autowired
	private RestTemplate template;
	
	
	@GetMapping(path = "/alldetails")
	public String getAllClientDetails() {
		return this.template.getForObject("http://BANK-ADMIN/api/v1/admin",String.class);
	}
	@GetMapping(path = "/accountno/{id}")
	public String getDetailsByAccNo(@PathVariable("id") long id) {
		return this.template.getForObject("http://BANK-ADMIN/api/v1/admin/"+id,String.class);
	}
	@GetMapping(path = "/balance/{id}")
	public String getBalance(@PathVariable("id") long id) {
		return this.template.getForObject("http://BANK-ADMIN/api/v1/admin/balance/"+id,String.class);
	}
	@GetMapping(path = "/allmobilereq")
	public String getAllMobileReq() {
		return this.template.getForObject("http://BANK-ADMIN/api/v1/admin/mobilereq/view",String.class);
	}
	
}
