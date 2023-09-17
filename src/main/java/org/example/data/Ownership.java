package org.example.data;

public class Ownership {
    private int id;
    private String name;
    private int flatNumber;
    private int numberOfRooms;
    private int square;
    private String street;
    private int buildingNumber;
    private int tel;
    private String eMail;

    public int getId() {
        return id;
    }

    Ownership setId(final int newValue) {
        id = newValue;
        return this;
    }

    public String getName() {
        return name;
    }

    Ownership setName(final String newValue) {
        name = newValue;
        return this;
    }

    public int getFlatNumber() {
        return flatNumber;
    }

    Ownership setFlatNumber(final int newValue) {
        flatNumber = newValue;
        return this;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    Ownership setNumberOfRooms(final int newValue) {
        numberOfRooms = newValue;
        return this;
    }

    public int getSquare() {
        return square;
    }

    Ownership setSquare(final int newValue) {
        square = newValue;
        return this;
    }

    public String getStreet() {
        return street;
    }

    Ownership setStreet(final String newValue) {
        street = newValue;
        return this;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    Ownership setBuildingNumber(final int newValue) {
        buildingNumber = newValue;
        return this;
    }

    public int getTel() {
        return tel;
    }

    Ownership setTel(final int newValue) {
        tel = newValue;
        return this;
    }

    public String getEMail() {
        return eMail;
    }

    Ownership setEMail(final String newValue) {
        eMail = newValue;
        return this;
    }
}
