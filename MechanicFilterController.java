package lv.venta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lv.venta.model.Mechanic;
import lv.venta.service.IMechanicFilterService;

@Controller
@RequestMapping("/mechanic")
public class MechanicFilterController {
	@Autowired
	private IMechanicFilterService mechanicService;
	
	@GetMapping("/create") //localhost:8080/mechanic/create
	public String getMechanicInsert(Model model) {
		model.addAttribute("mechanic", new Mechanic());
		return "mechanic-insert-page";
	}
	
	@PostMapping("/create")
	public String postMechanicInsert(@Valid Mechanic mechanic, BindingResult result) {
		if(result.hasErrors()) {
			return "mechanic-insert-page";
		}
		else
		{
			try {
				mechanicService.create(mechanic.getName(), mechanic.getSurname(), 
						mechanic.getPhoneNo(), mechanic.getExperience(), mechanic.getLicenceNo());
				return "redirect:/mechanic/show/all";
			} catch (Exception e) {
			
				return "redirect:/error";
			}
		}
	}
	
	@GetMapping("/error")//localhost:8080/error
	public String getError() {
		return "error-page";
	}
	
	@GetMapping("/show/all") // localhost:8080/mechanic/show/all
	public String getMechanicAll(Model model) {

		try {
			model.addAttribute("mydata", mechanicService.retrieveAll());
			model.addAttribute("msg", "All mechanics");
			return "mechanic-all-show-page";
		}
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/update")//localhost:8080/mechanic/update?id=2
	public String getMechanicUpdateById(@RequestParam("id") int id, Model model) {
		try {
			Mechanic updatedMechanic = mechanicService.retrieveById(id);
			model.addAttribute("mechanic", updatedMechanic);
			model.addAttribute("id", id);
			return "mechanic-update-page";
			
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
	
	@PostMapping("/update")
	public String postMechanicUpdateById(@Valid Mechanic mechanic, 
			BindingResult result, @RequestParam("id") int id, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("id", id);
			return "mechanic-update-page";
		}
		else
		{
			try
			{
				mechanicService.updateById(id,mechanic.getName(), mechanic.getSurname(), 
						mechanic.getPhoneNo(), mechanic.getExperience(), mechanic.getLicenceNo());
				
				return "redirect:/mechanic/show/all";
			}
			catch (Exception e) {
				model.addAttribute("errormsg", e.getMessage());
				return "error-page";
			}
		}
	}
	
	
	@GetMapping("/delete/{id}")//localhost:8080/mechanic/delete/2   var izdzest tikai tos kuri nav nekur piesaistiti
	public String getMechanicDeleteById(@PathVariable("id") int id, Model model) {
		
		try {
			mechanicService.deleteById(id);
			model.addAttribute("mydata",mechanicService.retrieveAll());
			model.addAttribute("msg", "All mechanics");
			return "mechanic-all-show-page";
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/licenceNo/{licenceNo}") //localhost:8080/mechanic/licenceNo/M123456
	public String findByLicenceNo(@PathVariable("licenceNo") String licenceNo, 
			Model model) {
		try {
			model.addAttribute("mydata", mechanicService.findByLicenceNo(licenceNo));
			model.addAttribute("msg", "Mechanic filtered by licence number");
			return "mechanic-all-show-page";
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/expLessThan/{value}") //localhost:8080/mechanic/expLessThan/7.1
	public String findByExperienceInYearsLessThan(@PathVariable("value") float value, 
			Model model) {
		try {
			model.addAttribute("mydata", mechanicService.findByExperienceInYearsLessThan(value));
			model.addAttribute("msg", "Mechanic filtered by experience less than treshold");
			return "mechanic-all-show-page";
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/expGreaterThan/{value}") //localhost:8080/mechanic/expGreaterThan/7.1
	public String findByExperienceInYearsGreaterThan(@PathVariable("value") float value, 
			Model model) {
		try {
			model.addAttribute("mydata", mechanicService.findByExperienceInYearsGreaterThan(value));
			model.addAttribute("msg", "Mechanic filtered by experience greater than treshold");
			return "mechanic-all-show-page";
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
}