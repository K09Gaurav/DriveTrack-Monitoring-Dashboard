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
@Table(name = "fuelData")
public class FuelData {

    // Unique id for the fuel data
    @Id
    @GeneratedValue
    private Long fuelId;

    // The car this fuel data belongs to
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    // How much fuel was used
    private double liters_used;
    // How far the car went
    private double distance_km;
    
    // When this fuel data was recorded
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    public FuelData(Vehicle vehicle, float liters_used, float distance_km, LocalDateTime timestamp) {
        this.vehicle = vehicle;
        this.liters_used = liters_used;
        this.distance_km = distance_km;
        this.timestamp = timestamp;
    }

    /**
     * This is a blank constructor. It's needed for JPA and frameworks to create objects.
     */
    public FuelData() {
    }

    /**
     * Getters and setters for all fields below
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    public double getLiters_used() {
        return liters_used;
    }

    public void setLiters_used(double liters_used) {
        this.liters_used = liters_used;
    }

    public double getdistance_km() {
        return distance_km;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Long getFuelId() {
        return fuelId;
    }

    @Override
    public String toString() {
        return "FuelData [fuelId=" + fuelId + ", vehicle=" + vehicle + ", liters_used=" + liters_used + ", distance_km="
                + distance_km + ", timestamp=" + timestamp + "]";
    }

    public void setFuelId(Long fuelId) {
        this.fuelId = fuelId;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setDistance_km(double distance_km) {
        this.distance_km = distance_km;
    }

}
