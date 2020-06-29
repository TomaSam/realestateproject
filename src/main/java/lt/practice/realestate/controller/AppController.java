package lt.practice.realestate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.practice.realestate.models.Building;
import lt.practice.realestate.service.BuildingsService;

@RestController
@RequestMapping("/api/buildings")
public class AppController {
	
	@Autowired
	private BuildingsService buildingsService;
	
	@GetMapping
	public List<Building> getAllBuildings() {
		return buildingsService.getAllBuildings();
	}
	
	@GetMapping(value="/{owner}")
	public List<Building> getBuildingsByOwner(@PathVariable("owner") String owner) {
		return buildingsService.getBuildingsByOwner(owner);	
	}
	
	@PostMapping
	public Building createBuilding(@RequestBody Building building) {
		return buildingsService.createBuilding(building);
	}
	
	@PutMapping(value="/{buildingId}")
	public void updateBuilding(@PathVariable("buildingId") Long buildingId, @RequestBody Building building) {
		buildingsService.updateBuilding(buildingId, building);	
	}
	
	@DeleteMapping(value="/{buildingId}")
	public void deleteBuilding(@PathVariable("buildingId") Long buildingId) {
		buildingsService.deleteBuilding(buildingId);
	}
	
	@GetMapping(value="tax/{owner}")
	public double getTaxByOwner(@PathVariable("owner") String owner) {
		return buildingsService.getTaxByOwner(owner);
	}

}
