package it.uniroma3.siw.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.session.SessionData;

@Controller
public class MainController {
	
	@Autowired
	SessionData sessionData;
	
	@Autowired
	CredentialsService credentialsService;
	/*
		public MainController() {
		
		}
	*/
	
	/**
	 * This method is called when a GET request is sent by the user to URL "/home".
	 * This method prepares and dispatches the User registration view.
	 * 
	 * @param model the Request model
	 * @return the name of the target view, that in this case is "register"
	 */
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		//User loggedUser = sessionData.getLoggedUser();
		//model.addAttribute("user", loggedUser);
		if(credentialsService.getRoleAuthenticated() != null) {
			model.addAttribute("role", credentialsService.getRoleAuthenticated());
		}
		else {
			return "home.html";
		}
		return "home.html";
	}
	
	
	/**
	 * This method is called when a GET request is sent by the user to URL "/" or "/index".
	 * This method prepares and dispatches the index view.
	 * 
	 * @param model the Request model
	 * @return the name of the target view, that in this case is "index"
	 */
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {
		//DA INSERIRE UN CONTROLLO
		if(credentialsService.getRoleAuthenticated() != null) {
			model.addAttribute("role", credentialsService.getRoleAuthenticated());
		}
		else {
			return "index.html";
		}
		
		if(credentialsService.getRoleAuthenticated().equals("ADMIN")) {
			return "fragments/baseadmin";
		}
		return "index.html";
		
	}
	
	@RequestMapping(value = {"/chisiamo"}, method = RequestMethod.GET)
	public String chiSiamo(Model model) {
		if(credentialsService.getRoleAuthenticated() != null) {
			model.addAttribute("role", credentialsService.getRoleAuthenticated());
		}
		else {
			return "chisiamo";
		}
		return "chisiamo";
	}
	
	@RequestMapping(value = {"/contattaci"}, method = RequestMethod.GET)
	public String contattaci(Model model) {
		if(credentialsService.getRoleAuthenticated() != null) {
			model.addAttribute("role", credentialsService.getRoleAuthenticated());	
		}
		else {
			return "contattaci";
		}
		return "contattaci";
	}
	
	@RequestMapping(value = {"/admin/chisiamoadmin"}, method = RequestMethod.GET)
	public String chiSiamoAdmin(Model model) {
		if(credentialsService.getRoleAuthenticated() != null) {
			model.addAttribute("role", credentialsService.getRoleAuthenticated());
		}
		return "chisiamoadmin.html";
	}
	
	@RequestMapping(value = {"/admin/contattaciadmin"}, method = RequestMethod.GET)
	public String contattaciAdmin(Model model) {
		if(credentialsService.getRoleAuthenticated() != null) {
			model.addAttribute("role", credentialsService.getRoleAuthenticated());
		}
		return "contattaciadmin.html";
	}
	
}
