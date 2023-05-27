package src2.object;

import java.util.LinkedList;
import java.util.List;

public class ConversionTable {
    private String name;
    private List<Double> score10;
    private List<Double> score4;
    private List<String> characterScore;

    public ConversionTable(String name) {
        this.name = name;
        score10 = new LinkedList<Double>();
        score4 = new LinkedList<Double>();
        characterScore = new LinkedList<String>();
    }

    // Getter
    public String getName() {
        return this.name;
    }

    public List<Double> getScore10() {
        return this.score10;
    }

    public List<Double> getScore4() {
        return this.score4;
    }

    public List<String> getStringScore4() {
        List<String> lst = new LinkedList<>();
        for (Double value : this.score4) {
            lst.add(value + "");
        }
        return lst;
    }

    public String[] getArrayStringScore4() {
        String[] arr = new String[this.score4.size()];
        for (int i = 0; i < characterScore.size(); i++) {
            arr[i] = score4.get(i) + "";
        }
        return arr;
    }

    public List<String> getCharacterScore() {
        return this.characterScore;
    }

    public String[] getArrayCharacterScore() {
        String[] arr = new String[this.characterScore.size()];
        for (int i = 0; i < characterScore.size(); i++) {
            arr[i] = characterScore.get(i);
        }
        return arr;
    }

    public String convert10ToAlpha(double score10) {
        String ans = "";
        for (int i = 0; i < this.score10.size() - 1; i++) {
            if (score10 >= this.score10.get(i)) {
                ans = characterScore.get(i);
            }
        }
        return ans;
    }

    public double convertAlphaTo4(String alpha) {
        for (int i = 0; i < characterScore.size(); i++) {
            if (characterScore.get(i).equals(alpha)) {
                return score4.get(i);
            }
        }
        return -1.0;
    }

    public double covert10To4(double score10) {
        return convertAlphaTo4(convert10ToAlpha(score10));
    }

    public String convert4ToAlpha(double score4) {
        for (int i = 0; i < this.score4.size(); i++) {
            if (this.score4.get(i) == score4) {
                return characterScore.get(i);
            }
        }
        return "";
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    public void setScore10(List<Double> score10) {
        this.score10 = score10;
    }

    public void setScore10(double[] score10) {
        List<Double> lst = new LinkedList<>();
        for (double a : score10) {
            lst.add(a);
        }
        setScore10(lst);
    }

    public void setScore10(String[] score10) {
        List<Double> lst = new LinkedList<>();
        for (String a : score10) {
            lst.add(Double.parseDouble(a));
        }
        setScore10(lst);
    }

    public void setScore4(List<Double> score4) {
        this.score4 = score4;
    }

    public void setScore4(double[] score4) {
        List<Double> lst = new LinkedList<>();
        for (double a : score4) {
            lst.add(a);
        }
        setScore4(lst);
    }

    public void setScore4(String[] score4) {
        List<Double> lst = new LinkedList<>();
        for (String a : score4) {
            lst.add(Double.parseDouble(a));
        }
        setScore4(lst);
    }

    public void setCharacterScore(List<String> characterScore) {
        this.characterScore = characterScore;
    }

    public void setCharacterScore(String[] characterScore) {
        List<String> lst = new LinkedList<String>();
        for (String str : characterScore) {
            lst.add(str);
        }
        setCharacterScore(lst);
    }
}
