package com.HumanResources;
/**
 * Manager class_Define manager obj
 */
public class Manager extends Staff implements ICalculator{

    String role;

    @Override
    public int calculateSalary() {
        // TODO Auto-generated method stub
        int salary;
        if (role.equals("Business Leader")) {
            salary = wage * 5000000 + 8000000;
        }
        else if (role.equals("Project Leader")) {
            salary = wage * 5000000 + 5000000;
        }
        else if (role.equals("Technical Leader")) {
            salary = wage * 5000000 + 6000000;
        }
        else{
            salary = wage * 5000000;
        }

        return salary;
    }

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
        System.out.printf("| %10s", "");
        System.out.printf("| %10s", role+"\n");
    }
    
}
