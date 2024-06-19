package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Car;
import lv.venta.model.CarBrand;
import lv.venta.model.CarType;


public interface ICarRepo extends CrudRepository<Car,Integer>{

	Car findById(int idCar);
	
	ArrayList<Car> findByCarNumber(String carNumber);

	ArrayList<Car> findByBrandAndType(CarBrand brand, CarType type);

	

}