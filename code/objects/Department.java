package code.objects;

import java.util.LinkedList;
import java.util.List;

public class Department {
    // Properties
    private String name;
    private List<Major> majors;

    // COnstructor
    public Department(String name) {
        this.name = name;
        this.majors = new LinkedList<Major>();
    }

    // Getter
    public String getName() {
        return this.name;
    }

    public List<Major> getMajors() {
        return this.majors;
    }

    public List<String> getMajorNames() {
        List<String> names = new LinkedList<String>();
        for (Major major : this.majors) {
            names.add(major.getName());
        }
        return names;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    public void addMajor(Major major) {
        this.majors.add(major);
    }
}
