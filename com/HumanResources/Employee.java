package com.HumanResources;
/**
 * Employee class_Define employee obj
 */
public class Employee extends Staff{

    private int overTime;
    //Getter
    public int GetOver() {
        return overTime;
    }
    //Setter
    public void SetOver(int input) {
        overTime = input;
    }

    @Override
    public void displayInformation() {
        // TODO Auto-generated method stub
        System.out.printf("| %2s", GetID());
        System.out.printf("| %10s", GetName());
        System.out.printf("| %10s", GetAge());
        System.out.printf("| %25s", GetWage());
        System.out.printf("| %30s", GetDate());
        System.out.printf("| %10s", GetDepartment().departmentName);
        System.out.printf("| %10s", GetDays());
        System.out.printf("| %10s", GetOver());
        System.out.printf("| %10s", "\n");
    }

    @Override
    public int calculateSalary() {
        // TODO Auto-generated method stub
        int salary;

        salary = GetWage() * 3000000 + overTime * 200000;

        return salary;
    }
}
