package lab_assessment_02;

public class Department {
    private String name;
    private Employee HOD, labIncharge;
    private Lab labs[] = new Lab[10];
    private PC pc[] = new PC[50];

    public Department(String name, Employee HOD, Employee labIncharge, Lab[] labs) {
        this.name = name;
        this.HOD = HOD;
        this.labIncharge = labIncharge;
        this.labs = labs;
    }

    public Department(Department department) {
        this.name = department.name;
        this.HOD = department.HOD;
        this.labIncharge = department.labIncharge;
        this.labs = department.labs;
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
        return String.format("Name: %s", this.name);
    }

    public Object clone() {
        return new Department(this.name, this.HOD, this.labIncharge, this.labs);
    }

    public boolean equals(Object o) {
        Department temp = (Department) o;
        boolean isResult = false;

        if (this.name == temp.name && this.getHOD().equals(temp.getHOD()) && this.getLabIncharge().equals(temp.getLabIncharge())) {
            isResult = true;
        } else {
            isResult = false;
        }

        return isResult;
    }
}
