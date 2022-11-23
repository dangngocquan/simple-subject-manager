package code.curriculum;

import java.util.LinkedList;
import java.util.List;

public class KnowledgePart {
    // Properties
    private String name;
    private List<Subject> compulsorySubjecs;
    private List<List<Subject>> optionalSubjects;
    private int minCreditsCompulsorySubjects;
    private int minCreditsOptionalSubjects;
    private String descriptionCompulsory;
    private String mainDescriptionOptionalSubjects;
    private List<String> descriptionOptionals;

    // Constructor
    public KnowledgePart(String name) {
        this.name = name;
        this.compulsorySubjecs = new LinkedList<Subject>();
        this.optionalSubjects = new LinkedList<List<Subject>>();
        this.minCreditsCompulsorySubjects = 0;
        this.minCreditsOptionalSubjects = 0;
        this.descriptionCompulsory = "";
        this.mainDescriptionOptionalSubjects = "";
        this.descriptionOptionals = new LinkedList<String>();
    }

    // Getter
    public String getName() {
        return this.name;
    }

    public int getMinCreditsCompulsorySubjects() {
        return this.minCreditsCompulsorySubjects;
    }

    public int getMinCreditsOptionalSubjects() {
        return this.minCreditsOptionalSubjects;
    }

    public List<Subject> getCompulsorySubjects() {
        return this.compulsorySubjecs;
    }

    public List<List<Subject>> getOptionalSubjects() {
        return this.optionalSubjects;
    }

    public List<Subject> getOptionalSubjectsByList() {
        List<Subject> lst = new LinkedList<Subject>();
        for (List<Subject> subjects : optionalSubjects) {
            for (Subject subject : subjects) {
                lst.add(subject);
            }
        }
        return lst;
    }

    public String getDescriptionCompulsory() {
        return this.descriptionCompulsory;
    }

    public String getMainDescriptionOptionalSubjects() {
        return this.mainDescriptionOptionalSubjects;
    }

    public List<String> getDescriptionOptionals() {
        return this.descriptionOptionals;
    }

    public List<Subject> getSubjects() {
        List<Subject> subjects = new LinkedList<Subject>();
        for (Subject subject : this.compulsorySubjecs) {
            subjects.add(subject);
        }
        for (Subject subject : getOptionalSubjectsByList()) {
            subjects.add(subject);
        }
        return subjects;
    }

    public int getNumberOfOptionalSubjectsList() {
        return this.optionalSubjects.size();
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    public void setMinCreditsCompulsorySubjects(int credits) {
        this.minCreditsCompulsorySubjects = credits;
    }

    public void setMinCreditsOptionalSubjects(int credits) {
        this.minCreditsOptionalSubjects = credits;
    }

    public void addCompulsorySubject(Subject subject) {
        this.compulsorySubjecs.add(subject);
    }

    public void addOptionalSubjects(List<Subject> subjects) {
        this.optionalSubjects.add(subjects);
    }

    public void addOptionalSubject(Subject subject, int indexOptional) {
        if (indexOptional < this.optionalSubjects.size()) {
            optionalSubjects.get(indexOptional).add(subject);
        }
    }

    public void setDescriptionCompulsory(String description) {
        this.descriptionCompulsory = description;
    }

    public void setMainDescriptionOptionalSubjects(String des) {
        this.mainDescriptionOptionalSubjects = des;
    }

    public void addDescriptionOptional(String description) {
        this.descriptionOptionals.add(description);
    }
}
