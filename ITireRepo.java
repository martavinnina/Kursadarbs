package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Tire;
import lv.venta.model.TireSize;
import lv.venta.model.TireType;

public interface ITireRepo extends CrudRepository<Tire, Integer>{

	ArrayList<Tire> findByPriceLessThan(float price);

	ArrayList<Tire> findByManufacturer(String manufacturer);
	
	Tire findById(int id);

	ArrayList<Tire> findByTireType(TireType tireType);

	ArrayList<Tire> findByTireSize(TireSize tireSize);

}