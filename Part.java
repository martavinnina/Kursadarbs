package lv.venta.model;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "PartTable")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Part {

	@Column(name = "idPa")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idPa;
	
	@Column(name = "price")
	@NotNull
	@DecimalMin(value = "1.0")
	@DecimalMax(value = "1000.0")
	private float price;
	
	@Column(name = "manufacturer")
	@NotNull
	@Size(min = 2, max = 40)
	@Pattern(regexp = "[A-Z]{1}[a-z]+")
	private String manufacturer;
	
	@ManyToOne //vairakiem part viena masina
	@JoinColumn(name = "idCar")
	private Car car;
	
	@OneToMany(mappedBy = "part")
	@ToString.Exclude
	private Collection<Procedure> procedures; 
	
	public Part(float price, String manufacturer) {
		setPrice(price);
		setManufacturer(manufacturer);
	}
	
}