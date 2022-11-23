package code.objects;

import java.util.LinkedList;
import java.util.List;

public class School {
    // Properties
    private String name;
    private List<Department> departments;

    // Constructor
    public School(String name) {
        this.name = name;
        this.departments = new LinkedList<Department>();
    }

    // Getter
    public String getName() {
        return this.name;
    }

    public List<Department> getDepartments() {
        return this.departments;
    }

    public List<String> getDepartmentNames() {
        List<String> names = new LinkedList<String>();
        for (Department department : this.departments) {
            names.add(department.getName());
        }
        return names;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    public void addDepartment(Department department) {
        this.departments.add(department);
    }

}
