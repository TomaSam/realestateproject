package lt.practice.realestate.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
@NoArgsConstructor
@Entity
public class Building {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull(message = "Address may not be null")
	@Length(min=5, max=200, message = "Address must be between 5 and 200 characters long")
	private String address;
	@NotNull(message = "Owner may not be null")
	@Length(min=5, max=100, message = "Owner must be between 5 and 100 characters long")
	private String owner;
	@Min(value=10, message = "Size should not be less than 10.00 square meters")
	private double size;
	@Min(value=10000, message="Market value should not be less than 10 000 euros")
	private double marketValue;
	@NotNull(message = "Property Type may not be null")
	private PropertyType propertyType;
	
	public Building(String address, String owner, double size, double marketValue, PropertyType propertyType) {
		this.address = address;
		this.owner = owner;
		this.size = size;
		this.marketValue = marketValue;
		this.propertyType = propertyType;
	}

	public Building(Long id, String address, String owner, double size, double marketValue, PropertyType propertyType) {
		this.id = id;
		this.address = address;
		this.owner = owner;
		this.size = size;
		this.marketValue = marketValue;
		this.propertyType = propertyType;
	}
	
	
	
	
	
	
}
