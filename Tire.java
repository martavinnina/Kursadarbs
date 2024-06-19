package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Table(name = "TiresTable")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Tire extends Part {
    
	@Column(name = "tire_size")
	private TireSize tireSize;
    
	@Column(name = "tire_type")
    private TireType tireType;
    
    @Column(name = "loudness_db")
    @NotNull
    @Min(value = 50)
    @Max(value = 90)
    private int loudnessDb;
    
    @Column(name = "driving_in_rain")
    private TireRating drivingInRain;
    
    @Column(name = "fuel_efficiency")
    private TireRating fuelEfficiency;
    
    public Tire(float price, String manufacturer, TireSize tireSize, TireType tireType, int loudnessDb, TireRating drivingInRain, TireRating fuelEfficiency) {
        super(price, manufacturer);
        this.tireSize = tireSize;
        this.tireType = tireType;
        this.loudnessDb = loudnessDb;
        this.drivingInRain = drivingInRain;
        this.fuelEfficiency = fuelEfficiency;
    }
}