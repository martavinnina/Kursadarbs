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

//@Table(name = "LightsTable")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Lights extends Part{
	
	@Column(name = "light_type")
	private LightType lightType;
	
	@Column(name = "light_use_type")
	private LightUseType lightUseType;
	
	@Column(name = "light_color_kelvin")
    @NotNull
    @Min(value = 2000)
	@Max(value = 8000)
	private int lightColorKelvin;
	
	@Column(name = "power_watt")
    @NotNull
    @Min(value = 1)
	@Max(value = 200)
	private int powerWatt;
	
	
	public Lights(float price, String manufacturer, LightType lightType, LightUseType lightUseType, int lightColorKelvin, int powerWatt) {
        super(price, manufacturer);
        this.lightType = lightType;
        this.lightUseType = lightUseType;
        this.lightColorKelvin = lightColorKelvin;
        this.powerWatt = powerWatt;
    }

}