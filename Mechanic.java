package lv.venta.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@Table(name = "Mechanic_Table")
public class Mechanic extends Person{
	@Column(name="IdM")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int idM;
	
	@Column(name = "Experience")
	@NotNull
	@Min(0)
	private float experience;
	
	@Column(name = "LicenceNo")
	@NotNull
	@Size(min = 2, max = 20)
	@Pattern(regexp = "[mM]{1}[0-9]{6}")
	private String licenceNo;
	
	public Mechanic(String name, String surname, String phoneNo, float experience, String licenceNo) {
		super(name, surname, phoneNo);
		setExperience(experience);
		setLicenceNo(licenceNo);
	}
	
	/*@OneToMany(mappedBy = "mechanic")
	@ToString.Exclude
	private Collection<Car> cars = new ArrayList<Car>();
	*/
	@OneToMany(mappedBy = "mechanic")
	@ToString.Exclude
	private Collection<Procedure> procedures = new ArrayList<Procedure>();
	
	/*public void addCar(Car car) {
		if(!cars.contains(car))
			cars.add(car);
	}
	
	public void removeCar(Car car) {
		if(cars.contains(car))
			cars.remove(car);
	}
	*/
	
}