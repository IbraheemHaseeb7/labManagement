
import java.util.Scanner;

public class Menu {
    static boolean mainMenu, subMenu, computersMenu;
    SideFunctions sf = new SideFunctions();
    Scanner input = new Scanner(System.in);
    Department main;
    private int temp;
    MenuFunctions mf = new MenuFunctions(main);

    public String mainMenuString() {
        return String.format(
                "Please choose one of the following:\n\nNote: You can add new Employees, Computers and Labs if and only if you have created new department first\n\n1. To add new Department\n4. View Department Attributes\n");
    }

    public String subMenuString() {
        return String.format(
                "\n--> 2. To add new lab\n--> 5. View All labs in the department\n--> 9. Search a lab in the department\n--> 10. Delete a lab in the department\n--> 11. Add Multiple labs from one entry\n\nNote: You can add attributes to lab i.e. computers once you have created lab");
    }

    public String computersMenuString() {
        return String.format(
                "\n----> 3. To add new computer\n----> 6. View All Computers in the lab\n----> 7. Search a Computer by Asset ID\n----> 8. Delete a computer by Asset ID");
    }

    public String menu() {
        String menuString = "";

        // PC menu
        if (mainMenu && subMenu && computersMenu) {

            switch (temp) {
                case 3:
                    mf.addNewPC();
                    break;
                case 6:
                    mf.viewAllPC();
                    break;
                case 7:
                    mf.searchOnePC();
                    break;
                case 8:
                    mf.deleteOnePC();
                    break;

            }

            menuString = mainMenuString() + subMenuString() + computersMenuString();

            // Lab menu here
        } else if (mainMenu && subMenu) {

            switch (temp) {
                case 2:
                    mf.addNewLab();
                    break;
                case 5:
                    mf.showAllLabs();
                    break;
                case 9:
                    mf.searchOneLab();
                    break;
                case 10:
                    mf.deleteOneLab();
                    break;
                case 11:
                    mf.createMultipleLabs();
                    break;
            }

            menuString = mainMenuString() + subMenuString() + computersMenuString();

            // Department menu here
        } else if (mainMenu) {

            // taking input for new department
            if (temp == 1) {
                mf.addNewDepartment();
            } else if (temp == 4) {
                sf.println(mf.getDepartment().toString());
            }
            menuString = mainMenuString() + subMenuString();
        }

        main = mf.getDepartment();
        return menuString;
    }

    public void takeInput() {
        printString(mainMenuString());
        do {
            sf.print("Enter your option number: ");
            temp = input.nextInt();

            if (mainMenu && (temp == 2 || temp == 11)) {
                subMenu = true;
            } else if (mainMenu && subMenu && temp == 3) {
                computersMenu = true;
            } else if (temp == 1) {
                mainMenu = true;
            } else if (!mainMenu && !subMenu && !computersMenu) {
                printString("You enter a wrong value");
            }

            // System.out.println(temp + " Menu: " + mainMenu + " Sub Menu: " + subMenu + "
            // Computers Menu: " + computersMenu);

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
