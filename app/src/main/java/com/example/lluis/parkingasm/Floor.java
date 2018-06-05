package com.example.lluis.parkingasm;

import java.util.List;

public class Floor {
    private int id;
    private int company_number;
    private String name;
    private List<Slot> slots;

    public Floor(int id,int company_number, String name, List<Slot> slots){
        this.id=id;
        this.company_number=company_number;
        this.name=name;
        this.slots=slots;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompany_number() {
        return company_number;
    }

    public void setCompany_number(int company_number) {
        this.company_number = company_number;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Floor{" +
                "id=" + id +
                ", company_number=" + company_number +
                ", name='" + name + '\'' +
                ", slots=" + slots +
                '}';
    }
}