package lt.practice.realestate;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import lt.practice.realestate.models.Building;
import lt.practice.realestate.models.PropertyType;
import lt.practice.realestate.repository.BuildingsRepository;
import lt.practice.realestate.service.BuildingsServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BuildingsServiceImplTest {

	@Mock
	private BuildingsRepository buildingsRepository;
	
	@InjectMocks
	private BuildingsServiceImpl service;
	
	@Test
	@DisplayName("Test get all buildings")
	public void testGetAllBuildings() {
		List<Building> buildings = new ArrayList<>();
		buildings.add(new Building("Pirmoji g. 7, Kaunas", "Jonas Jonaitis", 150.0, 250000.0, PropertyType.HOUSE));
		buildings.add(new Building("Antroji g. 34-67, Kaunas", "Jonas Jonaitis", 78.0, 350000.0, PropertyType.APARTMENT));
		buildings.add(new Building("Trecioji g. 45, Kaunas", "UAB Maxima", 1500.0, 2500000.0, PropertyType.INDUSTRIAL));
		when(buildingsRepository.findAll()).thenReturn(buildings);
		
		List<Building> result = service.getAllBuildings();
		Assertions.assertEquals(3, result.size());
	}
	
	@Test
	@DisplayName("Test get buildings by owner")
	public void testGetBuildingsByOwner() {
		List<Building> buildings = new ArrayList<>();
		buildings.add(new Building("Pirmoji g. 7, Kaunas", "Jonas Jonaitis", 150.0, 250000.0, PropertyType.HOUSE));
		buildings.add(new Building("Antroji g. 34-67, Kaunas", "Jonas Jonaitis", 78.0, 350000.0, PropertyType.APARTMENT));
		buildings.add(new Building("Trecioji g. 45, Kaunas", "UAB Maxima", 1500.0, 2500000.0, PropertyType.INDUSTRIAL));
		when(buildingsRepository.findAll()).thenReturn(buildings);
		
		List<Building> result = service.getBuildingsByOwner("Jonas Jonaitis");
		Assertions.assertEquals(2, result.size());
	}
	
	@Test
	@DisplayName("Test create building")
	public void testCreate() {
		Building building = new Building("Pirmoji g. 7, Kaunas", "Jonas Jonaitis", 150.0, 250000.0, PropertyType.HOUSE);
		doReturn(building).when(buildingsRepository).save(any());
		
		Building result = service.createBuilding(building);
		Assertions.assertNotNull(result, "The created building should not be null");
		Assertions.assertEquals("Pirmoji g. 7, Kaunas", result.getAddress());
		Assertions.assertEquals("Jonas Jonaitis", result.getOwner());
		Assertions.assertEquals(150.0, result.getSize(), 0.1);
		Assertions.assertEquals(250000, result.getMarketValue(), 0.1);
		Assertions.assertEquals(PropertyType.HOUSE, result.getPropertyType());
	}
	
	@Test
	@DisplayName("Test delete building")
	public void testDeleteBuilding() {
		Building building = new Building("Pirmoji g. 7, Kaunas", "Jonas Jonaitis", 150.0, 250000.0, PropertyType.HOUSE);
		service.deleteBuilding(building.getId());
		verify(buildingsRepository, times(1)).deleteById(building.getId());
	}
	
	@Test
	@DisplayName("Test get total tax sum by owner")
	public void testGetTaxByOwner() {
		List<Building> buildings = new ArrayList<>();
		buildings.add(new Building("Pirmoji g. 7, Kaunas", "Jonas Jonaitis", 150.0, 250000.0, PropertyType.HOUSE));
		buildings.add(new Building("Antroji g. 34-67, Kaunas", "Jonas Jonaitis", 78.0, 350000.0, PropertyType.APARTMENT));
		buildings.add(new Building("Trecioji g. 45, Kaunas", "UAB Maxima", 1500.0, 2500000.0, PropertyType.INDUSTRIAL));
		when(buildingsRepository.findAll()).thenReturn(buildings);
		
		double result = service.getTaxByOwner("Jonas Jonaitis");
		Assertions.assertEquals(17500.0, result, 0.1);
	}
	
	
	
	
	
	
}
