package jp.co.sample.controller;

import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministratorController {
	
	@Autowired
	private AdministratorService administratorService;
	
	@ModelAttribute
	InsertAdministratorForm setUpAdministratorForm() {
		return new InsertAdministratorForm();
	}
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form,RedirectAttributes redirectAttributes) {
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		
		AdministratorService administratorService = new AdministratorService();
		administrator=administratorService.insert(administrator);
		redirectAttributes.addFlashAttribute("administrator",administrator);
		return "/";
	}
	
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return setUpLoginForm();
	}
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login.html";
	}
	
	@RequestMapping("/login")
		public String login(LoginForm form,Model model) {
		model.addAllAttributes("administratorName",administratorName);
		return "foword:/employee/showList";
	}

}
