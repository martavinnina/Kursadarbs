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
import lv.venta.model.LightType;
import lv.venta.model.LightUseType;
import lv.venta.model.Lights;
import lv.venta.model.Tire;
import lv.venta.model.TireType;
import lv.venta.service.ILightFilterService;

@Controller
@RequestMapping("/lights")
public class LightsFilterController {
	
	@Autowired
	private ILightFilterService lightService;
	
	@GetMapping("/filter/all")//localhost:8080/lights/filter/all
	public String getTireAll(Model model) {
		try {
			model.addAttribute("mydata", lightService.retrieveAll());
			model.addAttribute("msg", "All lights");
			return "lights-all-show-page";
		}
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/filter/price/{threshold}")//localhost:8080/lights/filter/price/150
	public String getTiresFilterByPrice(@PathVariable("threshold") float threshold, Model model) {
		
		try {
			//ArrayList<Tire> filterTires = tireService.selectAllTiresPriceLessThan(threshold);
			model.addAttribute("mydata", lightService.selectAllLightsPriceLessThan(threshold));
			model.addAttribute("msg", "Lights filtered by price less than: " + threshold + " EUR");
			return "lights-all-show-page";
		}
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
		
	}
	
	@GetMapping("/filter/manufacturer/{manufacturer}")//localhost:8080/lights/filter/manufacturer/BMW
	public String getTiresFilterByManufacturer(@PathVariable("manufacturer") String manufacturer, Model model) {
		
		try {
			model.addAttribute("mydata", lightService.selectAllLightsByManufacturer(manufacturer));
			model.addAttribute("msg", "Lights filtered by Manufacturer: " + manufacturer);
			return "lights-all-show-page";
		}
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
		
	}
	
	@GetMapping("/update")//localhost:8080/lights/update?id=6    !!! Lights ID ir no Parts tāpēc būs pēc Tire objektiem, tāpēc šī funkcija nestrādās ar id=1
	public String getLightsUpdateById(@RequestParam("id") int id, Model model) {
		
		try {
			Lights updateLights = lightService.retrieveById(id);
			model.addAttribute("lights", updateLights);
			model.addAttribute("id", id);
			return "lights-update-page";
		}
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
		
	}
	
	@PostMapping("/update")
	public String postLightsUpdateById(@Valid Lights lights, BindingResult result, @RequestParam("id") int id, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("id", id);
			return "lights-update-page";
		}
		else
		{
			try
			{
				lightService.updateById(id, lights.getPrice(), lights.getManufacturer(), lights.getLightType(), lights.getLightUseType(), lights.getLightColorKelvin(), lights.getPowerWatt());
				return "redirect:/lights/filter/all";
			}
			catch (Exception e) {
				model.addAttribute("errormsg", e.getMessage());
				return "error-page";
			}
		}
	}
	
	@GetMapping("/create") //localhost:8080/lights/create
	public String getLightsInsert(Model model) {
		model.addAttribute("lights", new Lights());
		return "lights-insert-page";
	}
	
	@PostMapping("/create")
	public String postTireInsert(@Valid Lights lights, BindingResult result) {
		if(result.hasErrors()) {
			return "lights-insert-page";
		}
		else
		{
			try {
				lightService.createNewLights(lights.getPrice(), lights.getManufacturer(), lights.getLightType(), lights.getLightUseType(), lights.getLightColorKelvin(), lights.getPowerWatt());
				return "redirect:/lights/filter/all";
			} catch (Exception e) {
			
				return "redirect:/error";
			}
		}
	}
	
	@GetMapping("/delete/{id}")//localhost:8080/lights/delete/7
	public String getLightsDeleteById(@PathVariable("id") int id, Model model) {
		
		try {
			lightService.deleteById(id);
			model.addAttribute("mydata",lightService.retrieveAll());
			model.addAttribute("msg", "All lights");
			return "lights-all-show-page";
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/filter/lighttype/{lighttype}")//localhost:8080/lights/filter/lighttype/LED
	public String getLightsFilterByLightType(@PathVariable("lighttype") LightType lightType, Model model) {
		
		try {
			model.addAttribute("mydata", lightService.selectAllLightsByLightType(lightType));
			model.addAttribute("msg", "Lights filtered by light type: " + lightType);
			return "lights-all-show-page";
		}
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
		
	}
	
	@GetMapping("/filter/lightusetype/{lightusetype}")//localhost:8080/lights/filter/lightusetype/Headlights
	public String getLightsFilterByLightUseType(@PathVariable("lightusetype") LightUseType lightUseType, Model model) {
		
		try {
			model.addAttribute("mydata", lightService.selectAllLightsByLightUseType(lightUseType));
			model.addAttribute("msg", "Lights filtered by light use type: " + lightUseType);
			return "lights-all-show-page";
		}
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
		
	}
	
	

}