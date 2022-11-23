package code.objects;

import java.util.LinkedList;
import java.util.List;

public class Subject {
    // Properties
    private String subjectName;
    private String subjectCode;
    private int numberCredits;
    private List<Subject[]> parentSubjects; // prerequisite subjects
    private List<String[]> parentSubjectCodes;

    // Constructor
    public Subject(String name, String code, int credits) {
        this.subjectCode = code;
        this.subjectName = name;
        this.numberCredits = credits;
        this.parentSubjects = new LinkedList<Subject[]>();
        this.parentSubjectCodes = new LinkedList<String[]>();
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

    public List<Subject[]> getParentSubjects() {
        return this.parentSubjects;
    }

    public List<Subject> getParentSubjectsByList() {
        List<Subject> lst = new LinkedList<Subject>();
        for (Subject[] subjects : getParentSubjects()) {
            for (Subject subject : subjects) {
                lst.add(subject);
            }
        }
        return lst;
    }

    public List<String[]> getParentSubjectCodes() {
        return this.parentSubjectCodes;
    }

    public List<String> getParentSubjectCodesByList() {
        List<String> lst = new LinkedList<String>();
        for (String[] codes : getParentSubjectCodes()) {
            for (String code : codes) {
                lst.add(code);
            }
        }
        return lst;
    }

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

    public void addParentSubject(Subject[] subjects) {
        this.parentSubjects.add(subjects);
    }

    public void addParentSubjectCode(String[] codes) {
        this.parentSubjectCodes.add(codes);
    }
}
