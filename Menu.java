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
        return String.format("\n--> 2. To add new lab\n--> 5. View All labs in the department\n--> 9. Search a lab in the department\n--> 10. Delete a lab in the department\n\nNote: You can add attributes to lab i.e. computers once you have created lab");
    }

    public String computersMenuString() {
        return String.format("\n----> 3. To add new computer\n----> 6. View All Computers in the lab\n----> 7. Search a Computer by Asset ID\n----> 8. Delete a computer by Asset ID");
    }

    public String menu() {
        String menuString = "";
        if (mainMenu && subMenu && computersMenu) {

            // adding one pc to the array of labs
            if (temp == 3) {

                // checking if at least one lab exists or not
                if (main.getLabCounter() == 0) {
                    sf.println("Please create a lab first...");
                } else {

                    // taking input for lab in which computer will be added
                    sf.print("Please enter lab name where you want to add computer: ");
                    String name = sf.takeString();

                    SideFunctions.LabSearch found = sf.searchLab(name, main.getLabs(), main.getLabCounter());

                    // checking if that lab exists
                    if (found.isFound) {

                        found.lab.getComputers()[found.lab.getPcCounter()] = sf.takePC();

                        sf.println("New PC added to the lab " + found.lab.getName() + " successfully!");
                        found.lab.setPcCounter(found.lab.getPcCounter() + 1);

                    } else {
                        sf.println("Searched lab does not exist...");
                    }
                }

            // viewing all the computers in the lab
            } else if (temp == 6) {

                // checking if at least one lab exists
                if (main.getLabCounter() == 0) {
                    sf.println("Please create a lab first...");

                } else {

                    sf.print("Enter lab whose computers you want to see: ");
                    String name = sf.takeString();

                    SideFunctions.LabSearch found = sf.searchLab(name, main.getLabs(), main.getLabCounter());

                    if (found.isFound) {

                        // checking if computer exists in the lab...
                        if (found.lab.getPcCounter() == 0) {
                            sf.println("Please add a computer first...");
                        } else {

                            sf.println("=========================================");
                            sf.println("                   PC                ");

                            for (int counter = 0; counter < found.lab.getPcCounter(); counter++) {
                                sf.println(found.lab.getComputers()[counter].toString());
                            }
                            sf.println("=========================================");
                        }
                    } else {

                        sf.println("Searched lab does not exist...");
                    }
                }

            // searching for the computer in a specific lab
            } else if (temp == 7) {

                // checking if at least one lab exist
                if (main.getLabCounter() == 0) {
                    sf.println("Please create a lab first...");
                } else {

                    sf.print("Enter the lab where you want to search: ");
                    String name = sf.takeString();

                    // running search function for lab
                    SideFunctions.LabSearch found = sf.searchLab(name, main.getLabs(), main.getLabCounter());

                    // checking if searched lab exist
                    if (found.isFound) {

                        // checking if computers exist in this lab
                        if (found.lab.getPcCounter() == 0) {
                            sf.println("Please add a computer first...");
                        } else {

                            sf.print("Enter asset id of computer: ");
                            String id = sf.takeString();

                            SideFunctions.PCsearch pc = sf.searchPC(id, found.lab.getComputers(), found.lab.getPcCounter());


                            // checking if entered pc exists
                            if (pc.isFound) {

                                sf.println("===========================================");
                                sf.println(pc.pc.toString());
                                sf.println("===========================================");
                            } else {
                                sf.println("Entered pc does not exist...");
                            }

                        }

                    } else {
                        sf.println("Searched lab does not exist...");
                    }
                }

            // delete a element from the computer array
            } else if (temp == 8) {

                // checking if labs exist
                if (main.getLabCounter() == 0) {
                    sf.println("Please create a lab first...");
                } else {

                    // taking input from which lab computer is to be deleted
                    sf.print("Enter the lab name from which you want to delete computer: ");
                    String name = sf.takeString();

                    SideFunctions.LabSearch found = sf.searchLab(name, main.getLabs(), main.getLabCounter());

                    // lab found
                    if (found.isFound) {

                        sf.print("Enter the pc asset ID which you want to delete: ");
                        String id = sf.takeString();

                        SideFunctions.PCsearch foundPc = sf.searchPC(id, found.lab.getComputers(), found.lab.getPcCounter());

                        // checking if pc exists
                        if (foundPc.isFound) {

                            // deleting pc from the array
                            found.lab.setComputers(sf.deletePC(id, found.lab.getComputers(), found.lab.getNumberOfComputers()));
                            found.lab.setPcCounter(found.lab.getPcCounter() - 1);
                            sf.println("Successfully deleted a PC");

                        } else {
                            sf.println("Entered pc does not exist");
                        }

                    // lab not found
                    } else {
                        sf.println("Entered Lab could not be found...");
                    }
                }
            }
            menuString = mainMenuString() + subMenuString() + computersMenuString();


        // Lab menu here
        } else if (mainMenu && subMenu) {

            // adding new lab in the department here
            if (temp == 2) {

                if (main.getLabCounter() == main.getNumberOfLabs()) {
                    sf.println("Number of labs exceeded");
                }

                main.getLabs()[main.getLabCounter()] = sf.takeLab();

                // incrementing in the lab counter to add new labs in
                main.setLabCounter(main.getLabCounter()+1);

                sf.println("New Lab Added Successfully");

            // showing all the labs here
            } else if (temp == 5) {

                if (main.getLabCounter() == 0) {
                    sf.println("Please enter a lab first...");
                } else {

                    sf.println("==================================================");
                    sf.println("     ALL LABS        ");
                    for (int counter = 0; counter < main.getLabCounter(); counter++) {
                        sf.println(main.getLabs()[counter].toString());
                    }
                    sf.println("==================================================");
                }

            // searching for a lab in the department
            } else if (temp == 9) {

                if (main.getLabCounter() == 0) {
                    sf.println("Please enter a lab first...");
                } else {

                    sf.print("Enter lab name you are looking for: ");
                    String name = sf.takeString();

                    sf.println("===================================================");
                    sf.println("       LAB       ");
                    sf.println(sf.searchLab(name, main.getLabs(), main.getLabCounter()).lab.toString());
                    sf.println("===================================================");
                }

            // deleting a pre-existing lab
            } else if (temp == 10) {

                if (main.getLabCounter() == 0) {
                    sf.println("Please enter a lab first...");
                } else {

                    // taking input for which lab is to be deleted
                    sf.print("Enter the lab name you want to delete: ");
                    String name = sf.takeString();

                    SideFunctions.LabSearch found = sf.searchLab(name, main.getLabs(), main.getLabCounter());

                    if (found.isFound) {

                        // deleting an element from the array
                        main.setLabs(sf.deleteLab(name, main.getLabs(), main.getLabCounter()));
                        main.setLabCounter(main.getLabCounter() - 1);
                        sf.println("Successfully lab deleted from the department...");

                    } else {

                        sf.println("Entered lab could not be found...");
                    }
                }
            }

            menuString = mainMenuString() + subMenuString() + computersMenuString();

        // Department menu here
        } else if (mainMenu) {

            // taking input for new department
            if (temp == 1) {
                main = sf.takeDepartment();
                sf.println("New Department Added Successfully");

            // showing all department attributes
            } else if (temp == 4) {
                sf.println(main.toString());
            }
            menuString = mainMenuString() + subMenuString();
        }
        return menuString;
    }

    public void takeInput() {
        printString(mainMenuString());
        do {
            sf.print("Enter your option number: ");
            temp = input.nextInt();

            if (mainMenu && temp == 2) {
                subMenu = true;
            } else if (mainMenu && subMenu && temp == 3) {
                computersMenu = true;
            } else if (temp == 1) {
                mainMenu = true;
            } else if (!mainMenu && !subMenu && !computersMenu) {
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
