package lt.practice.realestate;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import lt.practice.realestate.models.Building;
import lt.practice.realestate.models.PropertyType;

public class ValidationTest {
	private static Validator validator;

	   @BeforeClass
	   public static void setUp() {
	      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	      validator = factory.getValidator();
	   }

	   @Test
	   public void buildingAddressIsNull() {
	      Building test = new Building( null, "Company", 50.00, 50000.00, PropertyType.HOUSE );

	      Set<ConstraintViolation<Building>> constraintViolations =
	      validator.validate( test );

	      assertEquals( 1, constraintViolations.size() );
	      assertEquals(
	         "may not be null",
	         constraintViolations.iterator().next().getMessage()
	      );
	   }
	   
	   @Test
	   public void buildingOwnerIsNull() {
	      Building test = new Building( "Jogailos g. 50, Kaunas", null, 50.00, 50000.00, PropertyType.HOUSE );

	      Set<ConstraintViolation<Building>> constraintViolations =
	      validator.validate( test );

	      assertEquals( 1, constraintViolations.size() );
	      assertEquals(
	         "may not be null",
	         constraintViolations.iterator().next().getMessage()
	      );
	   }

	   @Test
	   public void buildingSizeIsSmaller() {
	      Building test = new Building( "Vilniaus g.23, Vilnius", "Petras", 4.00, 12000.00, PropertyType.APARTMENT );

	      Set<ConstraintViolation<Building>> constraintViolations =
	      validator.validate( test );

	      assertEquals( 1, constraintViolations.size() );
	      assertEquals(
	         "size must be bigger than 10",
	         constraintViolations.iterator().next().getMessage()
	      );
	   }
	   
	   @Test
	   public void buildingMarketValueIsSmaller() {
	      Building test = new Building( "Vilniaus g.23-78, Vilnius", "Petras", 78.90, 8000.00, PropertyType.APARTMENT );

	      Set<ConstraintViolation<Building>> constraintViolations =
	      validator.validate( test );

	      assertEquals( 1, constraintViolations.size() );
	      assertEquals(
	         "market value must be bigger than 10 000 euros",
	         constraintViolations.iterator().next().getMessage()
	      );
	   }

	   @Test
	   public void buildingPropertyTypeIsNull() {
	      Building test = new Building( "Jogailos g. 50, Kaunas", "Jonas Jonaitis", 50.00, 50000.00, null );

	      Set<ConstraintViolation<Building>> constraintViolations =
	      validator.validate( test );

	      assertEquals( 1, constraintViolations.size() );
	      assertEquals(
	         "may not be null",
	         constraintViolations.iterator().next().getMessage()
	      );
	   }



	   @Test
	   public void carIsValid() {
	      Building test = new Building( "Jogailos g. 50, Kaunas", "Jonas Jonaitis", 50.00, 50000.00, PropertyType.HOUSE );

	      Set<ConstraintViolation<Building>> constraintViolations =
	      validator.validate( test );

	      assertEquals( 0, constraintViolations.size() );
	   }

}
