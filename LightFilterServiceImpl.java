
package lv.venta.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.LightType;
import lv.venta.model.LightUseType;
import lv.venta.model.Lights;
import lv.venta.model.Tire;
import lv.venta.repo.ILightsRepo;
import lv.venta.service.ILightFilterService;

@Service
public class LightFilterServiceImpl implements ILightFilterService{

	@Autowired
	private ILightsRepo lightsRepo;
	
	@Override
	public ArrayList<Lights> retrieveAll() throws Exception {
		if(lightsRepo.count() == 0)
			throw new Exception("Tire list is empty");
		return (ArrayList<Lights>) lightsRepo.findAll();
	}

	@Override
	public ArrayList<Lights> selectAllLightsPriceLessThan(float price) throws Exception {
		if(price < 0 || price > 1000) throw new Exception("The limit of price is wrong");
		
		return (ArrayList<Lights>) lightsRepo.findByPriceLessThan(price);
	}

	@Override
	public ArrayList<Lights> selectAllLightsByManufacturer(String manufacturer) throws Exception {
		if(manufacturer == null) throw new Exception("The manufacturer can't be null");
		
		return (ArrayList<Lights>) lightsRepo.findByManufacturer(manufacturer);
	}

	@Override
	public void updateById(int id, float price, String manufacturer, LightType lightType, LightUseType lightUseType, int lightColorKelvin, int powerWatt) throws Exception{
		if(id < 0 || price < 0 || price > 1000 || manufacturer == null || lightType == null || lightUseType == null || lightColorKelvin < 2000 || lightColorKelvin > 8000 || powerWatt < 1 || powerWatt > 200)
			throw new Exception("Wrong input parameters");
			
		Lights updateLights = lightsRepo.findById(id);
		updateLights.setPrice(price);
		updateLights.setManufacturer(manufacturer);
		updateLights.setLightType(lightType);
		updateLights.setLightUseType(lightUseType);
		updateLights.setLightColorKelvin(lightColorKelvin);
		updateLights.setPowerWatt(powerWatt);
			
		lightsRepo.save(updateLights);
	}

	@Override
	public Lights retrieveById(int id) throws Exception {
		if(id < 0)
			throw new Exception("Id should be positive");
		Lights result = lightsRepo.findById(id);
		return result;
	}

	@Override
	public void createNewLights(float price, String manufacturer, LightType lightType, LightUseType lightUseType, int lightColorKelvin, int powerWatt) throws Exception {
		if(price < 0 || price > 1000 || manufacturer == null || lightType == null || lightUseType == null || lightColorKelvin < 2000 || lightColorKelvin > 8000 || powerWatt < 1 || powerWatt > 200)
			throw new Exception("Wrong input parameters");

		for (Lights temp : lightsRepo.findAll()) {
			if (temp.getPrice() ==price && temp.getManufacturer().equals(manufacturer) && temp.getLightType().equals(lightType) && temp.getLightUseType().equals(lightUseType) && temp.getLightColorKelvin() == lightColorKelvin && temp.getPowerWatt() == powerWatt) {
				throw new Exception("These lights already exists");
			}
		}
		Lights newLights = new Lights(price, manufacturer, lightType, lightUseType, lightColorKelvin, powerWatt);
		lightsRepo.save(newLights);
	}

	@Override
	public void deleteById(int id) throws Exception {
		if(id <= 0)
			throw new Exception("Id should be positive");
		Lights deleteLights = retrieveById(id);
		lightsRepo.delete(deleteLights);
	}

	@Override
	public ArrayList<Lights> selectAllLightsByLightType(LightType lightType) throws Exception {
		if(lightType == null) throw new Exception("The light type can't be null");
		
		return (ArrayList<Lights>) lightsRepo.findByLightType(lightType);
	}

	@Override
	public ArrayList<Lights> selectAllLightsByLightUseType(LightUseType lightUseType) throws Exception {
		if(lightUseType == null) throw new Exception("The light use type can't be null");
		
		return (ArrayList<Lights>) lightsRepo.findByLightUseType(lightUseType);
	}

}
