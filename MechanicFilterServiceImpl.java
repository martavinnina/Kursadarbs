package lv.venta.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lv.venta.model.Mechanic;
import lv.venta.model.Mechanic;
import lv.venta.model.Mechanic;
import lv.venta.repo.IMechanicRepo;
import lv.venta.service.IMechanicFilterService;

@Service
public class MechanicFilterServiceImpl implements IMechanicFilterService{
	@Autowired
	private IMechanicRepo mechanicRepo;

	@Override
	public ArrayList<Mechanic> findByExperienceInYearsGreaterThan(float tresh) throws Exception {
		if(tresh < 0) throw new Exception("Wrong input parameter");	
		ArrayList<Mechanic> result = mechanicRepo.findByExperienceGreaterThan(tresh);
		if(result.isEmpty()) throw new Exception("There are no mechanics with that much of experience");		
		return result;
	}

	@Override
	public ArrayList<Mechanic> findByExperienceInYearsLessThan(float tresh) throws Exception {
		if(tresh <= 0) throw new Exception("Wrong input parameter");	
		ArrayList<Mechanic> result = mechanicRepo.findByExperienceLessThan(tresh);
		if(result.isEmpty()) throw new Exception("There are no mechanics with that little experience");		
		return result;
	}

	public ArrayList<Mechanic> findByLicenceNo(String licenceNo) throws Exception {
		if(licenceNo == null) throw new Exception("wrong input parameter");
		ArrayList<Mechanic> result = mechanicRepo.findByLicenceNo(licenceNo);
		if(result.isEmpty())
			throw new Exception("There is no mechanic with " + licenceNo + " licence number");
		return result;
	}

	@Override
	public ArrayList<Mechanic> retrieveAll() throws Exception {
		if (mechanicRepo.count() == 0)
			throw new Exception("Mechanic list is empty");
		return (ArrayList<Mechanic>) mechanicRepo.findAll();
	}

	@Override
	public Mechanic retrieveById(int id) throws Exception {
		if(id < 0)
			throw new Exception("Id should be positive");
		Mechanic result = mechanicRepo.findById(id);
		return result;	
	}

	@Override
	public void updateById(int id, String name, String surname, String phoneNo, float experience, String licenceNo)
			throws Exception {
		if(id < 0 || name == null || surname == null || phoneNo == null || experience < 0 || licenceNo == null)
			throw new Exception("Wrong input parameters");
		Mechanic updateMechanic = mechanicRepo.findById(id);
		if(name != null)
			updateMechanic.setName(name);
		if(surname !=null) 
			updateMechanic.setSurname(surname);
		if(phoneNo != null) 
			updateMechanic.setPhoneNo(phoneNo);
		if(experience >= 0) 
			updateMechanic.setExperience(experience);
		if(licenceNo != null) 
			updateMechanic.setLicenceNo(licenceNo);
		mechanicRepo.save(updateMechanic);
	}

	@Override
	public void create(String name, String surname, String phoneNo, float experience, String licenceNo)
			throws Exception {
		if (name == null || surname == null || phoneNo == null || experience < 0 || licenceNo == null)
			throw new Exception("Problems with input params");

		for (Mechanic temp : mechanicRepo.findAll()) {
			if (temp.getName().equals(name) && temp.getSurname().equals(surname)
					&& temp.getPhoneNo().equals(phoneNo) && temp.getLicenceNo().equals(licenceNo)){
				throw new Exception("Mechanic already exists");
			}
		}
		Mechanic newMechanic = new Mechanic(name, surname, phoneNo, experience, licenceNo);
		mechanicRepo.save(newMechanic);
		
	}

	@Override
	public void deleteById(int id) throws Exception {
		if(id <= 0)
			throw new Exception("Id should be positive");
		Mechanic deleteMechanic = retrieveById(id);
		mechanicRepo.delete(deleteMechanic);
	}
}