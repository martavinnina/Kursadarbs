package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Part;

public interface IPartRepo extends CrudRepository<Part, Integer>{

}
