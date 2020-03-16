package com.jsmile.springsecurity.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController
{
	@GetMapping( "/" )
	public String showHome() 
	{
		return "home";
	}
}
