package com.Vehicleanalyzer.Services.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Vehicleanalyzer.Models.EngineData;
import com.Vehicleanalyzer.Repository.EngineRepository;
import com.Vehicleanalyzer.Services.EngineDataService;

// Implementation for EngineDataService
@Service
public class EngineDataServiceImp implements EngineDataService{

    private final EngineRepository engineRepository;

    public EngineDataServiceImp(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }

    
    @Override
    public List<EngineData> getEngineDataByVehicleId(Long vehicleId) {
        return engineRepository.findByVehicleid(vehicleId);
    }

    @Override
    public EngineData saveEngineData(EngineData engineData) {
        return engineRepository.save(engineData);
    }

    @Override
    public void deleteEngineData(Long id) {
        engineRepository.deleteById(id);
    }

    @Override
    public double getAverageRPM(Long vehicleId) {
        return engineRepository.getAverageRPMByVehicleId(vehicleId);
    }

    @Override
    public double getAverageTemperature(Long vehicleId) {
        return engineRepository.getAverageTemperatureByVehicleId(vehicleId);
    }

    @Override
    public EngineData getEngineDataById(Long id) {
        return engineRepository.findById(id).orElse(null);
    }


    @Override
    public EngineData updateEngineData(Long engineId, EngineData engineDataDetails) {
        EngineData existingEngineData = engineRepository.findById(engineId).orElse(null);

        if (existingEngineData != null) {
            existingEngineData.setRPM(engineDataDetails.getRPM());
            existingEngineData.setTemperature(engineDataDetails.getTemperature());
            existingEngineData.setTimestamp(engineDataDetails.getTimestamp());
            return engineRepository.save(existingEngineData);
        }

        return null;
    }

}
