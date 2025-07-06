package com.Vehicleanalyzer.Services;

import java.util.List;

import com.Vehicleanalyzer.Models.Vehicle;

/**
 * Service interface for managing vehicle data operations.
 */
public interface VehicleService {

    /**
     * Fetch all vehicle data entries.
     *
     * @return list of all vehicles
     */
    List<Vehicle> getAllVehicles();

    /**
     * Retrieve a vehicle's data by its ID.
     *
     * @param id the ID of the vehicle
     * @return the vehicle data
     */
    Vehicle getVehicleById(Long id);

    /**
     * Save a new vehicle or update an existing one.
     *
     * @param vehicle the vehicle to save
     * @return the saved vehicle
     */
    Vehicle saveVehicle(Vehicle vehicle);

    /**
     * Delete a vehicle by its ID.
     *
     * @param id the ID of the vehicle to delete
     */
    void deleteVehicle(Long id);

    /**
     * Update a vehicle's details by its ID.
     *
     * @param id the ID of the vehicle to update
     * @param vehicleDetails the new vehicle details
     * @return the updated vehicle
     */
    Vehicle updateVehicle(Long id, Vehicle vehicleDetails);
}
