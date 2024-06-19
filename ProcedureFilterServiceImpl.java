package lv.venta.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Car;
import lv.venta.model.Mechanic;
import lv.venta.model.Part;
import lv.venta.model.Procedure;
import lv.venta.repo.ICarRepo;
import lv.venta.repo.IMechanicRepo;
import lv.venta.repo.IProcedureRepo;
import lv.venta.service.IProcedureService;

@Service
public class ProcedureFilterServiceImpl implements IProcedureService{
	@Autowired
	private IProcedureRepo procedureRepo;
	
	@Autowired
	private IMechanicRepo mechanicRepo;
	
	@Autowired
	private ICarRepo carRepo;

	
	@Override
	public ArrayList<Procedure> retrieveAll() throws Exception {
		if (procedureRepo.count() == 0)
			throw new Exception("Procedure list is empty");
		return (ArrayList<Procedure>) procedureRepo.findAll();
	}

	@Override
	public Procedure retrieveById(int id) throws Exception {
		if(id <= 0) throw new Exception("id should be positive");
		
		Procedure result = procedureRepo.findById(id);
		return result;
	}

	@Override
	public void updateById(int id, Mechanic mechanic, Car car, Part part, LocalDateTime plannedReturn)
			throws Exception {
		if(id < 0 || mechanic == null || car == null || part == null || plannedReturn == null)		
			throw new Exception("Wrong input parameters");
		
		Procedure updatedProcedure = procedureRepo.findById(id);
		if(mechanic != null)
			updatedProcedure.setMechanic(mechanic);
		if(car != null)
			updatedProcedure.setCar(car);
		if(part != null)
			updatedProcedure.setPart(part);
		if(plannedReturn!= null)
			updatedProcedure.setPlannedReturn(plannedReturn);
		
		procedureRepo.save(updatedProcedure);
		
	}

	@Override
	public void create(Mechanic mechanic, Car car, Part part, LocalDateTime plannedReturn) throws Exception {
		if (mechanic == null || car == null || part == null || plannedReturn == null)
			throw new Exception("Problems with input params");

		for (Procedure temp : procedureRepo.findAll()) {
			if (temp.getMechanic().equals(mechanic) && temp.getCar().equals(car)
					&& temp.getPart().equals(part) && temp.getPlannedReturn().equals(plannedReturn)){
				throw new Exception("Procedure already exists");
			}
		}
		Procedure newProcedure = new Procedure(mechanic, car, part, plannedReturn);
		procedureRepo.save(newProcedure);
	}

	@Override
	public void deleteById(int id) throws Exception {
		if(id <= 0)
			throw new Exception("Id should be positive");
		Procedure deleteProcedure = retrieveById(id);
		procedureRepo.delete(deleteProcedure);
		
	}

	@Override
	public ArrayList<Procedure> selectAllProcedureByMechanicId(int id) throws Exception {
		if(id <= 0) throw new Exception("Id should be positive");
		
		if(!mechanicRepo.existsById(id)) //izveidota funkcija, kas atbilst tikai customerAsPerson
			throw new Exception("There is no mechanic with id" + id);
		
		if (procedureRepo.count()==0)
			throw new Exception("procedure list is empty");
		
		
		ArrayList<Procedure> result = procedureRepo.findByMechanicIdM(id);
		return result;
			
		}
	

	@Override
	public ArrayList<Procedure> selectAllProcedureByCarId(int id) throws Exception {
		if(id <= 0) throw new Exception("Id should be positive");
		
		if(!carRepo.existsById(id)) //izveidota funkcija, kas atbilst tikai customerAsPerson
			throw new Exception("There is no car with id" + id);
		
		if (procedureRepo.count()==0)
			throw new Exception("procedure list is empty");
		
		
		ArrayList<Procedure> result = procedureRepo.findByCarIdCar(id);
		return result;
	}
	
	
	

}
