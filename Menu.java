package lab_assessment_02;

import java.util.Scanner;

public class Menu {
    static boolean mainMenu, subMenu, computersMenu;
    SideFunctions sf = new SideFunctions();
    Scanner input = new Scanner(System.in);
    Department main;
    private int temp;


    public String mainMenuString() {
        return String.format("Please choose one of the following:\n\nNote: You can add new Employees, Computers and Labs if and only if you have created new department first\n\n1. To add new Department\n4. View Department Attributes\n");
    }

    public String subMenuString() {
        return String.format("\n--> 2. To add new lab\n--> 5. View All labs in the department\n\nNote: You can add attributes to lab i.e. computers once you have created lab");
    }

    public String computersMenuString() {
        return String.format("\n----> 3. To add new computer\n----> 6. View All Computers in the lab");
    }

    public String menu() {
        String menuString = "";
        if (mainMenu && subMenu && computersMenu) {

            // adding one pc to the array of labs
            main.getLabs()[0].getComputers()[main.getLabs()[0].getPcCounter()] = sf.takePC();
            menuString = mainMenuString() + subMenuString() + computersMenuString();


        // Adding a new lab in the labs array
        } else if (mainMenu && subMenu) {
            main.getLabs()[main.getLabCounter()] = sf.takeLab();

            // incrementing in the lab counter to add new labs in
            main.setLabCounter(main.getLabCounter()+1);

            sf.println("New Lab Added Successfully");
            menuString = mainMenuString() + subMenuString() + computersMenuString();

        // Adding a new department
        } else if (mainMenu) {

            if (temp == 1) {
                main = sf.takeDepartment();
                sf.println("New Department Added Successfully");
            }
            menuString = mainMenuString() + subMenuString();
        }
        return menuString;
    }

    public void takeInput() {
        printString(mainMenuString());
        do {
            temp = input.nextInt();

            if (mainMenu && temp == 2) {
                subMenu = true;
            } else if (mainMenu && subMenu && temp == 3) {
                computersMenu = true;
            } else if (temp == 1) {
                mainMenu = true;
            } else if(temp == 4 && mainMenu) {
                showDepartment();
            } else {
                printString("You enter a wrong value");
            }

//            System.out.println(temp + " Menu: " + mainMenu + " Sub Menu: " + subMenu + " Computers Menu: " + computersMenu);

            printString(menu());
        } while (temp != -1);
    }

    public void showDepartment() {
        sf.println(main.toString());
    }

    public void printString(String str) {
        System.out.println(str);
    }
}
