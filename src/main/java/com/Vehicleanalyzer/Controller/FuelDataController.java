package com.Vehicleanalyzer.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Vehicleanalyzer.Models.FuelData;
import com.Vehicleanalyzer.Models.Vehicle;
import com.Vehicleanalyzer.Services.FuelDataService;
import com.Vehicleanalyzer.Services.VehicleService;

/**
 * Controller for managing fuel data for vehicles.
 * Base route: /api/vehicles/{id}/fuel-data
 * Handles CRUD operations and analytics for fuel data.
 */
@RestController
@RequestMapping("/api/vehicles/{id}/fuel-data")
public class FuelDataController {

    private final FuelDataService fuelDataService;
    private final VehicleService vehicleService;

    public FuelDataController(FuelDataService fuelDataService, VehicleService vehicleService) {
        this.fuelDataService = fuelDataService;
        this.vehicleService = vehicleService;
    }

    /**
     * Get all fuel data for a vehicle.
     * HTTP GET /api/vehicles/{id}/fuel-data
     *
     * @param id the ID of the vehicle
     * @return list of fuel data
     */
    @GetMapping()
    public ResponseEntity<List<FuelData>> getAllFuelDataByVehicleId(@PathVariable Long id) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        if (vehicle == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fuelDataService.getAllFuelDataByVehicleId(id));
    }

    /**
     * Get a fuel data entry by its ID.
     * HTTP GET /api/vehicles/{id}/fuel-data/{fuel_id}
     *
     * @param fuel_id the ID of the fuel data
     * @return the fuel data if found, 404 otherwise
     */
    @GetMapping("/{fuel_id}")
    public ResponseEntity<FuelData> getFuelDataById(@PathVariable("fuel_id") Long fuel_id) {
        FuelData data = fuelDataService.getFuelDataById(fuel_id);   //just returning this was showing 200 but with blank body
        if (data == null) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
        return ResponseEntity.ok(data);
    }

    /**
     * Get the total fuel usage for a vehicle.
     * HTTP GET /api/vehicles/{id}/fuel-data/totalfuel
     *
     * @param id the ID of the vehicle
     * @return total fuel used
     */
    @GetMapping("/totalfuel")
    public ResponseEntity<Double> getTotalFuelUsage(@PathVariable("id") Long id) {
        double totalFuelUsage = fuelDataService.getTotalFuelUsage(id);
        return ResponseEntity.ok(totalFuelUsage);
    }

    /**
     * Get the total distance traveled for a vehicle.
     * HTTP GET /api/vehicles/{id}/fuel-data/totaldistance
     *
     * @param id the ID of the vehicle
     * @return total distance traveled
     */
    @GetMapping("/totaldistance")
    public ResponseEntity<Double> getTotalDistance(@PathVariable("id") Long id) {
        double totalDistance = fuelDataService.getTotalDistance(id);
        return ResponseEntity.ok(totalDistance);
    }

    /**
     * Calculate the fuel efficiency for a vehicle.
     * HTTP GET /api/vehicles/{id}/fuel-data/fuelefficiency
     *
     * @param id the ID of the vehicle
     * @return fuel efficiency
     */
    @GetMapping("/fuelefficiency")
    public ResponseEntity<Double> calculateFuelEfficiency(@PathVariable("id") Long id) {
        double fuelefficiency = fuelDataService.calculateFuelEfficiency(id);
        return ResponseEntity.ok(fuelefficiency);
    }

    /**
     * Create a new fuel data entry for a vehicle.
     * HTTP POST /api/vehicles/{id}/fuel-data
     *
     * @param id the ID of the vehicle
     * @param fuelData the fuel data to create
     * @return the created fuel data with location header
     */
    @PostMapping
    public ResponseEntity<FuelData> createFuelData(@PathVariable Long id, @RequestBody FuelData fuelData) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        if (vehicle == null) {
            return ResponseEntity.notFound().build(); // Handle case where vehicle doesn't exist
        }
        fuelData.setVehicle(vehicle);

        FuelData saveFuelData = fuelDataService.saveFuelData(fuelData);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{fuel_id}")
                .buildAndExpand(saveFuelData.getFuelId())
                .toUri();

        return ResponseEntity.created(location).body(saveFuelData);
    }

    /**
     * Delete a fuel data entry by its ID.
     * HTTP DELETE /api/vehicles/{id}/fuel-data/{fuel_id}
     *
     * @param fuel_id the ID of the fuel data to delete
     * @return 204 No Content if deleted, 404 if not found
     */
    @DeleteMapping("/{fuel_id}")
    public ResponseEntity<Void> deleteFuelData(@PathVariable("fuel_id") Long fuel_id) {
        FuelData fuelData = fuelDataService.getFuelDataById(fuel_id);
        if (fuelData != null) {
            fuelDataService.deleteFuelData(fuel_id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    /**
     * Update a fuel data entry by its ID.
     * HTTP PUT /api/vehicles/{id}/fuel-data/{fuel_id}
     *
     * @param fuel_id the ID of the fuel data to update
     * @param fuelData the new fuel data details
     * @return the updated fuel data if found, 404 otherwise
     */
    @PutMapping("/{fuel_id}")
    public ResponseEntity<FuelData> updateFuelData(@PathVariable("fuel_id") Long fuel_id, @RequestBody FuelData fuelData) {
        FuelData updated = fuelDataService.updateFuelData(fuel_id, fuelData);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

}
