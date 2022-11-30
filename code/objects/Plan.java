package code.objects;

import java.util.List;

public class Plan {
    private String name;
    private List<Subject> subjects;

    public Plan(String name, List<Subject> subjects) {
        this.name = name;
        this.subjects = subjects;
    }

    // Getter
    public String getName() {
        return this.name;
    }

    public List<Subject> getSubjects() {
        return this.subjects;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }
}
