package src.lab_assessment_02;

public class Lab {
    private String name;
    private PC computers[] = new PC[50];
    private Employee labAttendant;
    private String[] installedSoftwares;
    private int pcCounter;

    private String softwares[] = {"Intellij IDEAD", "Visual Studio", "Visual Studio Code", "Fleet IDE", "Adobe After Effects", "Adobe Premiere Pro", "Adobe PhotoShop"};

    private int numberOfComputers;

    public Lab(String name, Employee labAttendant, PC[] computers, int numberOfComputers, String[] installedSoftwares) {
        this.name = name;
        this.labAttendant = labAttendant;
        this.computers = computers;
        this.numberOfComputers = numberOfComputers;
        this.installedSoftwares = installedSoftwares;
    }

    public Lab(Lab lab) {
        this.name = lab.name;
        this.labAttendant = lab.labAttendant;
        this.computers = lab.computers;
        this.numberOfComputers = lab.numberOfComputers;
        this.pcCounter = lab.pcCounter;
        this.installedSoftwares = lab.installedSoftwares;
    }

    public Lab() {}

    public String[] getInstalledSoftwares() {
        return this.installedSoftwares;
    }

    public void setInstalledSoftwares(String[] installedSoftwares) {
        this.installedSoftwares = installedSoftwares;
    }

    public int getNumberOfComputers() {
        return numberOfComputers;
    }

    public void setNumberOfComputers(int numberOfComputers) {
        this.numberOfComputers = numberOfComputers;
    }

    public String[] getSoftwares() {
        return softwares;
    }

    public void setSoftwares(String[] softwares) {
        this.softwares = softwares;
    }

    public void setPcCounter(int pcCounter) {
        this.pcCounter = pcCounter;
    }

    public int getPcCounter() {
        return this.pcCounter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getLabAttendant() {
        return labAttendant;
    }

    public void setLabAttendant(Employee labAttendant) {
        this.labAttendant = labAttendant;
    }

    public PC[] getComputers() {
        return computers;
    }

    public void setComputers(PC[] computers) {
        this.computers = computers;
    }

    public void showSoftwares() {

        int counter = 1;

        for (String soft : softwares) {
            System.out.println(counter++ + ". " + soft);
        }
    }

    public String toString() {
        return String.format("Name: %s\nNumber of Computers: %d\n", this.name, this.numberOfComputers)
                + labAttendant.toString();
    }

    public Object clone() {
        return new Lab(this.name, this.labAttendant, this.computers, this.numberOfComputers, this.installedSoftwares);
    }

    public boolean equals(Object o) {
        Lab temp = (Lab) o;
        boolean isResult;

        if (this.name == temp.name && this.labAttendant.equals(temp.labAttendant)) {
            isResult = true;
        } else {
            isResult = false;
        }

        return isResult;
    }
}
