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

import com.Vehicleanalyzer.Models.Vehicle;
import com.Vehicleanalyzer.Services.VehicleService;

/**
 * Controller for managing vehicle data.
 * Base route: /api/vehicles
 * Handles CRUD operations for vehicles.
 */
@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // @PostConstruct /// Controller detect nahi hora bhai
    // public void init() {
    //     System.out.println("VehicleController initialized ");
    // }
    //order : get - post - put -delete 

    
    /**
     * Get all vehicles.
     * HTTP GET /api/vehicles
     *
     * @return list of all vehicles
     */
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    /**
     * Get a vehicle by its ID.
     * HTTP GET /api/vehicles/{id}
     *
     * @param id the ID of the vehicle
     * @return the vehicle if found, 404 otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        Vehicle existing = vehicleService.getVehicleById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(existing);
    }

    /**
     * Create a new vehicle.
     * HTTP POST /api/vehicles
     *
     * @param vehicle the vehicle to create
     * @return the created vehicle with location header
     */
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        Vehicle saved = vehicleService.saveVehicle(vehicle);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest() //POST should return 201 (not 200) aisa 2-3 ai tools bole jaab code review ka pucha to
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    /**
     * Update an existing vehicle by ID.
     * HTTP PUT /api/vehicles/{id}
     *
     * @param id the ID of the vehicle to update
     * @param vehicleDetails the new vehicle details
     * @return the updated vehicle if found, 404 otherwise
     */
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicleDetails) {

        if (vehicleService.getVehicleById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        Vehicle existing = vehicleService.updateVehicle(id, vehicleDetails);
        return ResponseEntity.ok(existing);
    }

    /**
     * Delete a vehicle by ID.
     * HTTP DELETE /api/vehicles/{id}
     *
     * @param id the ID of the vehicle to delete
     * @return 204 No Content if deleted, 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        Vehicle existing = vehicleService.getVehicleById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

}
