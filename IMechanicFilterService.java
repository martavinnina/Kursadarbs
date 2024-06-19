package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Mechanic;

public interface IMechanicFilterService {

	public abstract ArrayList<Mechanic> findByExperienceInYearsGreaterThan(float tresh) throws Exception;
	
	public abstract ArrayList<Mechanic> findByExperienceInYearsLessThan(float tresh) throws Exception;
	
	public abstract ArrayList<Mechanic> findByLicenceNo(String licenceNo)
			throws Exception;
	
	public abstract ArrayList<Mechanic> retrieveAll() throws Exception;
	
	public abstract Mechanic retrieveById(int id)throws Exception;
	
	public abstract void updateById(int id, String name, String surname, String phoneNo, float experience, String licenceNo) 
			throws Exception;

	public abstract void create(String name, String surname, String phoneNo, float experience, String licenceNo) throws Exception;
	
	public abstract void deleteById(int id) throws Exception;
}