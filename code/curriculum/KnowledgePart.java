package code.curriculum;

import java.util.LinkedList;
import java.util.List;

public class KnowledgePart {
    // Properties
    private String name;
    private int selection = 1;
    private List<Subject> compulsorySubjecs1;
    private List<Subject> optionalSubjects1;
    private List<Subject> compulsorySubjecs2;
    private List<Subject> optionalSubjects2;
    private int minCreditsCompulsorySubjects;

    private int minCreditsOptionalSubjects;

    // Constructor
    public KnowledgePart(String name,
            int minCreditsCompulsorySubjects,
            int minCreditsOptionalSubjects) {
        this.name = name;
        this.compulsorySubjecs1 = new LinkedList<Subject>();
        this.optionalSubjects1 = new LinkedList<Subject>();
        this.compulsorySubjecs2 = new LinkedList<Subject>();
        this.optionalSubjects2 = new LinkedList<Subject>();
        this.minCreditsCompulsorySubjects = minCreditsCompulsorySubjects;
        this.minCreditsOptionalSubjects = minCreditsCompulsorySubjects;
    }

    // Getter
    public String getName() {
        return this.name;
    }

    public int getSelection() {
        return this.selection;
    }

    public int getMinCreditsCompulsorySubjects() {
        return this.minCreditsCompulsorySubjects;
    }

    public int getMinCreditsOptionalSubjects() {
        return this.minCreditsOptionalSubjects;
    }

    public List<Subject> getCompulsorySubjects1() {
        return this.compulsorySubjecs1;
    }

    public List<Subject> getOptionalSubjects1() {
        return this.optionalSubjects1;
    }

    public List<Subject> getCompulsorySubjects2() {
        return this.compulsorySubjecs1;
    }

    public List<Subject> getOptionalSubjects2() {
        return this.optionalSubjects1;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    public void setSelection(int selection) {
        this.selection = selection;
    }

    public void setMinCreditsCompulsorySubjects(int credits) {
        this.minCreditsCompulsorySubjects = credits;
    }

    public void setMinCreditsOptionalSubjects(int credits) {
        this.minCreditsOptionalSubjects = credits;
    }

    public void addCompulsorySubject1(Subject subject) {
        this.compulsorySubjecs1.add(subject);
    }

    public void addOptionalSubject1(Subject subject) {
        this.optionalSubjects1.add(subject);
    }

    public void addCompulsorySubject2(Subject subject) {
        this.compulsorySubjecs2.add(subject);
    }

    public void addOptionalSubject2(Subject subject) {
        this.optionalSubjects2.add(subject);
    }
}
