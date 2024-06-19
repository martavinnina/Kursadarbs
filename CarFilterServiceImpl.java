package lv.venta.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import lv.venta.model.Car;
import lv.venta.model.CarBrand;
import lv.venta.model.CarType;
import lv.venta.model.Client;
import lv.venta.model.Mechanic;
import lv.venta.repo.ICarRepo;
import lv.venta.service.ICarFilterService;

@Service
public class CarFilterServiceImpl implements ICarFilterService{

	@Autowired
	private ICarRepo carRepo;

	@Override
	public ArrayList<Car> retrieveAll() throws Exception {
		if (carRepo.count() == 0)
			throw new Exception("Car list is empty");
		return (ArrayList<Car>) carRepo.findAll();
	}
	

	@Override
	public Car retrieveById(int idCar) throws Exception {
		if(idCar <= 0) throw new Exception("id should be positive");
		
		Car result = carRepo.findById(idCar);
		return result;
	}

	@Override
	public void updateById(int idCar, CarBrand brand, String carNumber, CarType type, String model, Client client) throws Exception {
		if(idCar < 0 || brand == null || carNumber == null || type == null || model == null || client == null)		
			throw new Exception("Wrong input parameters");
		
		Car updateCar = carRepo.findById(idCar);
		if(brand != null)
			updateCar.setBrand(brand);
		if(carNumber != null)
			updateCar.setCarNumber(carNumber);
		if(type != null)
			updateCar.setType(type);
		if(model != null)
			updateCar.setModel(model);
		if(client != null)
			updateCar.setClient(client);
		
	
		carRepo.save(updateCar);
	}

	@Override
	public void create(CarBrand brand, String carNumber, CarType type, String model, Client client)
			throws Exception {
		if(brand == null || carNumber == null || type == null || model == null || client == null)		
			throw new Exception("Wrong input parameters");
		
		for (Car tempC : carRepo.findAll()) {
			if (tempC.getBrand().equals(brand) && tempC.getCarNumber().equals(carNumber)&& tempC.getType().equals(type)
				&& tempC.getModel().equals(model) && tempC.getClient().equals(client)) {
				throw new Exception("Car already exists");
			}
		}
		Car newCar = new Car(brand, carNumber, type, model, client);
		carRepo.save(newCar);
	
	}

	@Override
	public void deleteById(int idCar) throws Exception {
		if(idCar <= 0) throw new Exception("id should be positive");
	
		Car deleteCar = retrieveById(idCar);
		carRepo.delete(deleteCar);
	}
	
	public ArrayList<Car> findByCarNumber(String carNumber) throws Exception {
		if(carNumber == null) throw new Exception("wrong input parameter");
		
		ArrayList<Car> result = carRepo.findByCarNumber(carNumber);
		
		if(result.isEmpty())
			throw new Exception("There is no car with " + carNumber + "carNumber");
		
		return result;
	}
	

	//TODO
	@Override
	public ArrayList<Car> findByBrandAndType(CarBrand brand, CarType type) throws Exception {
	if(brand == null || type == null) throw new Exception("wrong input parameters");
		
		ArrayList<Car> result = carRepo.findByBrandAndType(brand, type);
		
		if(result.isEmpty())
			throw new Exception("There is no car with that brand and model");
		return result;
	}
	
}
