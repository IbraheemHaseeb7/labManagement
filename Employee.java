package src.lab_assessment_02;

public class Employee {
    private String name, id, designation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Employee(String name, String id, String designation) {
        this.name = name;
        this.id = id;
        this.designation = designation;
    }

    public Employee(Employee emp) {
        this.name = emp.name;
        this.id = emp.id;
        this.designation = emp.designation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String toString() {
        return String.format("\nName: %s\nDesignation: %s\nID: %s\n", this.name, this.designation, this.id);
    }

    public Object clone() {
        return new Employee(this.name, this.id, this.designation);
    }

    public boolean equals(Object o) {
        Employee temp = (Employee) o;
        boolean isResult = false;

        if (this.name == temp.name && this.id == temp.id && this.designation == temp.designation) {
            isResult = true;
        } else {
            isResult = false;
        }

        return isResult;
    }
}
