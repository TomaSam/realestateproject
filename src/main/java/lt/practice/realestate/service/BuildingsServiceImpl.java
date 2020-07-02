package lt.practice.realestate.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lt.practice.realestate.models.Building;
import lt.practice.realestate.models.PropertyType;
import lt.practice.realestate.repository.BuildingsRepository;


@Service
public class BuildingsServiceImpl implements BuildingsService {
	
	private static final Logger log = LoggerFactory.getLogger(BuildingsServiceImpl.class);
	
	@Autowired
	BuildingsRepository buildingsRepository;

	@Override
	public List<Building> getAllBuildings() {
		return buildingsRepository.findAll();
	}

	@Override
	public List<Building> getBuildingsByOwner(String owner) {
		return buildingsRepository.findAll().stream()
				.filter(building -> building.getOwner().equals(owner)).collect(Collectors.toList());
	}

	@Override
	public Building createBuilding(Building building) {
		return buildingsRepository.save(building);
	}

	@Override
	public void updateBuilding(Long buildingId, Building building) {
		Building updatedBuilding = findById(buildingId);
		log.info("Find updated building " +  updatedBuilding);
		updatedBuilding.setAddress(building.getAddress());
		updatedBuilding.setOwner(building.getOwner());
		updatedBuilding.setMarketValue(building.getMarketValue());
		updatedBuilding.setSize(building.getSize());
		updatedBuilding.setPropertyType(building.getPropertyType());
		buildingsRepository.save(updatedBuilding);
	
	}

	@Override
	public void deleteBuilding(Long buildingId) {
		buildingsRepository.deleteById(buildingId);
		
	}
	
	@Override
	public double getTaxByOwner(String owner) {
		double totalTax = 0.0;
		for (Building building : getBuildingsByOwner(owner)) {
			if (building.getPropertyType().equals(PropertyType.APARTMENT)) {
				totalTax += (building.getMarketValue()*PropertyType.APARTMENT.getValue());
			}
			else if (building.getPropertyType().equals(PropertyType.HOUSE)) {
				totalTax += (building.getMarketValue()*PropertyType.HOUSE.getValue());
			}
			else {
				totalTax += (building.getMarketValue()*PropertyType.INDUSTRIAL.getValue());	
			}
		}
		return totalTax;
	}

	@Override
	public Building findById(Long buildingId) {
		return buildingsRepository.findById(buildingId).orElse(null);
	}

}
