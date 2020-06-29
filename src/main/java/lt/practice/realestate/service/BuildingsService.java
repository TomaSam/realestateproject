package lt.practice.realestate.service;

import java.util.List;

import lt.practice.realestate.models.Building;

public interface BuildingsService {
	
	List<Building> getAllBuildings();
	List<Building> getBuildingsByOwner(String owner);
	Building createBuilding(Building building);
	void updateBuilding(Long buildingId, Building building);
	void deleteBuilding(Long buildingId);
	double getTaxByOwner(String owner);
	Building findById(Long buildingId);
	

}
