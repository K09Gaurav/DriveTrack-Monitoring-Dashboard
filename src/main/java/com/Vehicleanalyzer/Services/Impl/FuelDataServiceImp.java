package com.Vehicleanalyzer.Services.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Vehicleanalyzer.Models.FuelData;
import com.Vehicleanalyzer.Repository.FuelDataRepository;
import com.Vehicleanalyzer.Services.FuelDataService;

// Implementation for FuelDataService
@Service
public class FuelDataServiceImp implements FuelDataService {

    private final FuelDataRepository fuelDataRepository;

    public FuelDataServiceImp(FuelDataRepository fuelDataRepository) {
        this.fuelDataRepository = fuelDataRepository;
    }

    @Override
    public List<FuelData> getAllFuelDataByVehicleId(Long vehicleid) {
        return fuelDataRepository.findByVehicle_id(vehicleid);
    }

    @Override
    public FuelData saveFuelData(FuelData fuelData) {
        return fuelDataRepository.save(fuelData);
    }

    @Override
    public void deleteFuelData(Long id) {
        fuelDataRepository.deleteById(id);
    }

    @Override
    public FuelData getFuelDataById(Long id) {
        return fuelDataRepository.findById(id).orElse(null);
    }

    @Override
    public Double getTotalFuelUsage(Long vehicleid) {
        Double total = fuelDataRepository.TotalFuelUsage(vehicleid);
        return total != null ? total : 0.0;
    }

    @Override
    public Double getTotalDistance(Long vehicleid) {
        Double total = fuelDataRepository.TotalDistance(vehicleid);
        return total != null ? total : 0.0;
    }

    @Override
    public Double calculateFuelEfficiency(Long vehicleid) {

        Double TotalDist = getTotalDistance(vehicleid);
        Double TotalFuel = getTotalFuelUsage(vehicleid);
        return TotalFuel != 0 ? (TotalDist / TotalFuel) : 0.0;

    }

    @Override
    public FuelData updateFuelData(Long fuelId, FuelData fuelDataDetails) {
        FuelData existingFuelData = fuelDataRepository.findById(fuelId).orElse(null);

        if (existingFuelData != null) {
            existingFuelData.setLiters_used(fuelDataDetails.getLiters_used());
            existingFuelData.setDistance_km(fuelDataDetails.getdistance_km());
            existingFuelData.setTimestamp(fuelDataDetails.getTimestamp());
            return fuelDataRepository.save(existingFuelData);
        }

        return null;
    }

}
