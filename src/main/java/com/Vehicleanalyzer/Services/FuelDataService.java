package com.Vehicleanalyzer.Services;

import java.util.List;

import com.Vehicleanalyzer.Models.FuelData;

/**
 * Service interface for managing fuel data operations.
 */
public interface FuelDataService {

    /**
     * Get all fuel data entries for a specific vehicle.
     *
     * @param vehicleId the ID of the vehicle
     * @return list of fuel data
     */
    List<FuelData> getAllFuelDataByVehicleId(Long vehicleId);

    /**
     * Retrieve a fuel data entry by its ID.
     *
     * @param id the ID of the fuel data
     * @return the fuel data
     */
    FuelData getFuelDataById(Long id);

    /**
     * Save a new fuel data entry or update an existing one.
     *
     * @param fuelData the fuel data to save
     * @return the saved fuel data
     */
    FuelData saveFuelData(FuelData fuelData);

    /**
     * Delete a fuel data entry by its ID.
     *
     * @param id the ID of the fuel data to delete
     */
    void deleteFuelData(Long id);

    /**
     * Update a fuel data entry by its ID.
     *
     * @param fuelId the ID of the fuel data to update
     * @param fuelDataDetails the new fuel data details
     * @return the updated fuel data
     */
    FuelData updateFuelData(Long fuelId, FuelData fuelDataDetails);

    /**
     * Get the total fuel usage for a vehicle.
     *
     * @param vehicleId the ID of the vehicle
     * @return total fuel used
     */
    Double getTotalFuelUsage(Long vehicleId);

    /**
     * Get the total distance traveled for a vehicle.
     *
     * @param vehicleId the ID of the vehicle
     * @return total distance traveled
     */
    Double getTotalDistance(Long vehicleId);

    /**
     * Calculate the fuel efficiency for a vehicle.
     *
     * @param vehicleId the ID of the vehicle
     * @return fuel efficiency
     */
    Double calculateFuelEfficiency(Long vehicleId);
}
