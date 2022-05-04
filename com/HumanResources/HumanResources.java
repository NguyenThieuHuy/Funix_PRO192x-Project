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
    static List<Employee> employees = new ArrayList<>();
    static List<Manager> managers = new ArrayList<>();

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
                    
                    employee.id = Integer.parseInt(data[0]);
                    employee.name = data[1];
                    employee.age = Integer.parseInt(data[2]);
                    employee.wage = Integer.parseInt(data[3]);
        
                    String dateInString = data[4];
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
                    Date date = sdf.parse(dateInString);
                    employee.startedDay = date;
        
                    Boolean checker = false;
                    for (Department d : departments) {
                        if (d.departmentName.equals(data[5])) {
                            checker = true;
                            employee.department = d;
                        }
                    }
                    if (checker == false) {
                        Department department = new Department();
                        department.departmentId = departments.size() + 1;
                        department.departmentName = data[5];
        
                        departments.add(department);
                        employee.department = department;
                    }
        
                    employee.offDays = Integer.parseInt(data[6]);

                    employee.overTime = Integer.parseInt(data[8]);
        
                    employees.add(employee);
                }else{
                    Manager manager = new Manager();
                    manager.id = Integer.parseInt(data[0]);        
                    manager.name = data[1];
                    manager.age = Integer.parseInt(data[2]);
                    manager.wage = Integer.parseInt(data[3]);
        
                    String dateInString = data[4];
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
                    Date date = sdf.parse(dateInString);
                    manager.startedDay = date;
        
                    Boolean checker = false;
                    for (Department d : departments) {
                        if (d.departmentName.equals(data[5])) {
                            checker = true;
                            manager.department = d;
                        }
                    }
                    if (checker == false) {
                        Department department = new Department();
                        department.departmentId = departments.size() + 1;
                        department.departmentName = data[5];
        
                        departments.add(department);
                        manager.department = department;
                    }
        
                    manager.offDays = Integer.parseInt(data[6]);
                    
                    manager.role = data[7];
        
                    managers.add(manager);
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
        for (Manager ma : managers) {
            csvWriter.append(Integer.toString(ma.id));
            csvWriter.append(",");
            csvWriter.append(ma.name);
            csvWriter.append(",");
            csvWriter.append(Integer.toString(ma.age));
            csvWriter.append(",");
            csvWriter.append(Integer.toString(ma.wage));
            csvWriter.append(",");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
            String date = sdf.format(ma.startedDay);  
            csvWriter.append(date);
            csvWriter.append(",");
            csvWriter.append(ma.department.departmentName);
            csvWriter.append(",");
            csvWriter.append(Integer.toString(ma.offDays));
            csvWriter.append(",");
            csvWriter.append(ma.role);
            csvWriter.append(",");
            csvWriter.append("0");
            csvWriter.append("\n");
        }

        for (Employee em : employees) {
            csvWriter.append(Integer.toString(em.id));
            csvWriter.append(",");
            csvWriter.append(em.name);
            csvWriter.append(",");
            csvWriter.append(Integer.toString(em.age));
            csvWriter.append(",");
            csvWriter.append(Integer.toString(em.wage));
            csvWriter.append(",");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
            String date = sdf.format(em.startedDay);  
            csvWriter.append(date);
            csvWriter.append(",");
            csvWriter.append(em.department.departmentName);
            csvWriter.append(",");
            csvWriter.append(Integer.toString(em.offDays));
            csvWriter.append(",");
            csvWriter.append("");
            csvWriter.append(",");
            csvWriter.append(Integer.toString(em.overTime));
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
        System.out.println("Managers");
        for (Manager ma : managers) {
            ma.displayInformation();
        }
        System.out.println("Employees");
        for (Employee em : employees) {
            em.displayInformation();
        }
    }

    //Display departments info from Arraylist
    public static void displayAllDepartments() {
        System.out.printf("| %2s","ID");
        System.out.printf("| %10s", "Name");
        System.out.printf("| %10s", "Members\n");
        
        for (Department de : departments) {
            de.memberNum = 0;

            for (Manager ma : managers) {
                if (ma.department == de) {
                    de.memberNum++;
                }
            }

            for (Employee em : employees) {
                if (em.department == de) {
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
            for (Manager ma : managers) {
                if (ma. department == de) {
                    ma.displayInformation();
                }
            }
            for (Employee em : employees) {
                if (em. department == de) {
                    em.displayInformation();
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

            manager.id = managers.size() + employees.size() + 1;
            System.out.println("ID: " + manager.id);

            System.out.print("Name: ");
            manager.name = sc.next();

            System.out.print("Age: ");
            manager.age = sc.nextInt();

            System.out.print("Coefficients salary: ");
            manager.wage = sc.nextInt();

            System.out.println("Start date(yyyy-M-dd): ");
            String dateInString = sc.next();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
            Date date = sdf.parse(dateInString);
            manager.startedDay = date;

            System.out.println("Department: ");
            String de = sc.next();
            Boolean checker = false;
            for (Department d : departments) {
                if (d.departmentName.equals(de)) {
                    checker = true;
                    manager.department = d;
                }
            }
            if (checker == false) {
                Department department = new Department();
                department.departmentId = departments.size() + 1;
                department.departmentName = de;

                departments.add(department);
                manager.department = department;
            }

            System.out.println("Paid leave days: ");
            manager.offDays = sc.nextInt();
            
            System.out.println("Role(Business Leader, Project Leader, Technical Leader): ");
            manager.role = sc.next();

            managers.add(manager);
        }else{
            Employee employee = new Employee();
            
            employee.id = managers.size() + employees.size() + 1;
            System.out.println("ID: " + employee.id);

            System.out.print("Name: ");
            employee.name = sc.next();

            System.out.print("Age: ");
            employee.age = sc.nextInt();

            System.out.print("Coefficients salary: ");
            employee.wage = sc.nextInt();

            System.out.println("Start date(yyyy-M-dd): ");
            String dateInString = sc.next();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
            Date date = sdf.parse(dateInString);
            employee.startedDay = date;

            System.out.println("Department: ");
            String de = sc.next();
            Boolean checker = false;
            for (Department d : departments) {
                if (d.departmentName.equals(de)) {
                    checker = true;
                    employee.department = d;
                }
            }
            if (checker == false) {
                Department department = new Department();
                department.departmentId = departments.size() + 1;
                department.departmentName = de;

                departments.add(department);
                employee.department = department;
            }

            System.out.println("Paid leave days: ");
            employee.offDays = sc.nextInt();

            System.out.println("Overtime: ");
            employee.overTime = sc.nextInt();

            employees.add(employee);
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
            for (Manager m : managers) {
                if (m.id == id) {
                    m.displayInformation();
                }
            }
            for (Employee e : employees) {
                if (e.id == id) {
                    e.displayInformation();
                }
            }
        }
        catch (NumberFormatException ex){
            for (Manager m : managers) {
                if (m.name.equals(input)) {
                    m.displayInformation();
                }
            }
            for (Employee e : employees) {
                if (e.name.equals(input)) {
                    e.displayInformation();
                }
            }
        }
    }

    //Calculate and display staff salary
    public static void displayAllStaffsPayroll() {

        System.out.printf("| %2s","ID");
        System.out.printf("| %10s", "Name");
        System.out.printf("| %15s", "Salary\n");

        System.out.println("Managers");
        for (Manager ma : managers) {
            System.out.printf("| %2s",ma.id);
            System.out.printf("| %10s", ma.name);
            System.out.printf("| %15s", ma.calculateSalary()+"\n");
        }
        System.out.println("Employees");
        for (Employee em : employees) {
            System.out.printf("| %2s",em.id);
            System.out.printf("| %10s", em.name);
            System.out.printf("| %15s", em.calculateSalary()+"\n");
        }
    }

    //Sort salary of staffs descending and ascending
    public static void sortPayroll() {
        Comparator<Manager> managersComparator
         = (c1, c2) -> (int) (c2.calculateSalary() - c1.calculateSalary());
         Comparator<Employee> employeesComparator
         = (c1, c2) -> (int) (c2.calculateSalary() - c1.calculateSalary());

        managers.sort(managersComparator);
        employees.sort(employeesComparator);

        displayAllStaffsPayroll();
    }
    public static void sortPayrollReverse() {
        Comparator<Manager> managersComparator
         = (c1, c2) -> (int) (c1.calculateSalary() - c2.calculateSalary());
         Comparator<Employee> employeesComparator
         = (c1, c2) -> (int) (c1.calculateSalary() - c2.calculateSalary());

        managers.sort(managersComparator);
        employees.sort(employeesComparator);

        displayAllStaffsPayroll();
    }

    //Main flow of methods
    public static void main(String[] args) throws ParseException, IOException {
        CSVreader();
        Boolean isRunning = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n1. Display the list of existing employees in the company\n2. Show parts of the company\n3. Display employees by department\n4. Add new employees to the company: including 2 types\n- Add regular staff \n- Add employees who are management levels (with additional positions)\n5. Search for employee information by employee's name or code\n6. Display the payroll of employees throughout the company\n7. Display employee payroll in ascending order\n8. Display employee payroll in descending order\n9. Close program and save file");
        do {
            System.out.print("\nChoose what you want to do from above options!");
            isRunning = true;
            int method = sc.nextInt();
            if (method == 1) {
                displayAllStaffs();
                isRunning = false;
            }
            else if (method == 2) {
                displayAllDepartments();
                isRunning = false;
            }
            else if (method == 3) {
                sortStaffsByDepartments();
                isRunning = false;
            }
            else if (method == 4) {
                newEmployee();
                isRunning = false;
            }
            else if (method == 5) {
                search();
                isRunning = false;
            }
            else if (method == 6) {
                displayAllStaffsPayroll();
                isRunning = false;
            }
            else if (method == 7) {
                sortPayroll();
                isRunning = false;
            }
            else if (method == 8){
                sortPayrollReverse();
                isRunning = false;
            }
            else if (method == 9){
                isRunning = true;
            }
            else{
                System.out.println("\nYour choice is invalid!");
                isRunning = false;
            }
        } while (isRunning == false);
        CSVwriter();
    }
}