package com.deptempgateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {
	
	@RequestMapping("/deartmentfallback")
	public String deptFallBack()
	{
		return "Its taking too much time to respond or down !please try again later";
		
	}

}
