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

    public int getTotalCredits() {
        int ans = 0;
        for (Subject subject : getSubjects()) {
            ans += subject.getNumberCredits();
        }
        return ans;
    }

    public int getNumberSubjectCompleted() {
        int ans = 0;
        for (Subject subject : getSubjects()) {
            if (subject.getState() == Subject.COMPLETED) {
                ans++;
            }
        }
        return ans;
    }

    public int getNumberCreditCompleted() {
        int ans = 0;
        for (Subject subject : getSubjects()) {
            if (subject.getState() == Subject.COMPLETED) {
                ans += subject.getNumberCredits();
            }
        }
        return ans;
    }

    public int getNumberSubjectGoingToRegister() {
        int ans = 0;
        for (Subject subject : getSubjects()) {
            if (subject.getState() == Subject.GOING_TO_REGISTER) {
                ans++;
            }
        }
        return ans;
    }

    public int getNumberCreditGoingToRegister() {
        int ans = 0;
        for (Subject subject : getSubjects()) {
            if (subject.getState() == Subject.GOING_TO_REGISTER) {
                ans += subject.getNumberCredits();
            }
        }
        return ans;
    }

    public int getNumberSubjectRegistered() {
        int ans = 0;
        for (Subject subject : getSubjects()) {
            if (subject.getState() == Subject.REGISTERED) {
                ans++;
            }
        }
        return ans;
    }

    public int getNumberCreditRegistered() {
        int ans = 0;
        for (Subject subject : getSubjects()) {
            if (subject.getState() == Subject.REGISTERED) {
                ans += subject.getNumberCredits();
            }
        }
        return ans;
    }

    public int getMaxLevel() {
        int max1 = 0;
        for (Subject subject : getSubjects()) {
            max1 = Math.max(max1, subject.getLevel());
        }
        return max1;
    }

    public int getMaxNumberSubjectInSameLevel() {
        int max1 = 0;
        int[] count = new int[getMaxLevel() + 1];
        for (Subject subject : getSubjects()) {
            int level = subject.getLevel();
            count[level] += 1;
            max1 = Math.max(max1, count[level]);
        }
        return max1;
    }

    public int getNumberSubjectLevelX(int level) {
        int count = 0;
        for (Subject subject : getSubjects()) {
            int lvl = subject.getLevel();
            if (lvl == level) {
                count++;
            }
        }
        return count;
    }

    public int getIndexOfSubject(Subject subject) {
        for (int i = 0; i < getSubjects().size(); i++) {
            if (subject.getCode().equals(getSubjects().get(i).getCode())) {
                return i;
            }
        }
        return -1;
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
