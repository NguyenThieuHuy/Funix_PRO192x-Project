package com.HumanResources;
import java.util.*;
/**
 * Staff abstract class_Construction of Employee and Manager
 */
public abstract class Staff {
    int id;
    String name;
    int age;
    int wage;
    Date startedDay;
    Department department;
    int offDays;

    //Abstract method_Displaying staff info
    public abstract void displayInformation();

}
