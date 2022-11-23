package code.objects;

import java.util.LinkedList;
import java.util.List;

public class SchoolManager {
    // Properties
    private List<School> schools;

    // Constructor
    public SchoolManager() {
        schools = new LinkedList<School>();
    }

    // Getter
    public List<School> getSchools() {
        return this.schools;
    }

    public List<String> getSchoolNames() {
        List<String> names = new LinkedList<String>();
        for (School school : this.schools) {
            names.add(school.getName());
        }
        return names;
    }

    // Setter
    public void addSchool(School school) {
        this.schools.add(school);
    }

}
