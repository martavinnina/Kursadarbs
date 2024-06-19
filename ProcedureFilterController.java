package lv.venta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lv.venta.model.Procedure;
import lv.venta.service.IProcedureService;

@Controller
@RequestMapping("/procedure/filter")
public class ProcedureFilterController {

	@Autowired
	private IProcedureService procedureService;
	
	@GetMapping("/all") // localhost:8080/procedure/filter/all
	public String getProcedureAll(Model model) {

		try {
			model.addAttribute("mydata", procedureService.retrieveAll());
			model.addAttribute("msg", "All procedures");
			return "procedure-all-show-page";
		}
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
	
	
		@GetMapping("/one") // localhost:8080/procedure/filter/one?id=2
		public String selectProcedureById(@RequestParam("id") int id, Model model) {
			try
			{
				model.addAttribute("mydata", procedureService.retrieveById(id));
				return "procedure-one-show-page";
			}
			catch (Exception e) {
				model.addAttribute("errormsg", e.getMessage());
				return "error-page";
			}

		}
		
		@GetMapping("/update")//localhost:8080/procedure/filter/update?id=2
		public String getProcedureUpdateById(@RequestParam("id") int id, Model model) {
			try {
				Procedure updatedProcedure = procedureService.retrieveById(id);
				model.addAttribute("procedure", updatedProcedure);
				model.addAttribute("id", id);
				return "procedure-update-page";
				
			} catch (Exception e) {
				model.addAttribute("errormsg", e.getMessage());
				return "error-page";
			}
		}
		
		@PostMapping("/update")
		public String postProcedureUpdateById(@Valid Procedure procedure, 
				BindingResult result, @RequestParam("id") int id, Model model) {
			if(result.hasErrors()) {
				model.addAttribute("id", id);
				return "procedure-update-page";
			}
			else
			{
				try
				{
					procedureService.updateById(id, procedure.getMechanic(), procedure.getCar()
							, procedure.getPart(), procedure.getPlannedReturn());
					
					return "redirect:/procedure/filter/all";
				}
				catch (Exception e) {
					model.addAttribute("errormsg", e.getMessage());
					return "error-page";
				}
			}
		}
	
		//create
		@GetMapping("/create") //localhost:8080/procedure/filter/create
		public String getProcedureInsert(Model model) {
			model.addAttribute("procedure", new Procedure());
			return "procedure-insert-page";
		}
		
		@PostMapping("/create")
		public String postProcedureInsert(@Valid Procedure procedure, BindingResult result) {
			if(result.hasErrors()) {
				return "procedure-insert-page";
			}
			else
			{
				try {
					procedureService.create(procedure.getMechanic(), procedure.getCar()
							, procedure.getPart(), procedure.getPlannedReturn());
					return "redirect:/procedure/filter/all";
				} catch (Exception e) {
				
					return "redirect:/error";
				}
			}
		}
		
		@GetMapping("/delete/{id}")//localhost:8080/procedure/filter/delete/2   
		public String getProcedureDeleteById(@PathVariable("id") int id, Model model) {
			
			try {
				procedureService.deleteById(id);
				model.addAttribute("mydata",procedureService.retrieveAll());
				model.addAttribute("msg", "All cars");
				return "procedure-all-show-page";
			} catch (Exception e) {
				model.addAttribute("errormsg", e.getMessage());
				return "error-page";
			}
		}
		
		//selectAllProcedureByMechanicId
		
		@GetMapping("/mechanic/{id}") // localhost:8080/procedure/filter/mechanic/1
		public String getProcedureByMechanicAll(@PathVariable("id") int id, Model model) {

			try {
				model.addAttribute("mydata", procedureService.selectAllProcedureByMechanicId(id));
				model.addAttribute("msg", "All procedures by mechanic id");
				return "procedure-all-show-page";
			}
			catch (Exception e) {
				model.addAttribute("errormsg", e.getMessage());
				return "error-page";
			}
		}
		
		//selectAllProcedureByCarId
		
				@GetMapping("/car/{id}") // localhost:8080/procedure/filter/car/1
				public String getProcedureByCarAll(@PathVariable("id") int id, Model model) {

					try {
						model.addAttribute("mydata", procedureService.selectAllProcedureByCarId(id));
						model.addAttribute("msg", "All procedures by car id");
						return "procedure-all-show-page";
					}
					catch (Exception e) {
						model.addAttribute("errormsg", e.getMessage());
						return "error-page";
					}
				}
}
