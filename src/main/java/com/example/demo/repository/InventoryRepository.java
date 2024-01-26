package com.example.demo.repository;

import com.example.demo.model.Guitar;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InventoryRepository {
    private List<Guitar> guitars;

    public InventoryRepository() {
        guitars = new ArrayList<>();
        try {
            FileWriter writer = new FileWriter("guitars_database.txt", false);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addGuitar(String serialNumber, double price, String builder, String model, String type, String backWood, String topWood) {
        /**
         * takes variables and appends guitar to the list and file.
         */
        if (getGuitar(serialNumber) != null) {
            // cannot add the same serial number twice
            return;
        }
        Guitar guitar = new Guitar(serialNumber, price, builder, model, type, backWood, topWood);
        guitars.add(guitar);
        try {
            FileWriter writer = new FileWriter("guitars_database.txt", true);
            writer.write(serialNumber + "," + price + "," + builder + "," + model + "," + type + "," + backWood + "," + topWood + "\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Guitar getGuitar(String serialNumber) {
        /**
         * checks if a guitar of a given serial number exists, returns null otherwise
         */
        for (Guitar guitar : guitars) {
            if (guitar.getSerialNumber().equals(serialNumber)) {
                return guitar;
            }
        }
        return null;
    }

    public List<Guitar> search(Guitar searchGuitar) {
        /**
         * checks if a given guitar exists
         *
         */
        List<Guitar> searchGuitars = new ArrayList<>();
        for (Guitar guitar : guitars) {
            if(searchGuitar.getBuilder()!=null && searchGuitar.getBuilder().equals(guitar.getSerialNumber())){
                continue;
            }
            if(searchGuitar.getPrice()!=null && searchGuitar.getPrice().equals(guitar.getPrice())){
                continue;
            }
            if(searchGuitar.getModel()!=null && searchGuitar.getModel().equals(guitar.getModel())){
                continue;
            }
            if(searchGuitar.getType()!=null && searchGuitar.getType().equals(guitar.getType())){
                continue;
            }
            if(searchGuitar.getBackWood()!=null && searchGuitar.getType().equals(guitar.getBackWood())){
                continue;
            }
            if(searchGuitar.getTopWood()!=null && searchGuitar.getType().equals(guitar.getTopWood())){
                continue;
            }
            guitars.add(guitar);
        }
        return searchGuitars;
    }

}