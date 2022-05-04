package com.HumanResources;
/**
 * Employee class_Define employee obj
 */
public class Employee extends Staff implements ICalculator{

    int overTime;

    @Override
    public void displayInformation() {
        // TODO Auto-generated method stub
        System.out.printf("| %2s", id);
        System.out.printf("| %10s", name);
        System.out.printf("| %10s", age);
        System.out.printf("| %25s", wage);
        System.out.printf("| %30s", startedDay);
        System.out.printf("| %10s", department.departmentName);
        System.out.printf("| %10s", offDays);
        System.out.printf("| %10s", overTime);
        System.out.printf("| %10s", "\n");
    }

    @Override
    public int calculateSalary() {
        // TODO Auto-generated method stub
        int salary;

        salary = wage * 3000000 + overTime * 200000;

        return salary;
    }
}
