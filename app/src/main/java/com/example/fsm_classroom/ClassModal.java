package com.example.fsm_classroom;

public class ClassModal {

    // variables for our classname,
    // description, tracks and duration, id.
    private String classNo;
    private String building;
    private String capacity;
    private String classDescription;
    private int id;

    // creating getter and setter methods
    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getClassDescription() {
        return classDescription;
    }

    public void setClassDescription(String classDescription) {
        this.classDescription = classDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // constructor
    public ClassModal(String classNo, String building, String capacity, String classDescription) {
        this.classNo = classNo;
        this.building = building;
        this.capacity = capacity;
        this.classDescription = classDescription;
    }
}
