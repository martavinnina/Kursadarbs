package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Car;
import lv.venta.model.Mechanic;
import lv.venta.model.Procedure;

public interface IProcedureRepo  extends CrudRepository<Procedure, Integer>{

	Procedure findById(int id);

	ArrayList<Procedure> findByMechanicIdM(int id);

	ArrayList<Procedure> findByCarIdCar(int id);
}
