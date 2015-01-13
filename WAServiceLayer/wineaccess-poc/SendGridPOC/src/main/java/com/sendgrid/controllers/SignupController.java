package com.sendgrid.controllers;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGrid.Email;
import com.sendgrid.forms.Signup;

//import com.github.scottmotte.Vcapenv;

@Controller
public class SignupController {

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("signup", new Signup());

		return "index";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String postSignup(@ModelAttribute Signup signup, Model model) {

		String username = "agarwal-gaurav";
		String password = "Globallogic10";

		SendGrid sendGrid = new SendGrid(username, password);

		Email email = new Email();
		email.addTo(signup.getEmail());
		email.setFrom("gaurav.agarwal1@globallogic.com+");
		email.setSubject("[WineAccess Sendgrid] Welcome");
		email.setHtml("<h3>Thanks for using the SendGrid.</h3>");
		try {
			email.addAttachment(new File("D:/WineAccessData/DB/Users.png"), "UsersDiagram");
			sendGrid.send(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	

		return "signup";
	}
}
