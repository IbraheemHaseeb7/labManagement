package lab_assessment_02;
import java.util.Scanner;

public class SideFunctions {

        Scanner stringInput = new Scanner(System.in);
        Scanner simpleInput = new Scanner(System.in);

    public Lab takeLab() {
        // public Lab(String name, Employee labAttendant, PC[] computers) {
        print("Enter lab name: ");
        String name = stringInput.nextLine();

        Employee emp = takeEmployee();

        print("Enter how many computers should be in this lab: ");
        int size = simpleInput.nextInt();

        return new Lab(name, emp, new PC[size]);
    }

    public Employee takeEmployee() {

        print("Enter employee name: ");
        String name = stringInput.nextLine();
        print("Enter employee designation: ");
        String designation = stringInput.nextLine();
        print("Enter employee id: ");
        String id = stringInput.nextLine();

        return new Employee(name, id, designation);
    }

    public PC takePC() {

        print("Enter pc name: ");
        String name = stringInput.nextLine();
        print("Enter lcd name: ");
        String lcdName = stringInput.nextLine();
        print("Enter asset id: ");
        String id = stringInput.nextLine();
        print("Enter RAM size(int): ");
        int ram = simpleInput.nextInt();
        print("Enter disk size(int): ");
        int disk = simpleInput.nextInt();

        return new PC(id, name, lcdName, ram, disk);
    }

    public Department takeDepartment() {

        print("Enter name: ");
        String name = stringInput.nextLine();
        Employee hod = takeEmployee();
        Employee labIncharge = takeEmployee();
        print("Enter number of labs for this department: ");
        int size = simpleInput.nextInt();

        return new Department(name, hod, labIncharge, new Lab[size], size);
    }

    public void print(String data) {
        System.out.print(data);
    }

    public void println(String data) {
        System.out.println(data);
    }
}
