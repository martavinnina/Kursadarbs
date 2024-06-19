package lv.venta.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "ProcedureTable")
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Procedure {


@Column(name="IdPr")
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Setter(value = AccessLevel.NONE)
private int idPr; 

//vairakiem pakalpojumiem viens mehanikis
@ManyToOne
@JoinColumn(name = "IdM")
private Mechanic mechanic;

//vairakiem auto viens pakalpojums
@ManyToOne
@JoinColumn(name = "IdCar")
private Car car;

//viena detala vairakos pakalpojumos
@ManyToOne
@JoinColumn(name = "idPa")
private Part part;


@Column(name = "StartDateTime")
private LocalDateTime startDateTime = LocalDateTime.now();


@Column(name = "PlannedReturn")
@NotNull
private LocalDateTime plannedReturn;

public Procedure (Mechanic mechanic, Car car, Part part, LocalDateTime plannedReturn) {
	setMechanic(mechanic);
	setCar(car);
	setPart(part);
	setPlannedReturn(plannedReturn);
}
	
}
