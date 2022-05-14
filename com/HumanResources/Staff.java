package com.HumanResources;
import java.util.*;
/**
 * Staff abstract class_Construction of Employee and Manager
 */
public abstract class Staff  implements ICalculator{

    private int id;
    private String name;
    private int age;
    private int wage;
    private Date startedDay;
    private Department department;
    private int offDays;

    //Getter
    public int GetID() {
        return id;
    }
    public String GetName() {
        return name;
    }
    public int GetAge() {
        return age;
    }
    public int GetWage() {
        return wage;
    }
    public Date GetDate() {
        return startedDay;
    }
    public Department GetDepartment() {
        return department;
    }
    public int GetDays() {
        return offDays;
    }

    //Setter
    public void SetID(int input) {
        id = input;
    }
    public void SetName(String input) {
        name = input;
    }
    public void SetAge(int input) {
        age = input;
    }
    public void SetWage(int input) {
        wage = input;
    }
    public void SetDate(Date input) {
        startedDay = input;
    }
    public void SetDepartment(Department input) {
        department = input;
    }
    public void SetDays(int input) {
        offDays = input;
    }



    //Abstract method_Displaying staff info
    public abstract void displayInformation();

}
