public class MenuFunctions {

    private SideFunctions sf = new SideFunctions();
    private Department main;

    public MenuFunctions(Department main) {
        this.main = main;
    }

    public void addNewPC() {

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
    }

    public void viewAllPC() {
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
    }

    public void searchOnePC() {
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

                    SideFunctions.PCsearch pc = sf.searchPC(id, found.lab.getComputers(),
                            found.lab.getPcCounter());

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
    }

    public void deleteOnePC() {
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

                SideFunctions.PCsearch foundPc = sf.searchPC(id, found.lab.getComputers(),
                        found.lab.getPcCounter());

                // checking if pc exists
                if (foundPc.isFound) {

                    // deleting pc from the array
                    found.lab.setComputers(
                            sf.deletePC(id, found.lab.getComputers(), found.lab.getNumberOfComputers()));
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

    public void addNewLab() {
        // adding new lab in the department here
        if (main.getLabCounter() == main.getNumberOfLabs()) {
            sf.println("Number of labs exceeded");
        }

        main.getLabs()[main.getLabCounter()] = sf.takeLab();

        // incrementing in the lab counter to add new labs in
        main.setLabCounter(main.getLabCounter() + 1);

        sf.println("New Lab Added Successfully");
    }

    public void showAllLabs() {
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
    }

    public void searchOneLab() {
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
    }

    public void deleteOneLab() {
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

    public void createMultipleLabs() {
        // creating multiple copies of same entry

        if (main.getLabCounter() == main.getNumberOfLabs()) {
            sf.println("Number of labs exceeded");
        } else {

            sf.print("Enter how many copies you want to make: ");
            int num = sf.takeInt();

            int innerCounter = 0;
            Lab newLab = sf.takeLab();

            do {

                main.getLabs()[main.getLabCounter()] = newLab;

                // incrementing in the lab counter to add new labs in
                main.setLabCounter(main.getLabCounter() + 1);

                innerCounter++;

            } while (innerCounter != num);
            sf.println("New Labs Added Successfully");
        }
    }

    public void addNewDepartment() {
        main = sf.takeDepartment();
        sf.println("New Department Added Successfully");
    }

    public Department getDepartment() {
        return main;
    }
}
