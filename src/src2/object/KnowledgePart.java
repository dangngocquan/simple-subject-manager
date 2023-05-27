package src2.object;

import java.util.LinkedList;
import java.util.List;

public class KnowledgePart {
    // Properties
    private String name;

    private List<List<Subject>> compulsorySubjecs;
    private List<Integer> minCreditsCompulsorySubjects;
    private List<String> descriptionCompulsory;

    private List<List<Subject>> optionalSubjects; // OR optionals
    private List<Integer> minCreditsOptionalSubjects; // OR optionals
    private String mainDescriptionOptionalSubjects; // OR optionals
    private List<String> descriptionOptionals; // OR optionals

    private List<List<Subject>> optionalSubjectsAND; // AND optionals
    private List<Integer> minCreditsOptionalSubjectsAND; // AND optionals
    private String mainDescriptionOptionalSubjectsAND; // AND optionals
    private List<String> descriptionOptionalsAND; // AND optionals

    // Constructor
    public KnowledgePart(String name) {
        this.name = name;
        this.compulsorySubjecs = new LinkedList<List<Subject>>();
        this.minCreditsCompulsorySubjects = new LinkedList<Integer>();
        this.descriptionCompulsory = new LinkedList<String>();

        this.optionalSubjects = new LinkedList<List<Subject>>();
        this.minCreditsOptionalSubjects = new LinkedList<Integer>();
        this.mainDescriptionOptionalSubjects = "";
        this.descriptionOptionals = new LinkedList<String>();

        this.optionalSubjectsAND = new LinkedList<List<Subject>>();
        this.minCreditsOptionalSubjectsAND = new LinkedList<Integer>();
        this.mainDescriptionOptionalSubjectsAND = "";
        this.descriptionOptionalsAND = new LinkedList<String>();
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

    public List<Integer> getMinCreditsOptionalSubjectsAND() {
        return this.minCreditsOptionalSubjectsAND;
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

    public List<List<Subject>> getOptionalSubjectsAND() {
        return this.optionalSubjectsAND;
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

    public List<Subject> getOptionalSubjectsANDByList() {
        List<Subject> lst = new LinkedList<Subject>();
        for (List<Subject> subjects : optionalSubjectsAND) {
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

    public String getMainDescriptionOptionalSubjectsAND() {
        return this.mainDescriptionOptionalSubjectsAND;
    }

    public List<String> getDescriptionOptionals() {
        return this.descriptionOptionals;
    }

    public List<String> getDescriptionOptionalsAND() {
        return this.descriptionOptionalsAND;
    }

    public List<Subject> getSubjects() {
        List<Subject> subjects = new LinkedList<Subject>();
        for (Subject subject : getCompulsorySubjectsByList()) {
            subjects.add(subject);
        }
        for (Subject subject : getOptionalSubjectsByList()) {
            subjects.add(subject);
        }
        for (Subject subject : getOptionalSubjectsANDByList()) {
            subjects.add(subject);
        }
        return subjects;
    }

    public int getNumberOfOptionalSubjectsList() {
        return this.optionalSubjects.size();
    }

    public int getNumberOfOptionalSubjectsANDList() {
        return this.optionalSubjectsAND.size();
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

    public void addMinCreditsOptionalSubjectsAND(int credits) {
        this.minCreditsOptionalSubjectsAND.add(credits);
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

    public void addOptionalSubjectsAND(List<Subject> subjects) {
        this.optionalSubjectsAND.add(subjects);
    }

    public void addOptionalSubject(Subject subject, int indexOptional) {
        if (indexOptional < this.optionalSubjects.size()) {
            optionalSubjects.get(indexOptional).add(subject);
        }
    }

    public void addOptionalSubjectAND(Subject subject, int indexOptional) {
        if (indexOptional < this.optionalSubjectsAND.size()) {
            optionalSubjectsAND.get(indexOptional).add(subject);
        }
    }

    public void addDescriptionCompulsory(String description) {
        this.descriptionCompulsory.add(description);
    }

    public void setMainDescriptionOptionalSubjects(String des) {
        this.mainDescriptionOptionalSubjects = des;
    }

    public void setMainDescriptionOptionalSubjectsAND(String des) {
        this.mainDescriptionOptionalSubjectsAND = des;
    }

    public void addDescriptionOptional(String description) {
        this.descriptionOptionals.add(description);
    }

    public void addDescriptionOptionalAND(String description) {
        this.descriptionOptionalsAND.add(description);
    }
}
