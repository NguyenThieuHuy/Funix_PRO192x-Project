package com.HumanResources;
/**
 * Department class_Define department obj
 */
public class Department {
    int departmentId;
    String departmentName;
    int memberNum;

    public String toString() {
        return "ID: " + departmentId + "  Name: " + departmentName + " Members: " + memberNum;
    }
    public void displayDepartments() {
        System.out.printf("| %2s",departmentId);
        System.out.printf("| %10s", departmentName);
        System.out.printf("| %10s", memberNum+"\n");
    }

}
