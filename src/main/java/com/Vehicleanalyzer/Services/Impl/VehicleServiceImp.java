package com.Vehicleanalyzer.Services.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Vehicleanalyzer.Models.Vehicle;
import com.Vehicleanalyzer.Repository.VehicleRepository;
import com.Vehicleanalyzer.Services.VehicleService;

// Implementation for VehicleService
@Service
public class VehicleServiceImp implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImp(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }


    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public Vehicle updateVehicle(Long id, Vehicle vehicleDetails) {
        Vehicle existingVehicle = vehicleRepository.findById(id).orElse(null);

        if (existingVehicle != null){
            //will do all the updating here
            if (vehicleDetails.getManufacturer() != null)
            existingVehicle.setManufacturer(vehicleDetails.getManufacturer());

            if (vehicleDetails.getModel() != null)
                existingVehicle.setModel(vehicleDetails.getModel());

            if (vehicleDetails.getYear_of_Mfg() != 0)
                existingVehicle.setYear_of_Mfg(vehicleDetails.getYear_of_Mfg());

            if (vehicleDetails.getType() != null)
                existingVehicle.setType(vehicleDetails.getType());

            if (vehicleDetails.getEngine_Type() != null)
                existingVehicle.setEngine_Type(vehicleDetails.getEngine_Type());

            if (vehicleDetails.getFuel_Type() != null)
                existingVehicle.setFuel_Type(vehicleDetails.getFuel_Type());
            
            existingVehicle = saveVehicle(existingVehicle);
            return existingVehicle;
        }
        
        
        return null;
    }
    
}
