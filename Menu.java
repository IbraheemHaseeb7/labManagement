package lab_assessment_02;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Menu {
    static boolean mainMenu, subMenu, computersMenu;
    SideFunctions sf = new SideFunctions();
    Scanner input = new Scanner(System.in);
    Department main;
    private int temp;
    MenuFunctions mf = new MenuFunctions(main);
    FileFunctions depf = new FileFunctions();
    FileFunctions labf = new FileFunctions();
    FileFunctions pcf = new FileFunctions();
    String menuString = "";

    {
        try {
            depf.createFile("dep.dat");
            labf.createFile("lab.dat");
            pcf.createFile("pc.dat");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String mainMenuString() {
        return String.format(
                "Please choose one of the following:\n\nNote: You can add new Employees, Computers and Labs if and only if you have created new department first\n\n1. To add new Department\n4. View Department Attributes\n");
    }

    public String subMenuString() {
        return String.format(
                "\n--> 2. To add new lab\n--> 5. View All labs in the department\n--> 9. Search a lab in the department\n--> 10. Delete a lab in the department\n\nNote: You can add attributes to lab i.e. computers once you have created lab");
    }

    public String computersMenuString() {
        return String.format(
                "\n----> 3. To add new computer\n----> 6. View All Computers in the lab\n----> 7. Search a Computer by Asset ID\n----> 8. Delete a computer by Asset ID\n----> 11. Add Multiple Computers With One Entry\n");
    }

    public String menu() {

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
                case 11:
                    mf.createMultipleComputers();
                    break;
                default:
                    sf.println("Value could not be found...");

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
                default:
                    sf.println("Value could not be found...");
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

    public Menu fetchData() throws FileNotFoundException {

        // fetching data from department file
        String depData[] = depf.readFromFile(1);
        for (String dep : depData) {
            if (dep != null) {
                main = new Department(dep.split("_")[0], new Employee(dep.split("_")[1], dep.split("_")[2], dep.split("_")[3]), new Employee(dep.split("_")[4], dep.split("_")[5], dep.split("_")[6]), new Lab[Integer.parseInt(dep.split("_")[8])], Integer.parseInt(dep.split("_")[8]));

                main.setLabCounter(Integer.parseInt(dep.split("_")[7]));

                // allowing main menu functions to work
                mainMenu = true;
                menuString = mainMenuString();

                // reading the lab data
                String labData[] = labf.readFromFile(main.getLabCounter());
                int labCounter = 0;
                for (String lab : labData) {
                    if (lab != null) {
                        main.getLabs()[labCounter++] = new Lab(lab.split("_")[0], new Employee(lab.split("_")[1], lab.split("_")[2], lab.split("_")[3]), new PC[Integer.parseInt(lab.split("_")[4])], Integer.parseInt(lab.split("_")[5]));


                        // allowing lab menu functions to work
                        subMenu = true;
                        menuString = mainMenuString() + subMenuString();

                        //reading the pc data
                    }
                }
            }
        }
        // giving data to menu functions to process
        mf.setDepartment(main);
        return this;
    }

    public Menu writeData() throws IOException {

        // writing into the department file
        depf.writeIntoFile(String.format("DEP NAME\tEMP NAME\tEMP DES\tEMP ID\tEMP NAME\tEMP DES\tEMP ID\tLAB COUNTER\tNO OF LABS\n%s_%s_%s_%s_%s_%s_%s_%d_%d", main.getName(), main.getHOD().getName(), main.getHOD().getDesignation(), main.getHOD().getId(), main.getLabIncharge().getName(), main.getLabIncharge().getDesignation(), main.getLabIncharge().getId(), main.getLabCounter(), main.getNumberOfLabs()));

        // writing into the labs file
        for (int counter = 0; counter < main.getLabCounter(); counter++) {

            labf.writeIntoFile(String.format("%s_%s_%s_%s_%d_%d\n", main.getLabs()[counter].getName(), main.getLabs()[counter].getLabAttendant().getName(), main.getLabs()[counter].getLabAttendant().getDesignation(), main.getLabs()[counter].getLabAttendant().getId(), main.getLabs()[counter].getPcCounter(), main.getLabs()[counter].getNumberOfComputers()));

            // writing into the pc file
            for (int innerCounter = 0; innerCounter < main.getLabs()[counter].getPcCounter(); innerCounter++) {
                pcf.writeIntoFile(String.format("%s_%s_%s_%d_%d\n", main.getLabs()[counter].getComputers()[innerCounter].getName(), main.getLabs()[counter].getComputers()[innerCounter].getAssetID(), main.getLabs()[counter].getComputers()[innerCounter].getLCDname(), main.getLabs()[counter].getComputers()[innerCounter].getRAMsize(), main.getLabs()[counter].getComputers()[innerCounter].getDiskSize()));
            }
        }

        return this;
    }

    public Menu launchProgram() {
        do {
            printString(menuString);
            sf.print("Enter your option number: ");
            temp = input.nextInt();

            if (mainMenu && (temp == 2)) {
                subMenu = true;
            } else if (mainMenu && subMenu && (temp == 3 || temp == 11)) {
                computersMenu = true;
            } else if (temp == 1) {
                mainMenu = true;
            } else if (!mainMenu && !subMenu && !computersMenu) {
                printString("You enter a wrong value");
            }

            // System.out.println(temp + " Menu: " + mainMenu + " Sub Menu: " + subMenu + "
            // Computers Menu: " + computersMenu);
            menu();
        } while (temp != -1);

        return this;
    }

    public void showDepartment() {
        sf.println(main.toString());
    }

    public void printString(String str) {
        System.out.println(str);
    }
}
