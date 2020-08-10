package com.solutions.backend.model;

import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pharmacies")
public class Pharmacy {

    @Setter
    private long id;

    @Setter
    private String name;

    @Setter
    private String address;

    @Setter
    private String city;

    @Setter
    private String state;

    @Setter
    private String zip;

    @Setter
    private double latitude;

    @Setter
    private double longitude;

    public Pharmacy() {}

    public Pharmacy(String name, String address, String city, String state, String zip, double latitude, double longitude) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    @Column(name = "address", nullable = false)
    public String getAddress() {
        return address;
    }

    @Column(name = "city", nullable = false)
    public String getCity() {
        return city;
    }

    @Column(name = "state", nullable = false)
    public String getState() {
        return state;
    }

    @Column(name = "zip", nullable = false)
    public String getZip() {
        return zip;
    }

    @Column(name = "latitude", nullable = false)
    public double getLatitude() {
        return latitude;
    }

    @Column(name = "longitude", nullable = false)
    public double getLongitude() {
        return longitude;
    }

    public static double distance(double lat, double otherLat, double lon, double otherLon) {
        if ((lat == otherLat) && (lon == otherLon)) {
            return 0;
        } else {
            double theta = lon - otherLon;
            double dist = Math.sin(Math.toRadians(lat)) * Math.sin(Math.toRadians(otherLat)) + Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(otherLat)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            return (dist);
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Pharmacy {");
        sb.append("name='").append(name).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append(", zip='").append(zip).append('\'');
        sb.append('}');

        return sb.toString();
    }
}
