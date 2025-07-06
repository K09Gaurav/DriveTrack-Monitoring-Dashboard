package com.Vehicleanalyzer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Vehicleanalyzer.Models.Vehicle;

// this is for cars in database
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

}

