package com.HumanResources;
import java.io.*;
import java.text.*;
import java.util.*;
/**
 * HumanResources_main flow and function of the program
 */
public class HumanResources {

    //Arraylist for storing objs
    static List<Department> departments = new ArrayList<>();
    static List<Staff> staffs = new ArrayList<>();

    //Read Data from CSV file
    public static void CSVreader() throws ParseException{
        
        String path = "CSV/employees.csv";
        String row = "";

        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(path));
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                // do something with the data
                if (data[7].isEmpty()) {
                    Employee employee = new Employee();
                    
                    employee.SetID(Integer.parseInt(data[0]));
                    employee.SetName(data[1]);
                    employee.SetAge(Integer.parseInt(data[2]));
                    employee.SetWage(Integer.parseInt(data[3]));
        
                    String dateInString = data[4];
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
                    Date date = sdf.parse(dateInString);
                    employee.SetDate(date);
        
                    Boolean checker = false;
                    for (Department d : departments) {
                        if (d.departmentName.equals(data[5])) {
                            checker = true;
                            employee.SetDepartment(d);
                        }
                    }
                    if (checker == false) {
                        Department department = new Department();
                        department.departmentId = departments.size() + 1;
                        department.departmentName = data[5];
        
                        departments.add(department);
                        employee.SetDepartment(department);
                    }
        
                    employee.SetDays(Integer.parseInt(data[6]));

                    employee.SetOver(Integer.parseInt(data[8]));
        
                    staffs.add(employee);
                }else{
                    Manager manager = new Manager();
                    manager.SetID(Integer.parseInt(data[0]));        
                    manager.SetName(data[1]);
                    manager.SetAge(Integer.parseInt(data[2]));
                    manager.SetWage(Integer.parseInt(data[3]));
        
                    String dateInString = data[4];
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
                    Date date = sdf.parse(dateInString);
                    manager.SetDate(date);
        
                    Boolean checker = false;
                    for (Department d : departments) {
                        if (d.departmentName.equals(data[5])) {
                            checker = true;
                            manager.SetDepartment(d);
                        }
                    }
                    if (checker == false) {
                        Department department = new Department();
                        department.departmentId = departments.size() + 1;
                        department.departmentName = data[5];
        
                        departments.add(department);
                        manager.SetDepartment(department);
                    }
        
                    manager.SetDays(Integer.parseInt(data[6]));
                    
                    manager.SetRole(data[7]);
        
                    staffs.add(manager);
                }
            }
            csvReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //Save Data to CSV file
    public static void CSVwriter() throws IOException {
        FileWriter csvWriter = new FileWriter("CSV/employees.csv");
        for (Staff staff : staffs) {
            csvWriter.append(Integer.toString(staff.GetID()));
            csvWriter.append(",");
            csvWriter.append(staff.GetName());
            csvWriter.append(",");
            csvWriter.append(Integer.toString(staff.GetAge()));
            csvWriter.append(",");
            csvWriter.append(Integer.toString(staff.GetWage()));
            csvWriter.append(",");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
            String date = sdf.format(staff.GetDate());  
            csvWriter.append(date);
            csvWriter.append(",");
            csvWriter.append(staff.GetDepartment().departmentName);
            csvWriter.append(",");
            csvWriter.append(Integer.toString(staff.GetDays()));
            csvWriter.append(",");
            if (staff.getClass() == Manager.class) {
                csvWriter.append(((Manager) staff).GetRole());
            } else {
                csvWriter.append("");
            }
            csvWriter.append(",");
            if (staff.getClass() == Employee.class) {
                csvWriter.append(Integer.toString(((Employee) staff).GetOver()));
            } else {
                csvWriter.append("0");
            }
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
    }

    //Display staffs info from Arraylist
    public static void displayAllStaffs() {
        System.out.printf("| %2s","ID");
        System.out.printf("| %10s", "Name");
        System.out.printf("| %10s", "Age");
        System.out.printf("| %25s", "Coefficients salary");
        System.out.printf("| %30s", "Start date(yyyy-M-dd)");
        System.out.printf("| %10s", "Department");
        System.out.printf("| %10s", "Leave days");
        System.out.printf("| %10s", "Overtime");
        System.out.printf("| %10s", "Role\n");
        for (Staff staff : staffs) {
            staff.displayInformation();
        }
    }

    //Display departments info from Arraylist
    public static void displayAllDepartments() {
        System.out.printf("| %2s","ID");
        System.out.printf("| %10s", "Name");
        System.out.printf("| %10s", "Members\n");
        for (Department de : departments) {
            de.memberNum = 0;
            for (Staff staff : staffs) {
                if (staff.GetDepartment() == de) {
                    de.memberNum++;
                }
            }
            de.displayDepartments();

        }
    }

    //Sort and display staffs info by department
    public static void sortStaffsByDepartments() {
        System.out.printf("| %2s","ID");
        System.out.printf("| %10s", "Name");
        System.out.printf("| %10s", "Age");
        System.out.printf("| %25s", "Coefficients salary");
        System.out.printf("| %30s", "Start date(yyyy-M-dd)");
        System.out.printf("| %10s", "Department");
        System.out.printf("| %10s", "Leave days");
        System.out.printf("| %10s", "Overtime");
        System.out.printf("| %10s", "Role\n");
        for (Department de : departments) {
            System.out.println(de.departmentId+" "+de.departmentName);
            for (Staff staff : staffs) {
                if (staff.GetDepartment() == de) {
                    staff.displayInformation();
                }
            }
        }
    }

    //Create new staff obj and add to Arraylist
    public static void newEmployee() throws ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nIs this employee a manager?(True/False)");
        boolean isManager = sc.nextBoolean();
        if (isManager == true) {
            Manager manager = new Manager();

            manager.SetID(staffs.size()+1);
            System.out.println("ID: " + manager.GetID());

            System.out.print("Name: ");
            manager.SetName(sc.next());

            System.out.print("Age: ");
            manager.SetAge(sc.nextInt());

            System.out.print("Coefficients salary: ");
            manager.SetWage(sc.nextInt());

            System.out.println("Start date(yyyy-M-dd): ");
            String dateInString = sc.next();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
            Date date = sdf.parse(dateInString);
            manager.SetDate(date);

            System.out.println("Department: ");
            String de = sc.next();
            Boolean checker = false;
            for (Department d : departments) {
                if (d.departmentName.equals(de)) {
                    checker = true;
                    manager.SetDepartment(d);
                }
            }
            if (checker == false) {
                Department department = new Department();
                department.departmentId = departments.size() + 1;
                department.departmentName = de;

                departments.add(department);
                manager.SetDepartment(department);
            }

            System.out.println("Paid leave days: ");
            manager.SetDays(sc.nextInt());
            
            System.out.println("Role(Business_Leader, Project_Leader, Technical_Leader): ");
            manager.SetRole(sc.next());

            staffs.add(manager);
        }else{
            Employee employee = new Employee();
            
            employee.SetID(staffs.size()+1);
            System.out.println("ID: " + employee.GetID());

            System.out.print("Name: ");
            employee.SetName(sc.next());

            System.out.print("Age: ");
            employee.SetAge(sc.nextInt());

            System.out.print("Coefficients salary: ");
            employee.SetWage(sc.nextInt());

            System.out.println("Start date(yyyy-M-dd): ");
            String dateInString = sc.next();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
            Date date = sdf.parse(dateInString);
            employee.SetDate(date);

            System.out.println("Department: ");
            String de = sc.next();
            Boolean checker = false;
            for (Department d : departments) {
                if (d.departmentName.equals(de)) {
                    checker = true;
                    employee.SetDepartment(d);
                }
            }
            if (checker == false) {
                Department department = new Department();
                department.departmentId = departments.size() + 1;
                department.departmentName = de;

                departments.add(department);
                employee.SetDepartment(department);
            }

            System.out.println("Paid leave days: ");
            employee.SetDays(sc.nextInt());

            System.out.println("Overtime: ");
            employee.SetOver(sc.nextInt());

            staffs.add(employee);
        }

    }

    //Search and display staff info by name or id
    public static void search(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Search by employee name or id");
        String input = sc.next();

        System.out.printf("| %2s","ID");
        System.out.printf("| %10s", "Name");
        System.out.printf("| %10s", "Age");
        System.out.printf("| %25s", "Coefficients salary");
        System.out.printf("| %30s", "Start date(yyyy-M-dd)");
        System.out.printf("| %10s", "Department");
        System.out.printf("| %10s", "Leave days");
        System.out.printf("| %10s", "Role\n");

        try{
            int id = Integer.parseInt(input);
            for (Staff s : staffs) {
                if (s.GetID() == id) {
                    s.displayInformation();
                }
            }
        }
        catch (NumberFormatException ex){
            for (Staff s : staffs) {
                if (s.GetName().equals(input)) {
                    s.displayInformation();
                }
            }
        }


    }

    //Calculate and display staff salary
    public static void displayAllStaffsPayroll() {

        System.out.printf("| %2s","ID");
        System.out.printf("| %10s", "Name");
        System.out.printf("| %15s", "Salary\n");

        for (Staff s : staffs) {
            System.out.printf("| %2s",s.GetID());
            System.out.printf("| %10s", s.GetName());
            System.out.printf("| %15s", s.calculateSalary()+"\n");
        }

    }

    //Sort salary of staffs descending and ascending
    public static void sortPayroll() {
        Comparator<Staff> Comparator
         = (c1, c2) -> (int) (c2.calculateSalary() - c1.calculateSalary());

        staffs.sort(Comparator);

        displayAllStaffsPayroll();
    }
    public static void sortPayrollReverse() {
        Comparator<Staff> Comparator
         = (c1, c2) -> (int) (c1.calculateSalary() - c2.calculateSalary());

        staffs.sort(Comparator);

        displayAllStaffsPayroll();
    }

    //Main flow of methods
    public static void main(String[] args) throws ParseException, IOException {
        CSVreader();
        Boolean isRunning = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n1. Display the list of existing employees in the company\n2. Show parts of the company\n3. Display employees by department\n4. Add new employees to the company: including 2 types\n- Add regular staff \n- Add employees who are management levels (with additional positions)\n5. Search for employee information by employee's name or code\n6. Display the payroll of employees throughout the company\n7. Display employee payroll in ascending order\n8. Display employee payroll in descending order\n9. Close program and save file");
        do {
            System.out.print("\nChoose what you want to do from above options: ");
            isRunning = true;
            int method = sc.nextInt();
            switch (method) {
                default:
                    System.out.println("\nYour choice is invalid!!\nPlease choose again.");
                    isRunning = false;
                    break;
                case 1:
                    displayAllStaffs();
                    isRunning = false;
                    break;
                case 2:
                    displayAllDepartments();
                    isRunning = false;
                    break;
                case 3:
                    sortStaffsByDepartments();
                    isRunning = false;
                    break;
                case 4:
                    newEmployee();
                    isRunning = false;
                    break;
                case 5:
                    search();
                    isRunning = false;
                    break;
                case 6:
                    displayAllStaffsPayroll();
                    isRunning = false;
                    break;
                case 7:
                    sortPayroll();
                    isRunning = false;
                    break;
                case 8:
                    sortPayrollReverse();
                    isRunning = false;
                    break;
                case 9:
                    isRunning = true;
            }
        } while (isRunning == false);
        CSVwriter();

    }
}