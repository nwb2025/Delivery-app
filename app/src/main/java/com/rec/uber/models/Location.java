package com.rec.uber.models;

import com.google.android.gms.maps.model.LatLng;

/**
 * Location Object used to know pickup and destination location
 */
public class Location {

    private LatLng coordinates;
    private String name = "";

    /**
     * LocationObject constructor
     * @param coordinates - latLng of the location
     * @param name - name of the location
     */
    public Location(LatLng coordinates, String name){
        this.coordinates = coordinates;
        this.name = name;
    }

    /**
     * LocationObject constructor
     * Creates an empty object
     */
    public Location(){
    }


    public LatLng getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(LatLng coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
