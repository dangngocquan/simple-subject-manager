package src.objects;

import java.util.LinkedList;
import java.util.List;

public class KnowledgePart {
    // Properties
    private String name;
    private List<List<Subject>> compulsorySubjecs;
    private List<List<Subject>> optionalSubjects;
    private List<Integer> minCreditsCompulsorySubjects;
    private List<Integer> minCreditsOptionalSubjects;
    private List<String> descriptionCompulsory;
    private String mainDescriptionOptionalSubjects;
    private List<String> descriptionOptionals;

    // Constructor
    public KnowledgePart(String name) {
        this.name = name;
        this.compulsorySubjecs = new LinkedList<List<Subject>>();
        this.optionalSubjects = new LinkedList<List<Subject>>();
        this.minCreditsCompulsorySubjects = new LinkedList<Integer>();
        this.minCreditsOptionalSubjects = new LinkedList<Integer>();
        this.descriptionCompulsory = new LinkedList<String>();
        this.mainDescriptionOptionalSubjects = "";
        this.descriptionOptionals = new LinkedList<String>();
    }

    // Getter
    public String getName() {
        return this.name;
    }

    public List<Integer> getMinCreditsCompulsorySubjects() {
        return this.minCreditsCompulsorySubjects;
    }

    public List<Integer> getMinCreditsOptionalSubjects() {
        return this.minCreditsOptionalSubjects;
    }

    public List<List<Subject>> getCompulsorySubjects() {
        return this.compulsorySubjecs;
    }

    public List<Subject> getCompulsorySubjectsByList() {
        List<Subject> lst = new LinkedList<Subject>();
        for (List<Subject> subjects : compulsorySubjecs) {
            for (Subject subject : subjects) {
                lst.add(subject);
            }
        }
        return lst;
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

    public List<String> getDescriptionCompulsory() {
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
        for (Subject subject : getCompulsorySubjectsByList()) {
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

    public int getNumberOfCompulsorySubjectsList() {
        return this.compulsorySubjecs.size();
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    public void addMinCreditsCompulsorySubjects(int credits) {
        this.minCreditsCompulsorySubjects.add(credits);
    }

    public void addMinCreditsOptionalSubjects(int credits) {
        this.minCreditsOptionalSubjects.add(credits);
    }

    public void addCompulaorySubjects(List<Subject> subjects) {
        this.compulsorySubjecs.add(subjects);
    }

    public void addCompulsorySubject(Subject subject, int indexCompulsory) {
        this.compulsorySubjecs.get(indexCompulsory).add(subject);
    }

    public void addOptionalSubjects(List<Subject> subjects) {
        this.optionalSubjects.add(subjects);
    }

    public void addOptionalSubject(Subject subject, int indexOptional) {
        if (indexOptional < this.optionalSubjects.size()) {
            optionalSubjects.get(indexOptional).add(subject);
        }
    }

    public void addDescriptionCompulsory(String description) {
        this.descriptionCompulsory.add(description);
    }

    public void setMainDescriptionOptionalSubjects(String des) {
        this.mainDescriptionOptionalSubjects = des;
    }

    public void addDescriptionOptional(String description) {
        this.descriptionOptionals.add(description);
    }
}
