package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.LightType;
import lv.venta.model.LightUseType;
import lv.venta.model.Lights;

public interface ILightFilterService {
	
	public abstract ArrayList<Lights> retrieveAll() throws Exception;

	ArrayList<Lights> selectAllLightsPriceLessThan(float price) throws Exception;

	ArrayList<Lights> selectAllLightsByManufacturer(String manufacturer) throws Exception;

	public abstract void updateById(int id, float price, String manufacturer, LightType lightType, LightUseType lightUseType, int lightColorKelvin, int powerWatt) throws Exception;

	Lights retrieveById(int id) throws Exception;

	void createNewLights(float price, String manufacturer, LightType lightType, LightUseType lightUseType, int lightColorKelvin, int powerWatt) throws Exception;

	void deleteById(int id) throws Exception;

	ArrayList<Lights> selectAllLightsByLightType(LightType lightType) throws Exception;

	ArrayList<Lights> selectAllLightsByLightUseType(LightUseType lightUseType) throws Exception;

}