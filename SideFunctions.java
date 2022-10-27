package src.lab_assessment_02;

import java.util.Scanner;
import java.util.ArrayList;

public class SideFunctions {

    Scanner stringInput = new Scanner(System.in);
    Scanner simpleInput = new Scanner(System.in);

    // inner classes to merge 3 functions into one
    class LabSearch {
        int index;
        Lab lab;
        boolean isFound;

        public LabSearch(int index, boolean isFound, Lab lab) {
            this.index = index;
            this.isFound = isFound;
            this.lab = lab;
        }
    }

    class PCsearch {
        int index;
        PC pc;
        boolean isFound;

        public PCsearch(int index, boolean isFound, PC pc) {
            this.index = index;
            this.isFound = isFound;
            this.pc = pc;
        }
    }

    public Lab takeLab() {
        // public Lab(String name, Employee labAttendant, PC[] computers) {
        print("Enter lab name: ");
        String name = stringInput.nextLine();

        Employee emp = takeEmployee();

        print("Enter how many computers should be in this lab: ");
        int size = simpleInput.nextInt();

        println("Which softwares do you want to install in the computers of this lab(Enter index number and -1 to end)...");
        new Lab().showSoftwares();

        String[] softwares = converter(takeSoftwares());

        return new Lab(name, emp, new PC[size], size, softwares);
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

    // deleting an element from the lab array and returning new array with deleted
    // element
    public Lab[] deleteLab(String name, Lab[] lab, int size) {

        LabSearch found = searchLab(name, lab, size);

        for (int counter = found.index; counter < size - 1; counter++) {
            Lab temp = lab[counter + 1];

            lab[counter] = temp;
        }

        return lab;
    }

    // deleting an element from the lab array and returning new array with deleted
    // element
    public PC[] deletePC(String id, PC[] pc, int size) {

        PCsearch found = searchPC(id, pc, size);

        for (int counter = found.index; counter < size - 1; counter++) {
            PC temp = pc[counter + 1];

            pc[counter] = temp;
        }

        return pc;
    }

    // searching for PC
    public PCsearch searchPC(String id, PC[] array, int size) {

        PC pc = new PC("", "", "", 0, 0);
        boolean isFound = false;
        int index = 0;

        for (int counter = 0; counter < size; counter++) {
            if (array[counter].getAssetID().equals(id)) {
                pc = array[counter];
                isFound = true;
                index = counter;
                break;
            }
        }

        return new PCsearch(index, isFound, pc);
    }

    // searching for Lab
    public LabSearch searchLab(String name, Lab[] array, int size) {

        Lab lab = new Lab("", new Employee("", "", ""), new PC[5], 0 , new String[5]);
        int index = 0;
        boolean isFound = false;

        for (int counter = 0; counter < size; counter++) {

            if (array[counter].getName().equals(name)) {
                lab = array[counter];
                index = counter;
                isFound = true;
            }
        }

        return new LabSearch(index, isFound, lab);
    }

    // general methods below used for various purposes
    public String takeString() {
        return stringInput.nextLine();
    }

    public ArrayList<String> takeSoftwares() {
        int option;

        ArrayList<String> softwares = new ArrayList<>();

        while (true) {

            option = takeInt();

            if (option == -1 || option >= new Lab().getSoftwares().length)
                break;

            if (checkExistence(softwares, searchSoftware(option))) {
                System.out.println("Software already added to list...");
            } else {
                softwares.add(searchSoftware(option));
            }

            println("");
            new Lab().showSoftwares();
        }
        return softwares;
    }

    public boolean checkExistence(ArrayList<String> array, String value) {

        boolean isCheck = false;

        for (String soft : array) {
            if (value.equals(soft)) {
                isCheck = true;
                break;
            }
        }

        return isCheck;
    }

    public String[] converter(ArrayList<String> array) {

        String[] temp = new String[array.size()];

        for (int counter = 0; counter < array.size(); counter++) {
            temp[counter] = array.get(counter);
        }

        return temp;
    }

    public String searchSoftware(int option) {
        return new Lab().getSoftwares()[option - 1];
    }

    public void print(String data) {
        System.out.print(data);
    }

    public void println(String data) {
        System.out.println(data);
    }

    public int takeInt() {
        return simpleInput.nextInt();
    }
}