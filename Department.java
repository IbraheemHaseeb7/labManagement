
public class Department {
    private String name;
    private Employee HOD, labIncharge;
    private Lab labs[];
    private int labCounter, numberOfLabs;

    public int getNumberOfLabs() {
        return numberOfLabs;
    }

    public void setNumberOfLabs(int numberOfLabs) {
        this.numberOfLabs = numberOfLabs;
    }

    public int getLabCounter() {
        return labCounter;
    }

    public void setLabCounter(int labCounter) {
        this.labCounter = labCounter;
    }

    public Department(String name, Employee HOD, Employee labIncharge, Lab[] labs, int numberOfLabs) {
        this.name = name;
        this.HOD = HOD;
        this.labIncharge = labIncharge;
        this.labs = labs;
        this.numberOfLabs = numberOfLabs;
    }

    public Department(Department department) {
        this.name = department.name;
        this.HOD = department.HOD;
        this.labIncharge = department.labIncharge;
        this.labs = department.labs;
        this.numberOfLabs = department.numberOfLabs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getHOD() {
        return HOD;
    }

    public void setHOD(Employee HOD) {
        this.HOD = HOD;
    }

    public Employee getLabIncharge() {
        return labIncharge;
    }

    public void setLabIncharge(Employee labIncharge) {
        this.labIncharge = labIncharge;
    }

    public Lab[] getLabs() {
        return labs;
    }

    public void setLabs(Lab[] labs) {
        this.labs = labs;
    }

    public String toString() {
        return String.format("Name: %s\nNo. of Labs: %d\n", this.name, this.numberOfLabs) + HOD.toString()
                + labIncharge.toString();
    }

    public Object clone() {
        return new Department(this.name, this.HOD, this.labIncharge, this.labs, this.numberOfLabs);
    }

    public boolean equals(Object o) {
        Department temp = (Department) o;
        boolean isResult = false;

        if (this.name == temp.name && this.getHOD().equals(temp.getHOD())
                && this.getLabIncharge().equals(temp.getLabIncharge())) {
            isResult = true;
        } else {
            isResult = false;
        }

        return isResult;
    }
}
