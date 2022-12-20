package code.objects;

import java.util.List;
import code.curriculum.Data;
import code.file_handler.WriteFile;

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

    public int getMaxSemester() {
        int max1 = 0;
        for (Subject subject : getSubjects()) {
            max1 = Math.max(max1, subject.getSemester());
        }
        return max1;
    }

    public int getMaxNumberSubjectInSameLevelAndSemester() {
        int max1 = 0;
        int[] count = new int[Math.max(getMaxLevel(), getMaxSemester()) + 1];
        for (Subject subject : getSubjects()) {
            int level = subject.getLevel();
            int semester = subject.getSemester();
            if (semester == 0) {
                count[level] += 1;
                max1 = Math.max(max1, count[level]);
            } else {
                count[semester] += 1;
                max1 = Math.max(max1, count[semester]);
            }
        }
        return max1;
    }

    public int getNumberSubjectLevelXOrSemesterX(int levelX) {
        int count = 0;
        for (Subject subject : getSubjects()) {
            int lvl = subject.getLevel();
            int semester = subject.getSemester();
            if (semester == 0) {
                if (lvl == levelX) {
                    count++;
                }
            } else {
                if (semester == levelX) {
                    count++;
                }
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

    // Get perfect index of column for this subject, and get number of connect of
    // this subject with above subjects(subject which has index row smaller and that
    // subject has relative with this subjec (has line))
    public int[] getPerfectColumnIndexAndCountConnection(Subject subject, Subject[][] matrixSubject) {
        double[] totalDistanceTop = new double[matrixSubject[0].length];
        double[] totalDistanceCenter = new double[matrixSubject[0].length];
        double[] totalDistanceBottom = new double[matrixSubject[0].length];
        int mainRow = subject.getRowIndexInMap();
        int countTop = 0;
        int countCenter = 0;
        int countBottom = 0;
        // Find minIndex and maxIndex
        for (int row = 0; row < matrixSubject.length; row++) {
            for (int column = 0; column < matrixSubject[0].length; column++) {
                // If 2 subject has connection (line)
                Subject tempSubject = matrixSubject[row][column];
                if (tempSubject != null && !tempSubject.getCode().equals(subject.getCode())
                        && (subject.getParentSubjectCodesByList().contains(tempSubject.getCode())
                                || tempSubject.getParentSubjectCodesByList()
                                        .contains(subject.getCode()))) {
                    for (int index = 0; index < totalDistanceTop.length; index++) {
                        if (row < mainRow) {
                            totalDistanceTop[index] += Math
                                    .sqrt((mainRow - row) * (mainRow - row) + (index - column) * (index - column));
                        } else if (row == mainRow) {
                            totalDistanceCenter[index] += Math
                                    .sqrt((mainRow - row) * (mainRow - row) + (index - column) * (index - column));
                        } else if (row > mainRow) {
                            totalDistanceBottom[index] += Math
                                    .sqrt((mainRow - row) * (mainRow - row) + (index - column) * (index - column));
                        }

                    }
                    if (row < mainRow) {
                        countTop++;
                    } else if (row == mainRow) {
                        countCenter++;
                    } else if (row > mainRow) {
                        countBottom++;
                    }
                }
            }
        }

        // Get perfect index in a row
        int perfectIndexTop = -1;
        double minTotalDistanceTop = 10000000;
        int perfectIndexCenter = -1;
        double minTotalDistanceCenter = 10000000;
        int perfectIndexBottom = -1;
        double minTotalDistanceBottom = 10000000;
        for (int index = 0; index < totalDistanceTop.length; index++) {
            if (totalDistanceTop[index] <= minTotalDistanceTop) {
                minTotalDistanceTop = totalDistanceTop[index];
                perfectIndexTop = index;
            }
            if (totalDistanceCenter[index] <= minTotalDistanceCenter) {
                minTotalDistanceCenter = totalDistanceCenter[index];
                perfectIndexCenter = index;
            }
            if (totalDistanceBottom[index] <= minTotalDistanceBottom) {
                minTotalDistanceBottom = totalDistanceBottom[index];
                perfectIndexBottom = index;
            }
        }
        if (countBottom == 0) {
            perfectIndexBottom = -1;
        }
        if (countCenter == 0) {
            perfectIndexCenter = Math.max(-1, perfectIndexBottom);
        }
        if (countTop == 0) {
            perfectIndexTop = Math.max(-1, perfectIndexBottom);
        }

        return new int[] { perfectIndexTop, countTop, perfectIndexBottom,
                countBottom, perfectIndexCenter, countCenter };
    }

    // A valid map: All subject has valid coordinate in map
    public boolean checkValidMap() {
        for (Subject subject : this.subjects) {
            if (!subject.hasValidCoordinateInMap()) {
                for (Subject subject1 : subjects) {
                    subject1.setRowIndexSorted(-1);
                    subject1.setColumnIndexSorted(-1);
                }
                return false;
            }
        }
        return true;
    }

    public void sortMatrixSubject(int[] rows, int[] columns, int maxRowIndex, int maxColumnIndex, int indexPlan) {
        // Create matrix subject
        Subject[][] matrixSubject = new Subject[maxRowIndex + 1][maxColumnIndex + 1];
        // Fill matrix with value null
        for (int indexRow = 0; indexRow < matrixSubject.length; indexRow++) {
            for (int indexColumn = 0; indexColumn < matrixSubject[0].length; indexColumn++) {
                matrixSubject[indexRow][indexColumn] = null;
            }
        }
        // Create data for matrix
        for (int index = 0; index < subjects.size(); index++) {
            matrixSubject[rows[index]][columns[index]] = subjects.get(index);
        }

        // Start sort matrix
        for (int row = 0; row <= maxRowIndex; row++) {
            for (int column = 0; column <= maxColumnIndex; column++) {
                // This is main subject which we are sorting
                Subject tempSubject = matrixSubject[row][column];
                if (tempSubject == null) {
                    continue;
                }
                // Get perfect index of column of tempSubject
                int[] infor = getPerfectColumnIndexAndCountConnection(tempSubject,
                        matrixSubject);

                int tempColumn = column;
                int tempRow = row;

                // Find index to swap
                int indexSwap = tempColumn;
                int abs = 0;
                if (infor[0] == -1) {
                    infor[0] = maxColumnIndex / 2;
                }
                while (abs <= Math.abs(infor[0] - tempColumn)) {
                    int[] values = { infor[0] - abs, infor[0] + abs };
                    for (int tempIndexSwap : values) {
                        // invalid index
                        if (tempIndexSwap < 0 || tempIndexSwap >= matrixSubject[0].length) {
                            continue;
                        }

                        if (matrixSubject[tempRow][tempIndexSwap] == null) {
                            indexSwap = tempIndexSwap;
                            break; // FOund tempIndexSwap
                        }

                        Subject subjectA = matrixSubject[tempRow][tempIndexSwap];
                        int indexColumnA = tempIndexSwap;
                        int[] inforA = getPerfectColumnIndexAndCountConnection(subjectA, matrixSubject);

                        for (int local = 0; local < 2; local++) { // 0: top, 1: bottom (not check center)
                            if (inforA[local * 2] == -1) { // If subject A has 0 connection in this local
                                if (infor[local * 2] == -1) { // If temp subject has 0 connection in this local
                                    continue; // Check next local
                                } else {
                                    indexSwap = tempIndexSwap;
                                    break; // Found indexSwap
                                }
                            } else { // If subject A has > 0 connection in this local
                                if (infor[local * 2] == -1) { // If temp subject has 0 connection in this local
                                    break; // Stop check, can't swap temp subject with subject A
                                } else { // If temp subject has > 0 connection in this local
                                    // if tempColumn is near perfectLocalA than indexcolumnA
                                    if (Math.abs(tempColumn - inforA[local * 2]) <= Math
                                            .abs(indexColumnA - inforA[local * 2])) {
                                        indexSwap = tempIndexSwap;
                                        break; // Found indexSwap
                                    } else {
                                        // Compare number connection
                                        if (infor[local * 2 + 1] > inforA[local * 2 + 1]) { // If tempSubject has number
                                                                                            // connection > subject A
                                            indexSwap = tempIndexSwap;
                                            break; // Found indexSwap
                                        } else if (infor[local * 2 + 1] == inforA[local * 2 + 1]) { // If tempSubject
                                                                                                    // has number
                                                                                                    // connection =
                                                                                                    // subject A
                                            // Do nothing
                                        } else { // If tempSubject has number
                                                 // connection < subject A
                                            break; // Can't swap tempSubject with subject A
                                        }
                                    }
                                }
                            }
                        }

                    }

                    if (indexSwap != tempColumn) {
                        break;
                    }
                    // Check next
                    abs++;
                }

                // swap two subject
                Subject subjectA = matrixSubject[tempRow][indexSwap];
                if (subjectA == null) {
                    // Update coordinate in matrix
                    matrixSubject[tempRow][tempColumn] = null;
                    matrixSubject[tempRow][indexSwap] = tempSubject;
                } else {
                    // Update coordinate in matrix
                    matrixSubject[tempRow][tempColumn] = subjectA;
                    matrixSubject[tempRow][indexSwap] = tempSubject;
                }
            }
        }

        // Save new data to file
        for (int row = 0; row <= maxRowIndex; row++) {
            for (int column = 0; column <= maxColumnIndex; column++) {
                Subject tempSubject = matrixSubject[row][column];
                if (tempSubject != null) {
                    int indexSubject = getIndexOfSubject(tempSubject);
                    tempSubject.setRowIndexSorted(row);
                    tempSubject.setColumnIndexSorted(column);
                    WriteFile.editSubject(indexPlan, indexSubject, tempSubject);
                }
            }
        }

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
