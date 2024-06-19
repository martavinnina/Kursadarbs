package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Client;

public interface IClientRepo extends CrudRepository<Client, Integer>{

	Client findById(int id);
	
	//ArrayList<Client> findByCarIdCar(int id);

	ArrayList<Client> findByPhoneNo(String phoneNo);
	
	ArrayList<Client> findByNameAndSurname(String name, String surname);
	
}