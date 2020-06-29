package lt.practice.realestate.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import lt.practice.realestate.models.Building;

public interface BuildingsRepository extends JpaRepository<Building, Long> {
	

}
