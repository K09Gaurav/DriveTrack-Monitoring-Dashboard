package com.Vehicleanalyzer.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Vehicleanalyzer.Models.EngineData;

@Repository
public interface EngineRepository extends JpaRepository<EngineData, Long> {

    @Query("SELECT f FROM EngineData f WHERE f.vehicle.id = :vehicleid")
    // gets engine data for a car
    List<EngineData> findByVehicleid(@Param("vehicleid") Long vehicleid);



    // _Id accesses its primary key.
    @Query("""
        SELECT
            AVG(e.RPM)
        FROM EngineData e
        WHERE e.vehicle.id = :vehicleId""")
    // finds average rpm
    double getAverageRPMByVehicleId(@Param("vehicleId") Long vehicleId);

    @Query("""
        SELECT 
            AVG(e.Temperature) 
        FROM EngineData e 
        WHERE e.vehicle.id = :vehicleId""")
    // finds average temperature
    double getAverageTemperatureByVehicleId(@Param("vehicleId") Long vehicleId);

}
