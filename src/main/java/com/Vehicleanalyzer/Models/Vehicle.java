package com.Vehicleanalyzer.Models;

import java.util.ArrayList;
import java.util.List;

import com.Vehicleanalyzer.Models.enums.EngineType;
import com.Vehicleanalyzer.Models.enums.FuelType;
import com.Vehicleanalyzer.Models.enums.VehicleTypes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    // Unique id for the car
    @Id
    @GeneratedValue
    private Long id;

    // Car company name
    private String Manufacturer;
    // Car model name
    private String Model;
    // Year the car was made
    private int year_of_Mfg;

    // Type of car (like SUV, Sedan, etc.)
    @Enumerated(EnumType.STRING)
    @Column(name = "Vehicle_Type")
    private VehicleTypes Type;

    // Type of engine (like Petrol, Diesel, etc.)
    @Enumerated(EnumType.STRING)
    @Column(name = "Engine_Type")
    private EngineType Engine_Type;

    // Type of fuel (like Petrol, Diesel, etc.)
    @Enumerated(EnumType.STRING)
    @Column(name = "Fuel_Type")
    private FuelType Fuel_Type;

    // List of fuel data for this car
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FuelData> fuelDataList = new ArrayList<>();

    // List of engine data for this car
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EngineData> engineDataList = new ArrayList<>();

    public Vehicle(String manufacturer, String model, int year_of_Mfg, VehicleTypes type, EngineType engine_Type,
            FuelType fuel_Type) {
        Manufacturer = manufacturer;
        Model = model;
        this.year_of_Mfg = year_of_Mfg;
        Type = type;
        Engine_Type = engine_Type;
        Fuel_Type = fuel_Type;
    }

    /**
     * This is a blank constructor. It's needed for JPA and frameworks to create objects.
     */
    public Vehicle() {
    }

    /**
     * Getters and setters for all fields below
     */
    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public int getYear_of_Mfg() {
        return year_of_Mfg;
    }

    public void setYear_of_Mfg(int year_of_Mfg) {
        this.year_of_Mfg = year_of_Mfg;
    }

    public VehicleTypes getType() {
        return Type;
    }

    public void setType(VehicleTypes type) {
        Type = type;
    }

    public EngineType getEngine_Type() {
        return Engine_Type;
    }

    public void setEngine_Type(EngineType engine_Type) {
        Engine_Type = engine_Type;
    }

    public FuelType getFuel_Type() {
        return Fuel_Type;
    }

    public void setFuel_Type(FuelType fuel_Type) {
        Fuel_Type = fuel_Type;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vehicle{");
        sb.append("id=").append(id);
        sb.append(", Manufacturer=").append(Manufacturer);
        sb.append(", Model=").append(Model);
        sb.append(", year_of_Mfg=").append(year_of_Mfg);
        sb.append(", Type=").append(Type);
        sb.append(", Engine_Type=").append(Engine_Type);
        sb.append(", Fuel_Type=").append(Fuel_Type);
        sb.append('}');
        return sb.toString();
    }

    public void setId(Long id) {
        this.id = id;
    }

}
