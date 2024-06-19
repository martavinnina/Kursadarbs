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
import lv.venta.model.Car;
import lv.venta.model.CarBrand;
import lv.venta.model.CarType;
import lv.venta.model.Client;
import lv.venta.service.ICarFilterService;

@Controller
@RequestMapping("/car/filter")
public class CarFilterController {

	@Autowired
	private ICarFilterService carService;
	
	@GetMapping("/all") // localhost:8080/car/filter/all
	public String getCarAll(Model model) {

		try {
			model.addAttribute("mydata", carService.retrieveAll());
			model.addAttribute("msg", "All cars");
			return "car-all-show-page";
		}
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
	
	//retrieveById
	@GetMapping("/one") // localhost:8080/car/filter/one?id=2
	public String selectCarById(@RequestParam("id") int id, Model model) {
		try
		{
			model.addAttribute("mydata", carService.retrieveById(id));
			return "car-one-show-page";
		}
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";// tiek parādīta error-page.html lapa
		}

	}
	
	//updateById
	@GetMapping("/update")//localhost:8080/car/filter/update?id=2
	public String getCarUpdateById(@RequestParam("id") int id, Model model) {
		try {
			Car updatedCar = carService.retrieveById(id);
			model.addAttribute("car", updatedCar);
			model.addAttribute("id", id);
			return "car-update-page";
			
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
	
	@PostMapping("/update")
	public String postCarUpdateById(@Valid Car car, 
			BindingResult result, @RequestParam("id") int id, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("id", id);
			return "car-update-page";
		}
		else
		{
			try
			{
				carService.updateById(id,car.getBrand(), car.getCarNumber(), 
						car.getType(), car.getModel(), car.getClient());
				
				return "redirect:/car/filter/all";
			}
			catch (Exception e) {
				model.addAttribute("errormsg", e.getMessage());
				return "error-page";
			}
		}
	}
	
	//create
	@GetMapping("/create") //localhost:8080/car/filter/create
	public String getCarInsert(Model model) {
		model.addAttribute("car", new Car());
		return "car-insert-page";
	}
	
	@PostMapping("/create")
	public String postClientInsert(@Valid Car car, BindingResult result) {
		if(result.hasErrors()) {
			return "car-insert-page";
		}
		else
		{
			try {
				carService.create(car.getBrand(), car.getCarNumber(), 
						car.getType(), car.getModel(), car.getClient());
				return "redirect:/car/filter/all";
			} catch (Exception e) {
			
				return "redirect:/error";
			}
		}
	}
	
	@GetMapping("/delete/{id}")//localhost:8080/car/filter/delete/2   var izdzest tikai tos kuri nav nekur piesaistiti
	public String getCarDeleteById(@PathVariable("id") int id, Model model) {
		
		try {
			carService.deleteById(id);
			model.addAttribute("mydata",carService.retrieveAll());
			model.addAttribute("msg", "All cars");
			return "car-all-show-page";
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
	
	//findByCarNumber
	@GetMapping("/number/{number}") //localhost:8080/car/filter/number/
	public String findByCarNo(@PathVariable("number") String carNumber, 
			Model model) {
		try {
			model.addAttribute("mydata", carService.findByCarNumber(carNumber));
			model.addAttribute("msg", "Cars filtered by number");
			return "car-all-show-page";
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
	
	//findByBrandAndType
	@GetMapping("/brandtype/{brand}/{type}") //localhost:8080/car/filter/brandtype/Audi/coupe
	public String findByCarBrandAndModel(@PathVariable CarBrand brand, @PathVariable CarType type, Model model) {
		try {
			model.addAttribute("mydata", carService.findByBrandAndType(brand, type));
			model.addAttribute("msg", "Cars filtered by brand and type");
			return "car-all-show-page";
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
}
