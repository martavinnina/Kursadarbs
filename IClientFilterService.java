package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Client;

public interface IClientFilterService {
	//public abstract ArrayList<Client> findByCarIdCar(int id) 
			//throws Exception;
	public abstract ArrayList<Client> findByPhoneNo(String phoneNo)
			throws Exception;
	public abstract ArrayList<Client> findByClientNameAndSurname(String name, String surname)
			throws Exception;
	
	public abstract ArrayList<Client> retrieveAll() throws Exception;
	
	public abstract Client retrieveById(int id)throws Exception;
	
	public abstract void updateById(int id, String name, String surname, String phoneNo) 
			throws Exception;

	public abstract void create(String name, String surname, String phoneNo) throws Exception;
	
	public abstract void deleteById(int id) throws Exception;
}