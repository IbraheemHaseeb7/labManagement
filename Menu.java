package src.lab_assessment_02;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
    String menuString = mainMenuString();

    {
        try {
            depf.createFile("D:\\JavaPractice\\lab_assessment_02\\src\\lab_assessment_02\\data\\dep.dat");
            labf.createFile("D:\\JavaPractice\\lab_assessment_02\\src\\lab_assessment_02\\data\\lab.dat");
            pcf.createFile("D:\\JavaPractice\\lab_assessment_02\\src\\lab_assessment_02\\data\\pc.dat");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Menu() throws IOException {
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
            }

            menuString = mainMenuString() + subMenuString() + computersMenuString();

            // Lab menu here
        }
        if (mainMenu && subMenu) {

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
            }

            menuString = mainMenuString() + subMenuString() + computersMenuString();

            // Department menu here
        }
        if (mainMenu) {

            // taking input for new department
            if (temp == 1) {
                mf.addNewDepartment();
            } else if (temp == 4) {
                sf.println(mf.getDepartment().toString());
            }
            menuString = mainMenuString() + subMenuString() + computersMenuString();
        }

        main = mf.getDepartment();
        return menuString;
    }

    public Menu fetchData() throws IOException {

        // fetching data from department file
        String depData[] = depf.readFromFile(5);
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

//                        System.out.println(Integer.parseInt(lab.split("_")[6]));
                        ArrayList<String> softwares = new ArrayList<>();

                        if (Integer.parseInt(lab.split("_")[6]) > 0) {
                            for (int i = 0; i < Integer.parseInt(lab.split("_")[6]); i++) {
                                softwares.add(lab.split("_")[i + 7]);
                            }
                        }

                        String[] softs = sf.converter(softwares);

                        main.getLabs()[labCounter] = new Lab(lab.split("_")[0], new Employee(lab.split("_")[1], lab.split("_")[2], lab.split("_")[3]), new PC[Integer.parseInt(lab.split("_")[5])], Integer.parseInt(lab.split("_")[5]), softs);

                        main.getLabs()[labCounter].setPcCounter(Integer.parseInt(lab.split("_")[4]));

                        // allowing lab menu functions to work
                        subMenu = true;
                        menuString = mainMenuString() + subMenuString();

                        //reading the pc data
                        String pcData[] = pcf.readFromFile(main.getLabs()[labCounter].getPcCounter());
                        int pcCounter = 0;

                        for (String pc : pcData) {
                            if (pc != null) {

                                main.getLabs()[labCounter].getComputers()[pcCounter++] = new PC(pc.split("_")[0], pc.split("_")[1], pc.split("_")[2], Integer.parseInt(pc.split("_")[3]), Integer.parseInt(pc.split("_")[4]));

                                // allowing pc menu to work
                                computersMenu = true;
                                menuString = mainMenuString() + subMenuString() + computersMenuString();
                            }
                        }
                        labCounter++;
                    }
                }
            }
        }

        // giving data to menu functions to process
        mf.setDepartment(main);
        return this;
    }

    public Menu writeData() throws IOException {
        FileWriter depw = new FileWriter("D:\\JavaPractice\\lab_assessment_02\\src\\lab_assessment_02\\data\\dep.dat");
        FileWriter labw = new FileWriter("D:\\JavaPractice\\lab_assessment_02\\src\\lab_assessment_02\\data\\lab.dat");
        FileWriter pcw = new FileWriter("D:\\JavaPractice\\lab_assessment_02\\src\\lab_assessment_02\\data\\pc.dat");


        // writing into the department file
        depw.write(String.format("%s_%s_%s_%s_%s_%s_%s_%d_%d", main.getName(), main.getHOD().getName(), main.getHOD().getDesignation(), main.getHOD().getId(), main.getLabIncharge().getName(), main.getLabIncharge().getDesignation(), main.getLabIncharge().getId(), main.getLabCounter(), main.getNumberOfLabs()));
        depf.writeIntoFile(depw, String.format("%s_%s_%s_%s_%s_%s_%s_%d_%d", main.getName(), main.getHOD().getName(), main.getHOD().getDesignation(), main.getHOD().getId(), main.getLabIncharge().getName(), main.getLabIncharge().getDesignation(), main.getLabIncharge().getId(), main.getLabCounter(), main.getNumberOfLabs()));

        // writing into the labs file
        for (int counter = 0; counter < main.getLabCounter(); counter++) {

            labw.write(String.format("%s_%s_%s_%s_%d_%d", main.getLabs()[counter].getName(), main.getLabs()[counter].getLabAttendant().getName(), main.getLabs()[counter].getLabAttendant().getId(), main.getLabs()[counter].getLabAttendant().getDesignation(), main.getLabs()[counter].getPcCounter(), main.getLabs()[counter].getNumberOfComputers()));

            labw.write("_" + main.getLabs()[counter].getInstalledSoftwares().length);

            for (String software : main.getLabs()[counter].getInstalledSoftwares()) {
                labw.write( "_" + software);
            }

            labw.write("\n");

            // writing into the pc file
            for (int innerCounter = 0; innerCounter < main.getLabs()[counter].getPcCounter(); innerCounter++) {

                pcw.write(String.format("%s_%s_%s_%d_%d\n", main.getLabs()[counter].getComputers()[innerCounter].getAssetID(), main.getLabs()[counter].getComputers()[innerCounter].getName(), main.getLabs()[counter].getComputers()[innerCounter].getLCDname(), main.getLabs()[counter].getComputers()[innerCounter].getRAMsize(), main.getLabs()[counter].getComputers()[innerCounter].getDiskSize()));
            }
        }

        depw.close();
        labw.close();
        pcw.close();

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
                printString("You entered a wrong value");
            }

            // System.out.println(temp + " Menu: " + mainMenu + " Sub Menu: " + subMenu + "
            // Computers Menu: " + computersMenu);
            menu();
        } while (temp != -1);

        return this;
    }

    public void printString(String str) {
        System.out.println(str);
    }
}
