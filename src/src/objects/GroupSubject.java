package src.objects;

import java.util.LinkedList;
import java.util.List;

public class GroupSubject {
    private int indexRowRoot = 0, indexColumnRoot = 0;
    private List<Subject> subjects;

    // COnstructor
    public GroupSubject() {
        this.subjects = new LinkedList<Subject>();
    }

    // Getter
    public List<Subject> getSubjects() {
        return this.subjects;
    }

    public int[] getSizeEachIndexRow() {
        int[] sizeEachIndexRow = new int[getMaxIndexRow() + 1];
        for (Subject subject : subjects) {
            sizeEachIndexRow[subject.getRowIndexSorted()]++;
        }
        return sizeEachIndexRow;
    }

    public int getMaxNumberColumn() {
        int maxColumn = 0;
        int[] sizeEachIndexRow = getSizeEachIndexRow();
        for (int numberColumn : sizeEachIndexRow) {
            maxColumn = Math.max(maxColumn, numberColumn);
        }
        return maxColumn;
    }

    public int getMaxIndexRow() {
        int maxIndexRow = 0;
        for (Subject subject : subjects) {
            maxIndexRow = Math.max(subject.getRowIndexSorted(), maxIndexRow);
        }
        return maxIndexRow;
    }

    public List<Integer> getIndexRowNotNull() {
        List<Integer> listIndex = new LinkedList<>();
        int[] sizeEachIndexRow = getSizeEachIndexRow();
        for (int indexRow = 0; indexRow < sizeEachIndexRow.length; indexRow++) {
            if (sizeEachIndexRow[indexRow] != 0) {
                listIndex.add(indexRow);
            }
        }
        return listIndex;
    }

    public List<Integer[]> getMinAndMaxIndexColumnEachRow() {
        List<Integer> listIndexRowNotNull = getIndexRowNotNull();
        int[] minIndexColumnEachRow = new int[getMaxIndexRow() + 1];
        int[] maxIndexColumnEachRow = new int[minIndexColumnEachRow.length];
        for (int index = 0; index < minIndexColumnEachRow.length; index++) {
            minIndexColumnEachRow[index] = 10041004;
        }
        for (Subject subject : subjects) {
            int indexRow = subject.getRowIndexSorted();
            int indexColumn = subject.getColumnIndexSorted();
            minIndexColumnEachRow[indexRow] = Math.min(minIndexColumnEachRow[indexRow], indexColumn);
            maxIndexColumnEachRow[indexRow] = Math.max(maxIndexColumnEachRow[indexRow], indexColumn);
        }

        List<Integer[]> listMinAndMaxIndexColumnEachRow = new LinkedList<>();
        for (int indexRowNotNull : listIndexRowNotNull) {
            listMinAndMaxIndexColumnEachRow.add(new Integer[] {
                    minIndexColumnEachRow[indexRowNotNull],
                    maxIndexColumnEachRow[indexRowNotNull] });
        }

        return listMinAndMaxIndexColumnEachRow;
    }

    // Setter
    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }

    public void sort() {
        int[] sizeEachIndexRow = getSizeEachIndexRow();
        int maxColumn = 0;
        for (int numberColumn : sizeEachIndexRow) {
            maxColumn = Math.max(maxColumn, numberColumn);
        }
        int[] tempIndexColumn = new int[sizeEachIndexRow.length];
        for (int indexRow = 0; indexRow < sizeEachIndexRow.length; indexRow++) {
            tempIndexColumn[indexRow] = (maxColumn - sizeEachIndexRow[indexRow]) / 2;
        }
        for (Subject subject : subjects) {
            int indexRow = subject.getRowIndexSorted();
            subject.setColumnIndexSorted(tempIndexColumn[indexRow]);
            tempIndexColumn[indexRow]++;
        }
    }

    // Set index row root, index column root (only use this method after use 'sort'
    // method)
    public void setRoot(int indexRowRoot, int indexColumnRoot) {
        sort();
        this.indexRowRoot = indexRowRoot;
        this.indexColumnRoot = indexColumnRoot;
        for (Subject subject : subjects) {
            subject.setRowIndexSorted(subject.getRowIndexSorted() + this.indexRowRoot);
            subject.setColumnIndexSorted(subject.getColumnIndexSorted() + this.indexColumnRoot);
        }
    }

    // Check if the root can has input coordinate (matrixStatus still not have this
    // group)
    public boolean isValidRoot(int indexRowRoot, int indexColumnRoot, int[][] matrixStatus) {
        boolean flag = true;
        for (Subject subject : subjects) {
            int tempIndexRow = subject.getRowIndexSorted();
            int tempIndexColumn = subject.getColumnIndexSorted();
            int indexRowNew = tempIndexRow - this.indexRowRoot + indexRowRoot;
            int indexColumnNew = tempIndexColumn - this.indexColumnRoot + indexColumnRoot;
            // Check
            matrixStatus[indexRowNew][indexColumnNew]++;
            if (matrixStatus[indexRowNew][indexColumnNew] > 1) {
                flag = false;
            }
            // edit to old status
            matrixStatus[indexRowNew][indexColumnNew]--;
            if (flag == false) {
                break;
            }
        }
        return flag;
    }

}
