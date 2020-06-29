package lt.practice.realestate.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	private String address;
	private String owner;
	private double size;
	private double marketValue;
	private PropertyType propertyType;
	
	public Building(String address, String owner, double size, double marketValue, PropertyType propertyType) {
		this.address = address;
		this.owner = owner;
		this.size = size;
		this.marketValue = marketValue;
		this.propertyType = propertyType;
	}
	
	
	
	
}
