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
import lv.venta.model.Client;
import lv.venta.service.IClientFilterService;

@Controller
@RequestMapping("/client/filter")
public class ClientFilterController {
	@Autowired
	private IClientFilterService clientService;
	
	/*@GetMapping("/idcar/{param}") //localhost:8080/client/filter/idcar/2
	public String findByCarIdCar(@PathVariable("param") int id, 
			Model model) {
		try {
			model.addAttribute("mydata", clientService.findByCarIdCar(id));
			model.addAttribute("msg", "Clients filtered by car ID's");
			return "client-all-show-page";
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}*/
	
	@GetMapping("/phone/{phone}") //localhost:8080/client/filter/phone/24444444
	public String findByClientPhoneNo(@PathVariable("phone") String phoneNo, 
			Model model) {
		try {
			model.addAttribute("mydata", clientService.findByPhoneNo(phoneNo));
			model.addAttribute("msg", "Clients filtered by phone number");
			return "client-all-show-page";
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/name&surname/{name}/{surname}") //localhost:8080/client/filter/name&surname/Janis/Berzins
	public String findByClientPhoneNo(@PathVariable String name, @PathVariable String surname, Model model) {
		try {
			model.addAttribute("mydata", clientService.findByClientNameAndSurname(name, surname));
			model.addAttribute("msg", "Clients filtered by name and surname");
			return "client-all-show-page";
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/create") //localhost:8080/client/filter/create
	public String getClientInsert(Model model) {
		model.addAttribute("client", new Client());
		return "client-insert-page";
	}
	
	@PostMapping("/create")
	public String postClientInsert(@Valid Client client, BindingResult result) {
		if(result.hasErrors()) {
			return "client-insert-page";
		}
		else
		{
			try {
				clientService.create(client.getName(), client.getSurname(), 
						client.getPhoneNo());
				return "redirect:/client/filter/all";
			} catch (Exception e) {
			
				return "redirect:/error";
			}
		}
	}
	
	@GetMapping("/error")//localhost:8080/error
	public String getError() {
		return "error-page";
	}
	
	@GetMapping("/all") // localhost:8080/client/filter/all
	public String getClientAll(Model model) {

		try {
			model.addAttribute("mydata", clientService.retrieveAll());
			model.addAttribute("msg", "All clients");
			return "client-all-show-page";
		}
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/update")//localhost:8080/client/filter/update?id=2
	public String getClientUpdateById(@RequestParam("id") int id, Model model) {
		try {
			Client updatedClient = clientService.retrieveById(id);
			model.addAttribute("client", updatedClient);
			model.addAttribute("id", id);
			return "client-update-page";
			
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
	
	@PostMapping("/update")
	public String postClientUpdateById(@Valid Client client, 
			BindingResult result, @RequestParam("id") int id, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("id", id);
			return "client-update-page";
		}
		else
		{
			try
			{
				clientService.updateById(id,client.getName(), client.getSurname(), 
						client.getPhoneNo());
				
				return "redirect:/client/filter/all";
			}
			catch (Exception e) {
				model.addAttribute("errormsg", e.getMessage());
				return "error-page";
			}
		}
	}
	
	
	@GetMapping("/delete/{id}")//localhost:8080/client/filter/delete/2   var izdzest tikai tos kuri nav nekur piesaistiti
	public String getClientDeleteById(@PathVariable("id") int id, Model model) {
		
		try {
			clientService.deleteById(id);
			model.addAttribute("mydata",clientService.retrieveAll());
			model.addAttribute("msg", "All clients");
			return "client-all-show-page";
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
}