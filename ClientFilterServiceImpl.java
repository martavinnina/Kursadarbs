package lv.venta.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Client;
import lv.venta.repo.ICarRepo;
import lv.venta.repo.IClientRepo;
import lv.venta.service.IClientFilterService;

@Service
public class ClientFilterServiceImpl implements IClientFilterService{
	
	@Autowired
	private IClientRepo clientRepo;
	

	/*@Override
	public ArrayList<Client> findByCarIdCar(int id) throws Exception {
		if(id < 0) throw new Exception("id should be 0 or higher");
		
		ArrayList<Client> result = clientRepo.findByCarIdCar(id);
		
		if(result.isEmpty())
			throw new Exception("There is no car with " + id + " id number");
		
		return result;
	}
*/
	public ArrayList<Client> findByPhoneNo(String phoneNo) throws Exception {
		if(phoneNo == null) throw new Exception("wrong input parameter");
		
		ArrayList<Client> result = clientRepo.findByPhoneNo(phoneNo);
		
		if(result.isEmpty())
			throw new Exception("There is no client with " + phoneNo + " phone number");
		
		return result;
	}

	@Override
	public ArrayList<Client> findByClientNameAndSurname(String name, String surname) throws Exception {
		if(name == null || surname == null) throw new Exception("wrong input parameters");
		
		ArrayList<Client> result = clientRepo.findByNameAndSurname(name, surname);
		
		if(result.isEmpty())
			throw new Exception("There is no client with that name and surname");
		return result;
	}

	@Override
	public void create(String name, String surname, String phoneNo) throws Exception {
		if (name == null || surname == null || phoneNo == null)
			throw new Exception("Problems with input params");

		for (Client temp : clientRepo.findAll()) {
			if (temp.getName().equals(name) && temp.getSurname().equals(surname)
					&& temp.getPhoneNo().equals(phoneNo)) {
				throw new Exception("Client already exists");
			}
		}
		Client newClient = new Client(name, surname, phoneNo);
		clientRepo.save(newClient);
	}

	@Override
	public ArrayList<Client> retrieveAll() throws Exception {
		if (clientRepo.count() == 0)
			throw new Exception("Client list is empty");
		return (ArrayList<Client>) clientRepo.findAll();
	}
	
	public Client retrieveById(int id) throws Exception {
		if(id < 0)
			throw new Exception("Id should be positive");
		Client result = clientRepo.findById(id);
		return result;	
	}

	@Override
	public void updateById(int id, String name, String surname, String phoneNo) throws Exception {
		if(id < 0 || name == null || surname == null || phoneNo == null)
			throw new Exception("Wrong input parameters");
		Client updateClient = clientRepo.findById(id);
		if(name != null)
			updateClient.setName(name);
		if(surname !=null) 
			updateClient.setSurname(surname);
		if(phoneNo != null) 
			updateClient.setPhoneNo(phoneNo);
		clientRepo.save(updateClient);
	}

	@Override
	public void deleteById(int id) throws Exception {
		if(id <= 0)
			throw new Exception("Id should be positive");
		Client deleteClient = retrieveById(id);
		clientRepo.delete(deleteClient);
		//clientRepo.deleteById(id);
	}
}