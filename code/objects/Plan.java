package code.objects;

import java.util.List;

import code.curriculum.Data;

public class Plan {
    private String name;
    private List<Subject> subjects;
    private ConversionTable conversionTable;
    private int indexConversionTable;

    public Plan(String name, List<Subject> subjects, int index) {
        this.name = name;
        this.subjects = subjects;
        this.indexConversionTable = index;
        this.conversionTable = Data.CONVERSIONS.getConversionTables().get(index);
    }

    // Getter
    public String getName() {
        return this.name;
    }

    public List<Subject> getSubjects() {
        return this.subjects;
    }

    public ConversionTable getConversionTable() {
        return this.conversionTable;
    }

    public int getIndexConversionTable() {
        return this.indexConversionTable;
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

    public void setConversionTable(ConversionTable conversionTable) {
        this.conversionTable = conversionTable;
    }

    public void setIndexConversionTable(int index) {
        this.indexConversionTable = index;
    }
}
