package lv.venta.controller;

import java.util.ArrayList;

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
import lv.venta.model.Tire;
import lv.venta.model.TireSize;
import lv.venta.model.TireType;
import lv.venta.service.ITireFilterService;

@Controller
@RequestMapping("/tire")
public class TireFilterController {

	@Autowired
	private ITireFilterService tireService;
	
	@GetMapping("/filter/all")//localhost:8080/tire/filter/all
	public String getTireAll(Model model) {
		try {
			model.addAttribute("mydata", tireService.retrieveAll());
			model.addAttribute("msg", "All tires");
			return "tire-all-show-page";
		}
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/filter/price/{threshold}")//localhost:8080/tire/filter/price/150
	public String getTiresFilterByPrice(@PathVariable("threshold") float threshold, Model model) {
		
		try {
			//ArrayList<Tire> filterTires = tireService.selectAllTiresPriceLessThan(threshold);
			model.addAttribute("mydata", tireService.selectAllTiresPriceLessThan(threshold));
			model.addAttribute("msg", "Tires filtered by price less than: " + threshold + " EUR");
			return "tire-all-show-page";
		}
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
		
	}
	
	@GetMapping("/filter/manufacturer/{manufacturer}")//localhost:8080/tire/filter/manufacturer/Michellin
	public String getTiresFilterByManufacturer(@PathVariable("manufacturer") String manufacturer, Model model) {
		
		try {
			model.addAttribute("mydata", tireService.selectAllTiresByManufacturer(manufacturer));
			model.addAttribute("msg", "Tires filtered by Manufacturer: " + manufacturer);
			return "tire-all-show-page";
		}
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
		
	}
	
	@GetMapping("/update")//localhost:8080/tire/update?id=1
	public String getTireUpdateById(@RequestParam("id") int id, Model model) {
		
		try {
			Tire updateTire = tireService.retrieveById(id);
			model.addAttribute("tire", updateTire);
			model.addAttribute("id", id);
			return "tire-update-page";
		}
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
		
	}
	
	@PostMapping("/update")
	public String postTireUpdateById(@Valid Tire tire, BindingResult result, @RequestParam("id") int id, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("id", id);
			return "tire-update-page";
		}
		else
		{
			try
			{
				tireService.updateById(id, tire.getPrice(), tire.getManufacturer(), tire.getTireSize(), tire.getTireType(), tire.getLoudnessDb(), tire.getDrivingInRain(), tire.getFuelEfficiency());
				return "redirect:/tire/filter/all";
			}
			catch (Exception e) {
				model.addAttribute("errormsg", e.getMessage());
				return "error-page";
			}
		}
	}
	
	@GetMapping("/create") //localhost:8080/tire/create
	public String getTireInsert(Model model) {
		model.addAttribute("tire", new Tire());
		return "tire-insert-page";
	}
	
	@PostMapping("/create")
	public String postTireInsert(@Valid Tire tire, BindingResult result) {
		if(result.hasErrors()) {
			return "tire-insert-page";
		}
		else
		{
			try {
				tireService.createNewTire(tire.getPrice(), tire.getManufacturer(), tire.getTireSize(), tire.getTireType(), tire.getLoudnessDb(), tire.getDrivingInRain(), tire.getFuelEfficiency());
				return "redirect:/tire/filter/all";
			} catch (Exception e) {
			
				return "redirect:/error";
			}
		}
	}
	
	@GetMapping("/delete/{id}")//localhost:8080/tire/delete/2
	public String getTireDeleteById(@PathVariable("id") int id, Model model) {
		
		try {
			tireService.deleteById(id);
			model.addAttribute("mydata",tireService.retrieveAll());
			model.addAttribute("msg", "All tires");
			return "tire-all-show-page";
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/filter/tiretype/{tiretype}")//localhost:8080/tire/filter/tiretype/SUMMER
	public String getTiresFilterByTireType(@PathVariable("tiretype") TireType tireType, Model model) {
		
		try {
			model.addAttribute("mydata", tireService.selectAllTiresByTireType(tireType));
			model.addAttribute("msg", "Tires filtered by tire type: " + tireType);
			return "tire-all-show-page";
		}
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
		
	}
	
	@GetMapping("/filter/tiresize/{tiresize}")//localhost:8080/tire/filter/tiresize/SIZE_275_55
	public String getTiresFilterByTireSize(@PathVariable("tiresize") TireSize tireSize, Model model) {
		
		try {
			model.addAttribute("mydata", tireService.selectAllTiresByTireSize(tireSize));
			model.addAttribute("msg", "Tires filtered by tire size: " + tireSize);
			return "tire-all-show-page";
		}
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
		
	}
	
	
}
