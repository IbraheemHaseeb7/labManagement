package lab_assessment_02;

public class Lab {
    private String name;
    private PC computers[] = new PC[50];

    {
        for (int counter = 0; counter < computers.length; counter++) {
            computers[counter] =  new PC("123", "Dell", "1280x720", 8, 520);
        }
    }
    private Employee labAttendant;

    public String getName() {
        return name;
    }

    public Lab(String name, Employee labAttendant, PC[] computers) {
        this.name = name;
        this.labAttendant = labAttendant;
        this.computers = computers;
    }

    public Lab(Lab lab) {
        this.name = lab.name;
        this.labAttendant = lab.labAttendant;
        this.computers = lab.computers;
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

    public String toString() {
        return String.format("Name: %s", this.name);
    }

    public Object clone() {
        return new Lab(this.name, this.labAttendant, this.computers);
    }

    public boolean equals(Object o) {
        Lab temp = (Lab)o;
        boolean isResult = false;

        if (this.name == temp.name && this.labAttendant.equals(temp.labAttendant)) {
            isResult = true;
        } else {
            isResult = false;
        }

        return isResult;
    }
}
