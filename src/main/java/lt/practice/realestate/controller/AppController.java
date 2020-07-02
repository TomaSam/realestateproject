package lt.practice.realestate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.practice.realestate.models.Building;
import lt.practice.realestate.service.BuildingsService;


@Api(value="realestateapplication")
@RestController
@RequestMapping("/api/buildings")
public class AppController {
	
	@Autowired
	private BuildingsService buildingsService;
	
	@ApiOperation(value="Get buildings", notes="Returns all created buildings.")
	@GetMapping
	public  ResponseEntity<List<Building>> getAllBuildings() {
		List<Building> allBuildings = buildingsService.getAllBuildings();
		if (allBuildings.isEmpty()) {
			return new ResponseEntity<List<Building>>(HttpStatus.NOT_FOUND);
		}
	return new ResponseEntity<List<Building>>(allBuildings, HttpStatus.OK);
	}
	
	@ApiOperation(value="Get buildings by owner", notes="Get list of buildings by owner")
	@GetMapping(value="/{owner}")
	public ResponseEntity<List<Building>> getBuildingsByOwner(@ApiParam(value="name under which the buildings are to be found", required=true) @PathVariable("owner") String owner) {
		List<Building> buildingsByOwner = buildingsService.getBuildingsByOwner(owner);
		if (buildingsByOwner.isEmpty()) {
			return new ResponseEntity<List<Building>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Building>>(buildingsByOwner, HttpStatus.OK);	
	}
	
	@ApiOperation(value="Create building", notes="Create new building")
	@PostMapping
	public ResponseEntity<Building> createBuilding(@RequestBody Building building) {
		buildingsService.createBuilding(building);
		return new ResponseEntity<Building>(building, HttpStatus.CREATED);
	}
	
	@ApiOperation(value="Update building", notes="Update registrated building")
	@PutMapping(value="/{buildingId}")
	public ResponseEntity<Void> updateBuilding(@ApiParam(value="id under which the building for update is to be founded", required=true) 
	@PathVariable("buildingId") Long buildingId, @RequestBody Building building) {
		Building update = buildingsService.findById(buildingId);
		if (update == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		buildingsService.updateBuilding(buildingId, building);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ApiOperation(value="Delete building", notes="Delete building by building Id")
	@DeleteMapping(value="/{buildingId}")
	public ResponseEntity<Void> deleteBuilding(@ApiParam(value="id under which the building for delete is to be founded", required=true) 
	@PathVariable("buildingId") Long buildingId) {
		Building building = buildingsService.findById(buildingId);
		if (building == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		buildingsService.deleteBuilding(buildingId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value="Get tax", notes="Get total tax amount of buildings by owner")
	@GetMapping(value="tax/{owner}")
	public ResponseEntity<Double> getTaxByOwner(@ApiParam(value="name under which the buildings are to be found") @PathVariable("owner") String owner) {
		double tax = buildingsService.getTaxByOwner(owner);
		return new ResponseEntity<Double>(tax, HttpStatus.OK);
	}

}
