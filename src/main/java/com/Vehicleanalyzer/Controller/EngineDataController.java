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

import com.Vehicleanalyzer.Models.EngineData;
import com.Vehicleanalyzer.Models.Vehicle;
import com.Vehicleanalyzer.Services.EngineDataService;
import com.Vehicleanalyzer.Services.VehicleService;

/**
 * Controller for managing engine data for vehicles.
 * Base route: /api/vehicles/{id}/engine-data
 * Handles CRUD operations and analytics for engine data.
 */
@RestController
@RequestMapping("/api/vehicles/{id}/engine-data")
public class EngineDataController {

    private final EngineDataService engineDataService;
    private final VehicleService vehicleService;

    public EngineDataController(EngineDataService engineDataService, VehicleService vehicleService) {
        this.engineDataService = engineDataService;
        this.vehicleService = vehicleService;
    }

    /**
     * Get all engine data for a vehicle.
     * HTTP GET /api/vehicles/{id}/engine-data
     *
     * @param id the ID of the vehicle
     * @return list of engine data
     */
    @GetMapping()
    public ResponseEntity<List<EngineData>> getAllEngineDataVehicleId(@PathVariable Long id) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        if (vehicle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(engineDataService.getEngineDataByVehicleId(id));
    }

    /**
     * Get an engine data entry by its ID.
     * HTTP GET /api/vehicles/{id}/engine-data/{engine_id}
     *
     * @param engine_id the ID of the engine data
     * @return the engine data if found, 404 otherwise
     */
    @GetMapping("/{engine_id}")
    public ResponseEntity<EngineData> getEngineDataById(@PathVariable("engine_id") Long engine_id) {
        EngineData data = engineDataService.getEngineDataById(engine_id);   //just returning this was showing 200 but with blank body
        if (data == null) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
        return ResponseEntity.ok(data);
    }

    /**
     * Get the average RPM for a vehicle.
     * HTTP GET /api/vehicles/{id}/engine-data/Avg_RPM
     *
     * @param id the ID of the vehicle
     * @return average RPM
     */
    @GetMapping("/Avg_RPM")
    public ResponseEntity<Double> getAverageRPM(@PathVariable("id") Long id) {
        Double RPM = engineDataService.getAverageRPM(id);
        return ResponseEntity.ok(RPM);
    }

    /**
     * Get the average temperature for a vehicle.
     * HTTP GET /api/vehicles/{id}/engine-data/Avg_Temp
     *
     * @param id the ID of the vehicle
     * @return average temperature
     */
    @GetMapping("/Avg_Temp")
    public ResponseEntity<Double> getAverageTemp(@PathVariable("id") Long id) {
        Double Temp = engineDataService.getAverageTemperature(id);
        return ResponseEntity.ok(Temp);
    }

    /**
     * Create a new engine data entry for a vehicle.
     * HTTP POST /api/vehicles/{id}/engine-data
     *
     * @param id the ID of the vehicle
     * @param engineData the engine data to create
     * @return the created engine data with location header
     */
    @PostMapping
    public ResponseEntity<EngineData> createEngineData(@PathVariable Long id, @RequestBody EngineData engineData) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        if (vehicle == null) {
            return ResponseEntity.notFound().build(); // Handle case where vehicle doesn't exist
        }
        engineData.setVehicle(vehicle);

        EngineData saveEngineData = engineDataService.saveEngineData(engineData);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{engine_id}")
                .buildAndExpand(saveEngineData.getEngineId())
                .toUri();

        return ResponseEntity.created(location).body(saveEngineData);
    }

    /**
     * Delete an engine data entry by its ID.
     * HTTP DELETE /api/vehicles/{id}/engine-data/{engine_id}
     *
     * @param engine_id the ID of the engine data to delete
     * @return 204 No Content if deleted, 404 if not found
     */
    @DeleteMapping("/{engine_id}")
    public ResponseEntity<Void> deleteEngineData(@PathVariable("engine_id") Long engine_id) {
        EngineData engineData = engineDataService.getEngineDataById(engine_id);
        if (engineData != null) {
            engineDataService.deleteEngineData(engine_id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    /**
     * Update an engine data entry by its ID.
     * HTTP PUT /api/vehicles/{id}/engine-data/{engine_id}
     *
     * @param engine_id the ID of the engine data to update
     * @param engineData the new engine data details
     * @return the updated engine data if found, 404 otherwise
     */
    @PutMapping("/{engine_id}")
    public ResponseEntity<EngineData> updateEmgineData(@PathVariable("engine_id") Long engine_id, @RequestBody EngineData engineData) {
        EngineData updated = engineDataService.updateEngineData(engine_id, engineData);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

}
