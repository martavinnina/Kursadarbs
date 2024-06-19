package lv.venta.service;

import java.util.ArrayList;

import org.springframework.ui.Model;

import lv.venta.model.Car;
import lv.venta.model.CarBrand;
import lv.venta.model.CarType;
import lv.venta.model.Client;
import lv.venta.model.Mechanic;

public interface ICarFilterService {

	
public abstract ArrayList<Car> retrieveAll() throws Exception;
	
	public abstract Car retrieveById(int idCar)throws Exception;
	
	public abstract void updateById(int idCar, CarBrand brand, String carNumber, CarType type, String model, Client client) 
			throws Exception;

	public abstract void create(CarBrand brand, String carNumber, CarType type, String model, Client client) throws Exception;
	
	public abstract void deleteById(int idCar) throws Exception;

	public abstract ArrayList<Car> findByCarNumber(String carNumber)
			throws Exception;
	
	public abstract ArrayList<Car> findByBrandAndType(CarBrand brand, CarType type)
			throws Exception;
	
	
}


