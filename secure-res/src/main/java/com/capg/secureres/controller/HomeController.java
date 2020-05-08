package com.capg.secureres.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	// REST endpoint (Not Secured)
	@GetMapping("/public")
	public String home() {
		return "Test Data";
	}
	
	// REST endpoint (Manager)
	@RolesAllowed({"ROLE_MANAGER"})
	@GetMapping("/api/manager-data")
	public String managerHome() {
		return "Test Data - MANAGER";
	}
	
	// REST endpoint (Admin)
	@RolesAllowed({"ROLE_ADMIN"})
	@GetMapping("/api/admin-data")
	public String adminHome() {
		return "Test Data - ADMIN";
	}
}
