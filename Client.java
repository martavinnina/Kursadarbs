package lv.venta.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "ClientTable")
@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
public class Client extends Person{
	@Column(name="IdC")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int idC;
	
	public Client(String name, String surname, String phoneNo) {
		super(name, surname, phoneNo);
	}
	

	public void addCar(Car car) {
		if(!cars.contains(car))
			cars.add(car);
	}
	
	public void removeCar(Car car) {
		if(cars.contains(car))
			cars.remove(car);
	}
	
	@OneToMany(mappedBy = "client")
	@ToString.Exclude
	private Collection<Car> cars = new ArrayList<Car>();
}