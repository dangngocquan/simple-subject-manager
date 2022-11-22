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
    private String descriptionCompulsory1;
    private String descriptionCompulsory2;
    private String descriptionOptional1;
    private String descriptionOptional2;

    // Constructor
    public KnowledgePart(String name) {
        this.name = name;
        this.compulsorySubjecs1 = new LinkedList<Subject>();
        this.optionalSubjects1 = new LinkedList<Subject>();
        this.compulsorySubjecs2 = new LinkedList<Subject>();
        this.optionalSubjects2 = new LinkedList<Subject>();
        this.minCreditsCompulsorySubjects = 0;
        this.minCreditsOptionalSubjects = 0;
        this.descriptionCompulsory1 = "";
        this.descriptionCompulsory2 = "";
        this.descriptionOptional1 = "";
        this.descriptionOptional2 = "";
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
        return this.compulsorySubjecs2;
    }

    public List<Subject> getOptionalSubjects2() {
        return this.optionalSubjects2;
    }

    public String getDescriptionCompulsory1() {
        return this.descriptionCompulsory1;
    }

    public String getDescriptionCompulsory2() {
        return this.descriptionCompulsory2;
    }

    public String getDescriptionOptional1() {
        return this.descriptionOptional1;
    }

    public String getDescriptionOptional2() {
        return this.descriptionOptional2;
    }

    public List<Subject> getSubjects() {
        List<Subject> subjects = new LinkedList<Subject>();
        for (Subject subject : this.compulsorySubjecs1) {
            subjects.add(subject);
        }
        for (Subject subject : this.compulsorySubjecs2) {
            subjects.add(subject);
        }
        for (Subject subject : this.optionalSubjects1) {
            subjects.add(subject);
        }
        for (Subject subject : this.optionalSubjects2) {
            subjects.add(subject);
        }
        return subjects;
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

    public void setDescriptionCompulsory1(String description) {
        this.descriptionCompulsory1 = description;
    }

    public void setDescriptionCompulsory2(String description) {
        this.descriptionCompulsory2 = description;
    }

    public void setDescriptionOptional1(String description) {
        this.descriptionOptional1 = description;
    }

    public void setDescriptionOptional2(String description) {
        this.descriptionOptional2 = description;
    }
}
