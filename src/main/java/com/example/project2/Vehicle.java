package com.example.project2;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "vehicles")
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="makeModel")
    private String makeModel;
    @Column(name="year")
    private int year;
    @Column(name="retailPrice")
    private double retailPrice;

    public Vehicle(){

    }

    public Vehicle(String makeModel, int year,
                   double retailPrice) {
        this.makeModel = makeModel;
        this.year = year;
        this.retailPrice = retailPrice;
    }

    public Vehicle(int id, String makeModel, int year,
                   double retailPrice) {
        this.id = id;
        this.makeModel = makeModel;
        this.year = year;
        this.retailPrice = retailPrice;
    }
    public int getID() {return id;}

    public String getMakeModel() {
        return makeModel;
    }

    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String toString() {
        return  this.getID() + ", " + this.makeModel + ", Year: " + this.year + ", Price: " + this.retailPrice;
    }
}
