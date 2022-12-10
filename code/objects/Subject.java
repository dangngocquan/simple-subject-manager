package code.objects;

import java.util.LinkedList;
import java.util.List;
import java.awt.Color;

public class Subject {
    // Constant state of subject
    public static int STILL_NOT_REGISTER = 0;
    public static int GOING_TO_REGISTER = 1;
    public static int REGISTERED = 2;
    public static int COMPLETED = 3;

    // Properties
    private String subjectName;
    private String subjectCode;
    private int numberCredits;
    private List<Subject[]> parentSubjects; // prerequisite subjects
    private List<String[]> parentSubjectCodes;
    private int state = 0;
    private String characterScore = "";
    private double score10 = -1.0;
    private double score4 = -1.0;
    private Color color = new Color(255, 255, 255);

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

    public int getState() {
        if (!this.characterScore.isEmpty()) {
            setState(Subject.COMPLETED);
        }
        return this.state;
    }

    public String getCharacterScore() {
        return this.characterScore;
    }

    public Color getColor() {
        return this.color;
    }

    public double getScore10() {
        return this.score10;
    }

    public String getStringScore10() {
        if (score10 < 0) {
            return "";
        }
        return score10 + "";
    }

    public String getStringScore4() {
        if (score4 < 0) {
            return "";
        }
        return score4 + "";
    }

    public String getStringStatus() {
        switch (getState()) {
            case 0:
                return "Chưa đăng kí";
            case 1:
                return "Dự định đăng kí";
            case 2:
                return "Đã đăng kí";
            case 3:
                return "Đã hoàn thành";
        }
        return "";
    }

    public double getScore4() {
        return this.score4;
    }

    public boolean canRegister() {
        for (Subject[] parentSubjects : getParentSubjects()) {
            boolean flag = false;
            for (Subject parentSubject : parentSubjects) {
                if (parentSubject.getState() == Subject.COMPLETED) {
                    flag = true;
                }
            }
            if (flag == false) {
                return false;
            }
        }
        return true;
    }

    public String getAdvice() {
        if (canRegister()) {
            return "Có thể đăng kí. Không có học phần tiên quyết / Đã hoàn thành các học phần tiên quyết.";
        } else {
            return "Chưa nên đăng kí. Chưa hoàn thành các học phần tiên quyết.";
        }
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

    public void setState(int state) {
        this.state = state;
    }

    public void setCharacterScore(String score) {
        this.characterScore = score;
    }

    public void setColor(int r, int g, int b) {
        this.color = new Color(r, g, b);
    }

    public void setScore10(double value) {
        this.score10 = value;
    }

    public void setScore4(double value) {
        this.score4 = value;
    }
}
