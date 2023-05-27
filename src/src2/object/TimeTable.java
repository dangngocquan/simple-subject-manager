package src2.object;

import java.util.LinkedList;
import java.util.List;

public class TimeTable {
    // Properties
    private List<Subject> subjects;
    private int maxLessonsPerDay;

    // Constructor
    public TimeTable() {
        this.subjects = new LinkedList<>();
        this.maxLessonsPerDay = 10;
    }

    // Getter
    public List<Subject> getSubjects() {
        return this.subjects;
    }

    public int getMaxLessonPerDay() {
        return this.maxLessonsPerDay;
    }

    public boolean contains(String subjectCode) {
        for (Subject subject : subjects) {
            if (subject.getCode().equals(subjectCode)) {
                return true;
            }
        }
        return false;
    }

    // Setter
    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }

    public void setMaxLessonPerDay(int value) {
        this.maxLessonsPerDay = value;
    }

}
