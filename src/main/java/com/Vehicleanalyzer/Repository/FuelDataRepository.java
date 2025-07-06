package com.Vehicleanalyzer.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Vehicleanalyzer.Models.FuelData;

@Repository
public interface FuelDataRepository extends JpaRepository<FuelData, Long> {

    @Query("SELECT f FROM FuelData f WHERE f.vehicle.id = :vehicleid")
    // gets fuel data for a car
    List<FuelData> findByVehicle_id(@Param("vehicleid") Long vehicleid);

    @Query("""
        SELECT 
            SUM(f.liters_used)
        FROM FuelData f 
        WHERE f.vehicle.id = :vehicleid""")
    // adds up all fuel used
    Double TotalFuelUsage(@Param("vehicleid") Long vehicleid);

    @Query("""
        SELECT 
            SUM(f.distance_km)
        FROM FuelData f 
        WHERE f.vehicle.id = :vehicleid""")
    // adds up all distance
    Double TotalDistance(@Param("vehicleid") Long vehicleid);

}
