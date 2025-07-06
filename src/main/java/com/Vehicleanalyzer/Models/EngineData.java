package com.Vehicleanalyzer.Models;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "engineData")
public class EngineData {

    // Unique id for the engine data
    @Id
    @GeneratedValue
    private Long engineId;

    // The car this engine data belongs to
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    // How fast the engine is spinning
    private int RPM;
    // How hot the engine is
    private double  Temperature;

    // When this engine data was recorded
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    public EngineData(Vehicle vehicle, int RPM, double temperature, LocalDateTime timestamp) {
        this.vehicle = vehicle;
        this.RPM = RPM;
        this.Temperature = temperature;
        this.timestamp = timestamp;
    }

    /**
     * This is a blank constructor. It's needed for JPA and frameworks to create objects.
     */
    public EngineData() {
    }

    /**
     * Getters and setters for all fields below
     */
    public Long getEngineId() {
        return engineId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getRPM() {
        return RPM;
    }

    public void setRPM(int RPM) {
        this.RPM = RPM;
    }

    public double  getTemperature() {
        return Temperature;
    }

    public void setTemperature(double temperature) {
        this.Temperature = temperature;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "EngineData [engineId=" + engineId + ", vehicle=" + vehicle + ", RPM=" + RPM + ", Temperature="
                + Temperature + ", timestamp=" + timestamp + "]";
    }

    public void setEngineId(Long engineId) {
        this.engineId = engineId;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    

}
