package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.LightType;
import lv.venta.model.LightUseType;
import lv.venta.model.Lights;

public interface ILightsRepo extends CrudRepository<Lights, Integer>{

	ArrayList<Lights> findByPriceLessThan(float price);

	ArrayList<Lights> findByManufacturer(String manufacturer);
	
	Lights findById(int id);

	ArrayList<Lights> findByLightType(LightType lightType);

	ArrayList<Lights> findByLightUseType(LightUseType lightUseType);

}