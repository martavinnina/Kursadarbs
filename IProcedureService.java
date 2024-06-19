package lv.venta.service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import lv.venta.model.Car;
import lv.venta.model.Lights;
import lv.venta.model.Mechanic;
import lv.venta.model.Part;
import lv.venta.model.Procedure;

public interface IProcedureService {
//crud 
//filter 
//pec laika
	public abstract ArrayList<Procedure> retrieveAll() throws Exception;
	
	public abstract Procedure retrieveById(int id)throws Exception;
	
	public abstract void updateById(int id, Mechanic mechanic, Car car, Part part, LocalDateTime plannedReturn) 
			throws Exception;

	public abstract void create(Mechanic mechanic, Car car, Part part, LocalDateTime plannedReturn) throws Exception;
	
	public abstract void deleteById(int id) throws Exception;
	
	ArrayList<Procedure> selectAllProcedureByMechanicId(int id) throws Exception;
	
	ArrayList<Procedure> selectAllProcedureByCarId(int id) throws Exception;
	
	//filtracija pec datuma TODO
	
	
	
}
