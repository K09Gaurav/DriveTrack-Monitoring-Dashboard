package com.Vehicleanalyzer.Services;

import java.util.List;

import com.Vehicleanalyzer.Models.EngineData;

/**
 * Service interface for managing engine data operations.
 */
public interface EngineDataService {

    /**
     * Get all engine data entries for a specific vehicle.
     *
     * @param vehicleId the ID of the vehicle
     * @return list of engine data
     */
    List<EngineData> getEngineDataByVehicleId(Long vehicleId);

    /**
     * Save a new engine data entry or update an existing one.
     *
     * @param engineData the engine data to save
     * @return the saved engine data
     */
    EngineData saveEngineData(EngineData engineData);

    /**
     * Delete an engine data entry by its ID.
     *
     * @param id the ID of the engine data to delete
     */
    void deleteEngineData(Long id);

    /**
     * Get the average RPM for a vehicle.
     *
     * @param vehicleId the ID of the vehicle
     * @return average RPM
     */
    double getAverageRPM(Long vehicleId);

    /**
     * Get the average temperature for a vehicle.
     *
     * @param vehicleId the ID of the vehicle
     * @return average temperature
     */
    double getAverageTemperature(Long vehicleId);

    /**
     * Retrieve an engine data entry by its ID.
     *
     * @param id the ID of the engine data
     * @return the engine data
     */
    EngineData getEngineDataById(Long id);

    /**
     * Update an engine data entry by its ID.
     *
     * @param engineId the ID of the engine data to update
     * @param engineDataDetails the new engine data details
     * @return the updated engine data
     */
    EngineData updateEngineData(Long engineId, EngineData engineDataDetails);
}
