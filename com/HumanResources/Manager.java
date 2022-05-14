package com.HumanResources;
/**
 * Manager class_Define manager obj
 */
public class Manager extends Staff{

    private String role;
    //Getter
    public String GetRole() {
        return role;
    }
    //Setter
    public void SetRole(String input) {
        role = input;
    }

    @Override
    public int calculateSalary() {
        // TODO Auto-generated method stub
        int salary;
        if (role.equals("Business_Leader")) {
            salary = GetWage() * 5000000 + 8000000;
        }
        else if (role.equals("Project_Leader")) {
            salary = GetWage() * 5000000 + 5000000;
        }
        else if (role.equals("Technical_Leader")) {
            salary = GetWage() * 5000000 + 6000000;
        }
        else{
            salary = GetWage() * 5000000;
        }

        return salary;
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
        System.out.printf("| %10s", "");
        System.out.printf("| %10s", GetRole()+"\n");
    }
    
}
