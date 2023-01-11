package src.objects;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import src.launcher.Data;
import src.launcher.WriteFile;

public class Plan {
    private String name;
    private List<Subject> subjects;
    private ConversionTable conversionTable;
    private int indexConversionTable;
    private String schoolName = "", departmentName = "", majorName = "";
    private TimeTable timeTable = null;

    public Plan(String name, List<Subject> subjects, int index) {
        this.name = name;
        this.subjects = subjects;
        this.indexConversionTable = index;
        this.conversionTable = Data.CONVERSIONS.getConversionTables().get(index);
        this.timeTable = new TimeTable();
    }

    // Getter
    public TimeTable getTimeTable() {
        return this.timeTable;
    }

    public String getName() {
        return this.name;
    }

    public String getSchoolName() {
        return this.schoolName;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    public String getMajorName() {
        return this.majorName;
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

    public int getMaxIndexColumnSorted() {
        int max1 = 0;
        for (Subject subject : subjects) {
            max1 = Math.max(max1, subject.getColumnIndexSorted());
        }
        return max1;
    }

    public int getMaxIndexRowSorted() {
        int max1 = 0;
        for (Subject subject : subjects) {
            max1 = Math.max(max1, subject.getRowIndexSorted());
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

    // // Get perfect index of column for this subject, and get number of connect of
    // // this subject with above subjects(subject which has index row smaller and
    // that
    // // subject has relative with this subjec (has line))
    // public int[] getPerfectColumnIndexAndCountConnection(Subject subject,
    // Subject[][] matrixSubject) {
    // double[] totalDistanceTop = new double[matrixSubject[0].length];
    // double[] totalDistanceCenter = new double[matrixSubject[0].length];
    // double[] totalDistanceBottom = new double[matrixSubject[0].length];
    // int mainRow = subject.getRowIndexInMap();
    // int countTop = 0;
    // int countCenter = 0;
    // int countBottom = 0;
    // // Find minIndex and maxIndex
    // for (int row = 0; row < matrixSubject.length; row++) {
    // for (int column = 0; column < matrixSubject[0].length; column++) {
    // // If 2 subject has connection (line)
    // Subject tempSubject = matrixSubject[row][column];
    // if (tempSubject != null && !tempSubject.getCode().equals(subject.getCode())
    // && (subject.getParentSubjectCodesByList().contains(tempSubject.getCode())
    // || tempSubject.getParentSubjectCodesByList()
    // .contains(subject.getCode()))) {
    // for (int index = 0; index < totalDistanceTop.length; index++) {
    // if (row < mainRow) {
    // totalDistanceTop[index] += Math
    // .sqrt((mainRow - row) * (mainRow - row) + (index - column) * (index -
    // column));
    // } else if (row == mainRow) {
    // totalDistanceCenter[index] += Math
    // .sqrt((mainRow - row) * (mainRow - row) + (index - column) * (index -
    // column));
    // } else if (row > mainRow) {
    // totalDistanceBottom[index] += Math
    // .sqrt((mainRow - row) * (mainRow - row) + (index - column) * (index -
    // column));
    // }

    // }
    // if (row < mainRow) {
    // countTop++;
    // } else if (row == mainRow) {
    // countCenter++;
    // } else if (row > mainRow) {
    // countBottom++;
    // }
    // }
    // }
    // }

    // // Get perfect index in a row
    // int perfectIndexTop = -1;
    // double minTotalDistanceTop = 10000000;
    // int perfectIndexCenter = -1;
    // double minTotalDistanceCenter = 10000000;
    // int perfectIndexBottom = -1;
    // double minTotalDistanceBottom = 10000000;
    // for (int index = 0; index < totalDistanceTop.length; index++) {
    // if (totalDistanceTop[index] <= minTotalDistanceTop) {
    // minTotalDistanceTop = totalDistanceTop[index];
    // perfectIndexTop = index;
    // }
    // if (totalDistanceCenter[index] <= minTotalDistanceCenter) {
    // minTotalDistanceCenter = totalDistanceCenter[index];
    // perfectIndexCenter = index;
    // }
    // if (totalDistanceBottom[index] <= minTotalDistanceBottom) {
    // minTotalDistanceBottom = totalDistanceBottom[index];
    // perfectIndexBottom = index;
    // }
    // }
    // if (countBottom == 0) {
    // perfectIndexBottom = -1;
    // }
    // if (countCenter == 0) {
    // perfectIndexCenter = Math.max(-1, perfectIndexBottom);
    // }
    // if (countTop == 0) {
    // perfectIndexTop = Math.max(-1, perfectIndexBottom);
    // }

    // return new int[] { perfectIndexTop, countTop, perfectIndexBottom,
    // countBottom, perfectIndexCenter, countCenter };
    // }

    // SORT MAP ALGORITHM 1
    // public void sortMap1(int[] rows, int[] columns, int maxRowIndex, int
    // maxColumnIndex, int indexPlan) {
    // // Create matrix subject
    // Subject[][] matrixSubject = new Subject[maxRowIndex + 1][maxColumnIndex + 1];
    // // Fill matrix with value null
    // for (int indexRow = 0; indexRow < matrixSubject.length; indexRow++) {
    // for (int indexColumn = 0; indexColumn < matrixSubject[0].length;
    // indexColumn++) {
    // matrixSubject[indexRow][indexColumn] = null;
    // }
    // }
    // // Create data for matrix
    // for (int index = 0; index < subjects.size(); index++) {
    // matrixSubject[rows[index]][columns[index]] = subjects.get(index);
    // }

    // // Start sort matrix
    // for (int row = 0; row <= maxRowIndex; row++) {
    // for (int column = 0; column <= maxColumnIndex; column++) {
    // // This is main subject which we are sorting
    // Subject tempSubject = matrixSubject[row][column];
    // if (tempSubject == null) {
    // continue;
    // }
    // // Get perfect index of column of tempSubject
    // int[] infor = getPerfectColumnIndexAndCountConnection(tempSubject,
    // matrixSubject);

    // int tempColumn = column;
    // int tempRow = row;

    // // Find index to swap
    // int indexSwap = tempColumn;
    // int abs = 0;
    // if (infor[0] == -1) {
    // infor[0] = maxColumnIndex / 2;
    // }
    // while (abs <= Math.abs(infor[0] - tempColumn)) {
    // int[] values = { infor[0] - abs, infor[0] + abs };
    // for (int tempIndexSwap : values) {
    // // invalid index
    // if (tempIndexSwap < 0 || tempIndexSwap >= matrixSubject[0].length) {
    // continue;
    // }

    // if (matrixSubject[tempRow][tempIndexSwap] == null) {
    // indexSwap = tempIndexSwap;
    // break; // FOund tempIndexSwap
    // }

    // Subject subjectA = matrixSubject[tempRow][tempIndexSwap];
    // int indexColumnA = tempIndexSwap;
    // int[] inforA = getPerfectColumnIndexAndCountConnection(subjectA,
    // matrixSubject);

    // for (int local = 0; local < 2; local++) { // 0: top, 1: bottom (not check
    // center)
    // if (inforA[local * 2] == -1) { // If subject A has 0 connection in this local
    // if (infor[local * 2] == -1) { // If temp subject has 0 connection in this
    // local
    // continue; // Check next local
    // } else {
    // indexSwap = tempIndexSwap;
    // break; // Found indexSwap
    // }
    // } else { // If subject A has > 0 connection in this local
    // if (infor[local * 2] == -1) { // If temp subject has 0 connection in this
    // local
    // break; // Stop check, can't swap temp subject with subject A
    // } else { // If temp subject has > 0 connection in this local
    // // if tempColumn is near perfectLocalA than indexcolumnA
    // if (Math.abs(tempColumn - inforA[local * 2]) <= Math
    // .abs(indexColumnA - inforA[local * 2])) {
    // indexSwap = tempIndexSwap;
    // break; // Found indexSwap
    // } else {
    // // Compare number connection
    // if (infor[local * 2 + 1] > inforA[local * 2 + 1]) { // If tempSubject has
    // number
    // // connection > subject A
    // indexSwap = tempIndexSwap;
    // break; // Found indexSwap
    // } else if (infor[local * 2 + 1] == inforA[local * 2 + 1]) { // If tempSubject
    // // has number
    // // connection =
    // // subject A
    // // Do nothing
    // } else { // If tempSubject has number
    // // connection < subject A
    // break; // Can't swap tempSubject with subject A
    // }
    // }
    // }
    // }
    // }

    // }

    // if (indexSwap != tempColumn) {
    // break;
    // }
    // // Check next
    // abs++;
    // }

    // // swap two subject
    // Subject subjectA = matrixSubject[tempRow][indexSwap];
    // if (subjectA == null) {
    // // Update coordinate in matrix
    // matrixSubject[tempRow][tempColumn] = null;
    // matrixSubject[tempRow][indexSwap] = tempSubject;
    // } else {
    // // Update coordinate in matrix
    // matrixSubject[tempRow][tempColumn] = subjectA;
    // matrixSubject[tempRow][indexSwap] = tempSubject;
    // }
    // }
    // }

    // // Save new data to file
    // for (int row = 0; row <= maxRowIndex; row++) {
    // for (int column = 0; column <= maxColumnIndex; column++) {
    // Subject tempSubject = matrixSubject[row][column];
    // if (tempSubject != null) {
    // int indexSubject = getIndexOfSubject(tempSubject);
    // tempSubject.setRowIndexSorted(row);
    // tempSubject.setColumnIndexSorted(column);
    // WriteFile.editSubject(indexPlan, indexSubject, tempSubject);
    // }
    // }
    // }

    // }

    // // SORT MAP ALGORITHM 2
    // public void sortMap(int[] rows, int[] columns, int indexPlan) {
    // if (subjects.size() == 0) {
    // return;
    // }

    // // Create initial matrix subject - this matrix save default data of
    // coordinate,
    // // not changed during this method
    // // subjects in map
    // Subject[][] dataMatrix = new Subject[subjects.size()][subjects.size()];
    // // Fill matrix with initial value null
    // for (int indexRow = 0; indexRow < dataMatrix.length; indexRow++) {
    // for (int indexColumn = 0; indexColumn < dataMatrix[0].length; indexColumn++)
    // {
    // dataMatrix[indexRow][indexColumn] = null;
    // }
    // }
    // // Create data for matrix
    // for (int index = 0; index < subjects.size(); index++) {
    // dataMatrix[rows[index]][columns[index]] = subjects.get(index);
    // // System.out.println(subjects.get(index).getCode() + " " + rows[index] + " "
    // +
    // // columns[index]);
    // }

    // // This is matrix subject save coordinate of subjects after sort
    // Subject[][] matrix = new Subject[subjects.size()][subjects.size()];
    // // Matrix status of 'matrix'
    // boolean[][] isEmptyCell = new boolean[subjects.size()][subjects.size()];
    // // Status of subject
    // boolean[] isSorted = new boolean[subjects.size()];
    // // Fill initial value for 'matrix' and 'isEmptyCell'
    // for (int indexRow = 0; indexRow < matrix.length; indexRow++) {
    // for (int indexColumn = 0; indexColumn < matrix[0].length; indexColumn++) {
    // matrix[indexRow][indexColumn] = null;
    // isEmptyCell[indexRow][indexColumn] = false;
    // }
    // }
    // // Fill initial value for 'isSorted'
    // for (int index = 0; index < subjects.size(); index++) {
    // isSorted[index] = false;
    // }

    // // Start fill subject for 'matrix'
    // int tempRootColumn = 0;
    // for (int indexRow = 0; indexRow < dataMatrix.length; indexRow++) {
    // for (int indexColumn = 0; indexColumn < dataMatrix[0].length; indexColumn++)
    // {
    // // get subject in current coordinate - 'mainSubject'
    // Subject mainSubject = dataMatrix[indexRow][indexColumn];
    // // If null, check next coordinate
    // if (mainSubject == null) {
    // continue;
    // }

    // if (isSorted[getIndexOfSubject(mainSubject)]) {
    // continue;
    // }

    // System.out.println(mainSubject.getCode() + " " + indexRow + " " +
    // indexColumn);

    // // Get subjects has connect with 'mainSubject' and under 'mainSubject' (has
    // // bottom coordinate) and still not be sorted (isSorted == false)
    // List<List<Subject>> subjectsConnection = new LinkedList<List<Subject>>();
    // List<String> parentSubjectCodeOfMainSubject =
    // mainSubject.getParentSubjectCodesByList();
    // int totalConnects = 0;
    // for (int r = 0; r < subjects.size(); r++) {
    // List<Subject> connectSubjectsInRow = new LinkedList<Subject>();
    // subjectsConnection.add(connectSubjectsInRow);
    // if (r <= indexRow) {
    // continue;
    // }
    // // Check subject in current row
    // for (int c = 0; c < subjects.size(); c++) {
    // // Get subject in current coordinate
    // Subject tempSubject = dataMatrix[r][c];
    // // If null, check next column
    // if (tempSubject == null) {
    // continue;
    // }

    // // Check connection of 'tempSubject' with 'mainSubject'
    // if (!isSorted[getIndexOfSubject(tempSubject)]
    // && (tempSubject.getParentSubjectCodesByList().contains(mainSubject.getCode())
    // || parentSubjectCodeOfMainSubject.contains(tempSubject.getCode()))) {
    // connectSubjectsInRow.add(tempSubject);
    // totalConnects++;
    // }
    // }
    // }

    // // create coordinate for subjects
    // // coordiante for 'mainSubject'
    // int colMainSubject = tempRootColumn + totalConnects / 2;
    // matrix[indexRow][colMainSubject] = mainSubject;
    // isSorted[getIndexOfSubject(mainSubject)] = true;
    // if (totalConnects == 0) {
    // tempRootColumn++;
    // continue;
    // }
    // // coordinate for each subject in 'subjectsConnection'
    // int[] arrIndexColumn = new int[totalConnects];
    // for (int i = 0; i < totalConnects; i++) {
    // if (i % 2 == 0) {
    // arrIndexColumn[i] = i / 2;
    // } else {
    // arrIndexColumn[i] = totalConnects - 1 - i / 2;
    // }
    // }
    // int tempCount = 0;
    // for (int r = 0; r < subjectsConnection.size(); r++) {
    // for (int index = 0; index < subjectsConnection.get(r).size(); index++) {
    // Subject subject = subjectsConnection.get(r).get(index);
    // matrix[r][tempRootColumn + arrIndexColumn[tempCount]] = subject;
    // isSorted[getIndexOfSubject(subject)] = true;
    // tempCount++;
    // }
    // }

    // // Update tempRootColumn
    // tempRootColumn += totalConnects;
    // }
    // }

    // // Save new data to file
    // int count = 0;
    // for (int indexRow = 0; indexRow < subjects.size(); indexRow++) {
    // String line = "";
    // for (int indexColumn = 0; indexColumn < subjects.size(); indexColumn++) {
    // Subject subject = matrix[indexRow][indexColumn];
    // String str = "null";
    // if (subject != null) {
    // int indexSubject = getIndexOfSubject(subject);
    // subject.setRowIndexSorted(indexRow);
    // subject.setColumnIndexSorted(indexColumn);
    // WriteFile.editSubject(indexPlan, indexSubject, subject);
    // str = subject.getCode();
    // count++;
    // }
    // line += String.format("%10s", str);
    // }
    // // System.out.println(line);
    // }
    // // System.out.println(count);
    // }

    // // SORT MAP ALGORITHM 3
    // public void sortMap3(int[] rows, int[] columns, int indexPlan) {
    // if (subjects.size() == 0) {
    // return;
    // }

    // // Create initial matrix subject - this matrix save default data of
    // coordinate,
    // // not changed during this method
    // // subjects in map
    // Subject[][] dataMatrix = new Subject[subjects.size()][subjects.size()];
    // // Fill matrix with initial value null
    // for (int indexRow = 0; indexRow < dataMatrix.length; indexRow++) {
    // for (int indexColumn = 0; indexColumn < dataMatrix[0].length; indexColumn++)
    // {
    // dataMatrix[indexRow][indexColumn] = null;
    // }
    // }
    // // Create data for matrix
    // for (int index = 0; index < subjects.size(); index++) {
    // dataMatrix[rows[index]][columns[index]] = subjects.get(index);
    // }

    // // Start fill subject for 'matrix'
    // NodeInMap rootNode = new NodeInMap(null, -1, 0);
    // List<NodeInMap> existingNodes = new LinkedList<NodeInMap>();
    // // Create childNodes for 'rootNode' (is all subjects in the first row (this
    // row
    // // not null))

    // for (int indexRow = 0; indexRow < dataMatrix.length; indexRow++) {
    // for (int indexColumn = 0; indexColumn < dataMatrix[0].length; indexColumn++)
    // {
    // // get subject in current coordinate - 'mainSubject'
    // Subject mainSubject = dataMatrix[indexRow][indexColumn];
    // // If null, check next coordinate
    // if (mainSubject == null) {
    // continue;
    // }

    // // Create for 'mainSubject'
    // NodeInMap mainNode = new NodeInMap(mainSubject, indexRow, indexColumn);

    // // Check if this node has connection with any node existing
    // boolean hasConnection = false;

    // for (NodeInMap node : existingNodes) {
    // if (node.hasConnection(mainNode)) {
    // hasConnection = true;
    // node.addChildNode(mainNode);
    // mainNode.setParentNode(node);
    // break;
    // }
    // }

    // if (!hasConnection) {
    // rootNode.addChildNode(mainNode);
    // mainNode.setParentNode(rootNode);
    // }

    // existingNodes.add(mainNode);
    // }
    // }

    // // Save new data
    // for (NodeInMap node : existingNodes) {
    // Subject subject = node.getRootSubject();
    // subject.setRowIndexSorted(node.getIndexRow());
    // subject.setColumnIndexSorted(node.getIndexColumn());
    // }

    // // sort part 2 to better
    // // create matrix status
    // int[][] matrixStatus = new int[getMaxIndexRowSorted() +
    // 1][getMaxIndexColumnSorted() + 1];
    // // int[][] matrixCount = new int[getMaxIndexRowSorted() +
    // // 1][getMaxIndexColumnSorted() + 1];
    // Subject[][] matrixSubject = new
    // Subject[matrixStatus.length][matrixStatus[0].length];
    // for (Subject subject : subjects) {
    // int indexRowStart = subject.getRowIndexSorted();
    // int indexColumnStart = subject.getColumnIndexSorted();
    // matrixSubject[indexRowStart][indexColumnStart] = subject;
    // for (Subject parentSubject : subject.getParentSubjectsByList()) {
    // int indexRowEnd = parentSubject.getRowIndexSorted();
    // int indexColumnEnd = parentSubject.getColumnIndexSorted();
    // int r1 = Math.min(indexRowStart, indexRowEnd);
    // int r2 = Math.max(indexRowStart, indexRowEnd);
    // int c1 = Math.min(indexColumnStart, indexColumnEnd);
    // int c2 = Math.max(indexColumnStart, indexColumnEnd);
    // if (r1 == r2) {
    // for (int indexColumn = c1; indexColumn <= c2; indexColumn++) {
    // matrixStatus[r1][indexColumn] = 1;
    // }
    // } else if (r1 + 1 == r2) {
    // matrixStatus[r1][c1] = 1;
    // matrixStatus[r2][c2] = 1;
    // } else {
    // matrixStatus[r1][c1] = 1;
    // matrixStatus[r2][c2] = 1;
    // for (int indexRow = r1 + 1; indexRow <= r2 - 1; indexRow++) {
    // for (int indexColumn = c1; indexColumn <= c2; indexColumn++) {
    // matrixStatus[indexRow][indexColumn] = 1;
    // }
    // }
    // }

    // }
    // if (subject.getParentSubjectsByList().size() == 0) {
    // matrixStatus[indexRowStart][indexColumnStart] = 1;
    // }
    // }

    // // Start sort part 2

    // for (int indexRow = 0; indexRow < matrixStatus.length; indexRow++) {
    // int midIndexColumn = matrixStatus[indexRow].length / 2;
    // for (int indexColumn = midIndexColumn; indexColumn >= 0; indexColumn--) {
    // Subject subject = matrixSubject[indexRow][indexColumn];
    // if (subject != null && rootNode.contains(subject)) {
    // NodeInMap node = getNodeBySubject(existingNodes, subject);
    // if (node == null || node.getChildNodes().size() > 0) {
    // continue;
    // }
    // int indexColumnPerfect = indexColumn;
    // for (int index = midIndexColumn; index >= indexColumn; index--) {
    // if (matrixStatus[indexRow][index] == 0) {
    // indexColumnPerfect = index;
    // break;
    // }
    // }
    // subject.setColumnIndexSorted(indexColumnPerfect);
    // matrixSubject[indexRow][indexColumn] = null;
    // matrixSubject[indexRow][indexColumnPerfect] = subject;
    // matrixStatus[indexRow][indexColumn] = 0;
    // matrixStatus[indexRow][indexColumnPerfect] = 1;
    // }
    // }

    // for (int indexColumn = midIndexColumn + 1; indexColumn <
    // matrixStatus[indexRow].length; indexColumn++) {
    // Subject subject = matrixSubject[indexRow][indexColumn];
    // if (subject != null && rootNode.contains(subject)) {
    // NodeInMap node = getNodeBySubject(existingNodes, subject);
    // if (node == null || node.getChildNodes().size() > 0) {
    // continue;
    // }
    // int indexColumnPerfect = indexColumn;
    // for (int index = midIndexColumn + 1; index <= indexColumn; index++) {
    // if (matrixStatus[indexRow][index] == 0) {
    // indexColumnPerfect = index;
    // break;
    // }
    // }
    // subject.setColumnIndexSorted(indexColumnPerfect);
    // matrixSubject[indexRow][indexColumn] = null;
    // matrixSubject[indexRow][indexColumnPerfect] = subject;
    // matrixStatus[indexRow][indexColumn] = 0;
    // matrixStatus[indexRow][indexColumnPerfect] = 1;
    // }
    // }
    // }

    // // // Remove column full null
    // List<Integer> listIndexColumn = new LinkedList<>();
    // for (int indexColumn = 0; indexColumn < matrixSubject[0].length;
    // indexColumn++) {
    // boolean isFullNullColumn = true;
    // for (int indexRow = 0; indexRow < matrixSubject.length; indexRow++) {
    // if (matrixSubject[indexRow][indexColumn] != null) {
    // isFullNullColumn = false;
    // }

    // }
    // if (isFullNullColumn) {
    // listIndexColumn.add(0, indexColumn);
    // }
    // }

    // for (int indexColumnNull : listIndexColumn) {
    // for (int indexColumn = indexColumnNull + 1; indexColumn <
    // matrixSubject[0].length; indexColumn++) {
    // for (int indexRow = 0; indexRow < matrixSubject.length; indexRow++) {
    // Subject subject = matrixSubject[indexRow][indexColumn];
    // if (subject != null) {
    // subject.setColumnIndexSorted(indexColumn - 1);
    // }
    // matrixSubject[indexRow][indexColumn - 1] = subject;
    // matrixSubject[indexRow][indexColumn] = null;
    // }
    // }
    // }

    // // for (Subject subject : subjects) {
    // //
    // matrixCount[subject.getRowIndexSorted()][subject.getColumnIndexSorted()]++;
    // // }

    // // for (int i = 0; i < matrixCount.length; i++) {
    // // for (int j = 0; j < matrixCount[0].length; j++) {
    // // System.out.print((matrixCount[i][j]) + " ");
    // // }
    // // System.out.println();
    // // }

    // // Save new data to file
    // for (int indexSubject = 0; indexSubject < subjects.size(); indexSubject++) {
    // Subject subject = subjects.get(indexSubject);
    // subject.setRowIndexSorted(subject.getRowIndexSorted());
    // subject.setColumnIndexSorted(subject.getColumnIndexSorted());
    // WriteFile.editSubject(indexPlan, indexSubject, subject);
    // }
    // }

    // // Found node by subejct
    // public NodeInMap getNodeBySubject(List<NodeInMap> nodes, Subject subject) {
    // for (NodeInMap node : nodes) {
    // if (node.getRootSubject().getCode().equals(subject.getCode())) {
    // return node;
    // }
    // }
    // return null;
    // }

    // SORT MAP ALGORITHM 4
    public void sortMap4(int[] rows, int[] columns, int indexPlan) {
        List<GroupSubject> groupSubjects = new LinkedList<GroupSubject>();

        // Status of subjects
        boolean[] isGrouped = new boolean[subjects.size()];
        for (int index = 0; index < subjects.size(); index++) {
            isGrouped[index] = false;
        }

        // Start create group subject
        Queue<Subject> queue = new LinkedList<Subject>();
        for (int index = 0; index < subjects.size(); index++) {
            if (isGrouped[index]) {
                continue;
            }
            GroupSubject group = new GroupSubject();
            group.addSubject(subjects.get(index));
            queue.add(subjects.get(index));
            isGrouped[index] = true;
            while (queue.size() > 0) {
                Subject subject = queue.poll();
                List<Subject> nearSubjects = getParentAndChildSubjects(subject);
                for (Subject nearSubject : nearSubjects) {
                    int indexOfNearSubject = getIndexOfSubject(nearSubject);
                    if (!isGrouped[indexOfNearSubject]) {
                        group.addSubject(nearSubject);
                        queue.add(nearSubject);
                        isGrouped[indexOfNearSubject] = true;
                    }
                }
            }
            groupSubjects.add(group);
        }

        // SORT PART 1
        // sort each group and add to temp map
        List<Integer> listNumberColumn = new LinkedList<>();
        int tempIndexColumn = 0;
        for (GroupSubject group : groupSubjects) {
            group.setRoot(0, tempIndexColumn);
            tempIndexColumn += group.getMaxNumberColumn();
            listNumberColumn.add(group.getMaxNumberColumn());
        }

        // SORT PART 2 to better
        // Sort groups by size (max number column of each group)
        for (int turn = 0; turn < groupSubjects.size(); turn++) {
            for (int indexGroup = 0; indexGroup < groupSubjects.size() - 1; indexGroup++) {
                GroupSubject group1 = groupSubjects.get(indexGroup);
                GroupSubject group2 = groupSubjects.get(indexGroup + 1);
                int numberColumn1 = listNumberColumn.get(indexGroup);
                int numberColumn2 = listNumberColumn.get(indexGroup + 1);
                if (numberColumn1 < numberColumn2) {
                    groupSubjects.set(indexGroup, group2);
                    groupSubjects.set(indexGroup + 1, group1);
                    listNumberColumn.set(indexGroup, numberColumn2);
                    listNumberColumn.set(indexGroup + 1, numberColumn1);
                }
            }
        }

        // Start fill groups in new empty map
        List<GroupSubject> groups = new LinkedList<>();
        int[][] status = getMatrixStatus(groups);
        // Add each existing group to 'groups'
        for (GroupSubject group : groupSubjects) {
            boolean flag = false;
            int indexColumnRoot = 0;
            while (flag == false) {
                flag = true;
                group.setRoot(0, indexColumnRoot);
                groups.add(group);
                status = getMatrixStatus(groups);
                for (int i = 0; i < status.length; i++) {
                    for (int j = 0; j < status[0].length; j++) {
                        if (status[i][j] > 1) {
                            flag = false;
                            groups.remove(group);
                            indexColumnRoot++;
                            break;
                        }
                    }
                }
            }

        }

        // Save new data to file
        for (int indexSubject = 0; indexSubject < subjects.size(); indexSubject++) {
            Subject subject = subjects.get(indexSubject);
            subject.setRowIndexSorted(subject.getRowIndexSorted());
            subject.setColumnIndexSorted(subject.getColumnIndexSorted());
            WriteFile.editSubject(indexPlan, indexSubject, subject);
        }
    }

    // Get list parent subject and child subject of input subject
    public List<Subject> getParentAndChildSubjects(Subject subject) {
        List<Subject> listSubjects = new LinkedList<>();
        for (Subject subjectCheck : subjects) {
            if (subject.getParentSubjectCodesByList().contains(subjectCheck.getCode())
                    || subjectCheck.getParentSubjectCodesByList().contains(subject.getCode())) {
                listSubjects.add(subjectCheck);
            }
        }
        return listSubjects;
    }

    // Get matrix status
    public int[][] getMatrixStatus(List<GroupSubject> listGroup) {
        int[][] matrix = new int[getMaxIndexRowSorted() + 1][getMaxIndexColumnSorted() + 1];
        for (GroupSubject group : listGroup) {
            List<Integer> listIndexRowNotNull = group.getIndexRowNotNull();
            List<Integer[]> minAndMaxIndexColumnEachRow = group.getMinAndMaxIndexColumnEachRow();
            int tempIndexRow = listIndexRowNotNull.get(0);
            int tempMinIndexColumn = minAndMaxIndexColumnEachRow.get(0)[0];
            int tempMaxIndexColumn = minAndMaxIndexColumnEachRow.get(0)[1];
            if (listIndexRowNotNull.size() == 1) {
                // Fill 'true' value in one row
                for (int indexColumn = tempMinIndexColumn; indexColumn <= tempMaxIndexColumn; indexColumn++) {
                    matrix[tempIndexRow][indexColumn]++;
                }
            } else if (listIndexRowNotNull.size() == 2) {
                // fill 'true' value in first row
                for (int indexColumn = tempMinIndexColumn; indexColumn <= tempMaxIndexColumn; indexColumn++) {
                    matrix[tempIndexRow][indexColumn]++;
                }
                // Fill 'true' value in row which beetwen first row and second row
                tempMinIndexColumn = Math.min(minAndMaxIndexColumnEachRow.get(0)[0],
                        minAndMaxIndexColumnEachRow.get(1)[0]);
                tempMaxIndexColumn = Math.max(minAndMaxIndexColumnEachRow.get(0)[1],
                        minAndMaxIndexColumnEachRow.get(1)[1]);
                for (int indexRow = listIndexRowNotNull.get(0) + 1; indexRow < listIndexRowNotNull.get(1); indexRow++) {
                    for (int indexColumn = tempMinIndexColumn; indexColumn <= tempMaxIndexColumn; indexColumn++) {
                        matrix[indexRow][indexColumn]++;
                    }
                }
                // Fill 'true' value in second row
                tempIndexRow = listIndexRowNotNull.get(1);
                tempMinIndexColumn = minAndMaxIndexColumnEachRow.get(1)[0];
                tempMaxIndexColumn = minAndMaxIndexColumnEachRow.get(1)[1];
                for (int indexColumn = tempMinIndexColumn; indexColumn <= tempMaxIndexColumn; indexColumn++) {
                    matrix[tempIndexRow][indexColumn]++;
                }
            } else {
                // Each row from min index row to (max index row - 1)
                for (int indexRow = listIndexRowNotNull.get(0); indexRow < listIndexRowNotNull
                        .get(listIndexRowNotNull.size() - 1); indexRow++) {
                    // find tempMinIndexCOlumn and tempMaxIndexColumn
                    int indexInList = listIndexRowNotNull.indexOf(indexRow);
                    if (indexInList != -1) {
                        tempMinIndexColumn = Math.min(tempMinIndexColumn,
                                minAndMaxIndexColumnEachRow.get(indexInList)[0]);
                        tempMaxIndexColumn = Math.max(tempMaxIndexColumn,
                                minAndMaxIndexColumnEachRow.get(indexInList)[1]);
                    }
                    // Fill 'true' value
                    for (int indexColumn = tempMinIndexColumn; indexColumn <= tempMaxIndexColumn; indexColumn++) {
                        matrix[indexRow][indexColumn]++;
                    }
                }
                // last row (max index row in 'listIndexRowNotNull')
                int lastIndex = listIndexRowNotNull.size() - 1;
                tempMinIndexColumn = minAndMaxIndexColumnEachRow.get(lastIndex)[0];
                tempMaxIndexColumn = minAndMaxIndexColumnEachRow.get(lastIndex)[1];
                for (int indexColumn = tempMinIndexColumn; indexColumn <= tempMaxIndexColumn; indexColumn++) {
                    matrix[listIndexRowNotNull.get(lastIndex)][indexColumn]++;
                }
            }
        }
        return matrix;
    }

    // Setter
    public void setTimeTable(TimeTable times) {
        this.timeTable = times;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSchoolName(String name) {
        this.schoolName = name;
    }

    public void setDepartmentName(String name) {
        this.departmentName = name;
    }

    public void setMajorName(String name) {
        this.majorName = name;
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
