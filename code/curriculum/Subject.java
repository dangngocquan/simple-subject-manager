package code.curriculum;

import java.util.LinkedList;
import java.util.List;

public class Subject {
    // Properties
    private String subjectName;
    private String subjectCode;
    private int numberCredits;
    private List<Subject> parentSubjects; // prerequisite subjects
    // private List<Subject> childSubjects;

    // Constructor
    public Subject(String name, String code, int credits) {
        this.subjectCode = code;
        this.subjectName = name;
        this.numberCredits = credits;
        this.parentSubjects = new LinkedList<Subject>();
        // this.childSubjects = new LinkedList<Subject>();
    }

    // Getter method
    public String getName() {
        return this.subjectName;
    }

    public String getCode() {
        return this.subjectCode;
    }

    public int getNumberCredits() {
        return this.numberCredits;
    }

    public List<Subject> getParentSubjects() {
        return this.parentSubjects;
    }

    // public List<Subject> getChildSubjects() {
    // return this.childSubjects;
    // }

    // Setter method
    public void setName(String name) {
        this.subjectName = name;
    }

    public void setCode(String code) {
        this.subjectCode = code;
    }

    public void setNumberCredits(int numberCredits) {
        this.numberCredits = numberCredits;
    }

    public void addParentSubject(Subject subject) {
        this.parentSubjects.add(subject);
        // subject.addChildSubject(this);
    }

    // public void addChildSubject(Subject subject) {
    // this.childSubjects.add(subject);
    // }
}
