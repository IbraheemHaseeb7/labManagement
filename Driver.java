package lab_assessment_02;

public class Driver {
    public static void main(String[] args) {

        Lab[] labs = new Lab[10];

        // public Department(String name, Employee HOD, Employee labIncharge, Lab[] labs) {
        Department first = new Department("CS", new Employee("John", "123", "HOD"), new Employee("Doe", "123", "Lab Incharge"), labs);

        // setting computers
        PC pc[] = new PC[50];

        // setting labs
        for (int counter = 0; counter < first.getLabs().length; counter++) {
            first.getLabs()[counter] = new Lab("C-13", new Employee("Lab", "123", "Lab Attendant"), pc);
        }

        for (int counter = 0; counter < first.getLabs().length; counter++) {
            for (int innerCounter = 0; innerCounter < first.getLabs()[counter].getComputers().length; innerCounter++) {
                first.getLabs()[counter].getComputers()[innerCounter] = new PC("123", "Dell", "1280x768", 8, 520);
            }
        }

        // printing data for testing purposes
        System.out.println(first.getLabs()[0].getName());
    }
}
