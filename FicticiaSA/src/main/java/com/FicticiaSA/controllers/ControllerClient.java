package com.FicticiaSA.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.FicticiaSA.entities.Client;
import com.FicticiaSA.exceptions.ServiceException;
import com.FicticiaSA.services.ServiceClient;

@Controller
@RequestMapping("/client")
public class ControllerClient {

	@Autowired
	private ServiceClient serviceClient;
	

	@GetMapping("/register")
	public String register() {
		return "registerClient";
	}
	
	@PostMapping("/newClient")
	public String newClient(@RequestParam String email, @RequestParam String password, @RequestParam String fullName, 
							@RequestParam Long dni, @RequestParam String address,
							@RequestParam String birthday, @RequestParam Long phone,
							@RequestParam Boolean driver, @RequestParam Boolean glasses,
							@RequestParam Boolean diabetic, @RequestParam(required = false) String typeDiabetes, 
							@RequestParam Boolean diseases, @RequestParam(required = false) String whatDiseases, RedirectAttributes redirAttrs) throws ServiceException{
		
		try {
			serviceClient.registrationClient(email, password, fullName, dni, address, birthday, phone, driver, glasses, diabetic, typeDiabetes, diseases, whatDiseases);
			return "redirect:/login";
		} catch (ServiceException e) {
			e.printStackTrace();
			return "registerClient";
		}		
		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_CLIENT')")
	@GetMapping("/profileCli/{id}")
	public String profileClient (@PathVariable("id") String id, ModelMap modelo)	{
		try {
			Client c = serviceClient.obtenerPorId(id);
			modelo.addAttribute("client", c);	
			return "profileClient";
		} catch (ServiceException e) {
			e.printStackTrace();
			return "index";
		}
	}
	
	@PreAuthorize("hasAnyRole('ROLE_CLIENT')")
	@GetMapping("/editProfile/{id}")
	public String editProfile(ModelMap modelo, @PathVariable("id") String id) {
		try {
			Client c = serviceClient.obtenerPorId(id);
			modelo.addAttribute("client", c);
			return "updateProfileClient";
		} catch (ServiceException e) {
			e.printStackTrace();
			return "redirect:/";
		}
		
	}
		
	@PreAuthorize("hasAnyRole('ROLE_CLIENT')")
	@PostMapping("/updateProfile/{id}")
	public String updateProfile(@PathVariable("id") String id, @RequestParam String fullName, 
			@RequestParam Long dni, @RequestParam String address,
			@RequestParam String birthday, @RequestParam Long phone,
			@RequestParam Boolean driver, @RequestParam Boolean glasses,
			@RequestParam Boolean diabetic, @RequestParam(required = false) String typeDiabetes, 
			@RequestParam Boolean diseases, @RequestParam(required = false) String whatDiseases) {
		try {			
			serviceClient.modifyClient(id, fullName, dni, address, birthday, phone, driver, glasses, diabetic, typeDiabetes, diseases, whatDiseases);
			return "redirect:/client/profileCli/".concat(id);
		} catch (ServiceException e) {
			e.printStackTrace();
			return "redirect:/";
		} 
		
		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_CLIENT')")
	@GetMapping("setActive/{id}")
	public String setActive(@PathVariable("id") String id) {
		serviceClient.setActive(id);
		return "redirect:/client/profileCli/".concat(id);
		
	}
	
}
