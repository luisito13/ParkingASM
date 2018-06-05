package com.example.lluis.parkingasm;

import android.content.ContentValues;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;

public class ContentValuesUtils {

    public static ContentValues modelToContentValuesP(Parking parking){
        ContentValues cv = new ContentValues();
        try{
            cv.put(ModelContracts.ParkingContract.ID, parking.getId());
            cv.put(ModelContracts.ParkingContract.NAME, parking.getName());
            cv.put(ModelContracts.ParkingContract.COMPANY_NUMBER, parking.getCompany_number());
            cv.put(ModelContracts.ParkingContract.LOCATION_ID, parking.getLocation().getId());

        }catch (Exception e){
            System.out.println("There is an error");
        }
        return cv;
    }
    public static ContentValues modelToContentValuesF(Floor floor){
        ContentValues cv = new ContentValues();
        try{
            cv.put(ModelContracts.FloorContract.ID, floor.getId());
            cv.put(ModelContracts.FloorContract.NAME, floor.getName());
            cv.put(ModelContracts.FloorContract.COMPANY_NUMBER, floor.getCompany_number());
        }catch (Exception e){
            System.out.println("There is an error");
        }
        return cv;
    }
    public static ContentValues modelToContentValuesS(Slot slot, int floor_id){
        ContentValues cv = new ContentValues();
        try{
            cv.put(ModelContracts.SlotContract.ID,slot.getId());
            cv.put(ModelContracts.SlotContract.FLOOR_ID,floor_id);
            cv.put(ModelContracts.SlotContract.NAME,slot.getName());
            cv.put(ModelContracts.SlotContract.COMPANY_NUMBER,slot.getCompany_number());

        }catch (Exception e){
            System.out.println("There is an error");
        }
        return cv;
    }

    public static ContentValues modelToContentValuesL(Location location, String nom){
        ContentValues cv = new ContentValues();
        try{
            cv.put(ModelContracts.LocationContract.ID,location.getId());
            cv.put(ModelContracts.LocationContract.NAME,nom);
            cv.put(ModelContracts.LocationContract.LATITUDE,location.getLatitude());
            cv.put(ModelContracts.LocationContract.LONGITUDE,location.getLongitude());
            cv.put(ModelContracts.LocationContract.POSTAL_CODE,location.getPostal_code());
            cv.put(ModelContracts.LocationContract.STATE_PROVINCE,location.getState_province());
            cv.put(ModelContracts.LocationContract.STREET_ADDRESS,location.getStreet_address());

        }catch (Exception e){
            System.out.println("There is an error");
        }
        return cv;
    }
}