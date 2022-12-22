package code.objects;

import java.util.LinkedList;
import java.util.List;

public class NodeInMap {
    // properties
    private Subject rootSubject;
    private int indexRow, indexColumn;
    private List<NodeInMap> childNodes;
    private NodeInMap parentNode = null;
    private int indexInParentNode = 0;

    // Constructor
    public NodeInMap(Subject rootSubject, int indexRow, int indexColumn) {
        this.rootSubject = rootSubject;
        this.indexRow = indexRow;
        this.childNodes = new LinkedList<NodeInMap>();
    }

    // getter
    public Subject getRootSubject() {
        return rootSubject;
    }

    public int getIndexRow() {
        return this.indexRow;
    }

    public int getIndexColumn() {
        return this.indexColumn;
    }

    public List<NodeInMap> getChildNodes() {
        return this.childNodes;
    }

    public NodeInMap getParentNode() {
        return this.parentNode;
    }

    public int getWidth() {
        if (this.childNodes.size() == 0) {
            return 1;
        }
        int width = 0;
        for (NodeInMap node : childNodes) {
            width += node.getWidth();
        }
        return width;
    }

    public int getIndexInParentNode() {
        return this.indexInParentNode;
    }

    public int getMinIndexColumn() {
        int minIndexColumn = this.getIndexColumn();
        if (childNodes.size() > 0) {
            minIndexColumn = Math.min(minIndexColumn, childNodes.get(0).getMinIndexColumn());
        }
        return minIndexColumn;
    }

    public int getMaxIndexColumn() {
        int maxIndexColumn = this.getIndexColumn();
        if (childNodes.size() > 0) {
            maxIndexColumn = Math.max(maxIndexColumn, childNodes.get(childNodes.size() - 1).getMaxIndexColumn());
        }
        return maxIndexColumn;
    }

    public int getMaxIndexRow() {
        int maxIndexRow = this.getIndexRow();
        for (NodeInMap node : childNodes) {
            maxIndexRow = Math.max(maxIndexRow, node.getMaxIndexRow());
        }
        return maxIndexRow;
    }

    public boolean hasConnection(NodeInMap otherNode) {
        if (this.rootSubject == null || otherNode.getRootSubject() == null) {
            return false;
        }
        Subject otherSubject = otherNode.getRootSubject();
        return (this.rootSubject.getParentSubjectCodesByList().contains(otherSubject.getCode())
                || otherSubject.getParentSubjectCodesByList().contains(this.rootSubject.getCode()));
    }

    // Setter
    public void setRootSubject(Subject subject) {
        this.rootSubject = subject;
    }

    public void setIndexRow(int index) {
        this.indexRow = index;
    }

    public void setIndexColumn(int index) {
        this.indexColumn = index;
    }

    public void setParentNode(NodeInMap node) {
        this.parentNode = node;
    }

    public void setIndexColumnAndAutoSetChildNodes(int index) {
        int abs = index - this.indexColumn;
        if (abs >= 0) {
            moveRight(getMinIndexColumn(), abs);
        } else {
            moveLeft(getMaxIndexColumn(), -abs);
        }
    }

    public void correctIndexColumn() {
        int lastIndexChild = childNodes.size() - 1;
        if (lastIndexChild > -1) {
            setIndexColumn((getMinIndexColumn() + getMaxIndexColumn()) / 2);
        }
    }

    public void setIndexInParentNode(int index) {
        this.indexInParentNode = index;
    }

    public boolean contains(Subject childSubject) {
        for (NodeInMap node : childNodes) {
            if (node.getRootSubject().getCode().equals(childSubject.getCode())) {
                return true;
            }
        }
        return false;
    }

    public void addChildNode(NodeInMap node) {
        // get root of map
        NodeInMap root = this;
        while (root.getParentNode() != null) {
            root = root.getParentNode();
        }

        // Find good index for 'node' in 'childNodes' by node max index row
        int indexForNode = 0;
        if (childNodes.size() == 0) {
            node.setIndexInParentNode(this.childNodes.size());
        } else {
            int maxIndexRowOfNode = node.getMaxIndexRow();
            int[] maxIndexRows = new int[childNodes.size()];
            for (int index = 0; index < childNodes.size(); index++) {
                maxIndexRows[index] = childNodes.get(index).getMaxIndexRow();
            }

            int countLeft = 0;
            int countRight = 0;
            for (int index = 0; index < maxIndexRows.length; index++) {
                if (maxIndexRows[index] == maxIndexRowOfNode) {
                    if (index < maxIndexRows.length / 2) {
                        countLeft++;
                    } else {
                        countRight++;
                    }
                }
            }

            if (countLeft <= countRight) {
                int tempMaxIndexRow = maxIndexRows[0];
                int tempCount = 0;
                for (int index = 0; index < maxIndexRows.length; index++) {
                    if (maxIndexRows[index] == tempMaxIndexRow) {
                        tempCount++;
                    } else if (tempMaxIndexRow < maxIndexRows[index]) {
                        tempMaxIndexRow = maxIndexRows[index];
                        tempCount = 1;
                    } else {
                        indexForNode = index - 1 - tempCount / 2;
                        break;
                    }

                    if (maxIndexRowOfNode >= maxIndexRows[index]) {
                        indexForNode = index;
                    } else {
                        indexForNode = index - 1 - tempCount / 2;
                        break;
                    }

                    if (index == maxIndexRows.length - 1) {
                        indexForNode = index - tempCount / 2;
                    }
                }
            } else {
                int tempMaxIndexRow = maxIndexRows[0];
                int tempCount = 0;
                for (int index = maxIndexRows.length - 1; index >= 0; index--) {
                    if (maxIndexRows[index] == tempMaxIndexRow) {
                        tempCount++;
                    } else if (tempMaxIndexRow < maxIndexRows[index]) {
                        tempMaxIndexRow = maxIndexRows[index];
                        tempCount = 1;
                    } else {
                        indexForNode = index + tempCount / 2;
                        break;
                    }

                    if (maxIndexRowOfNode >= maxIndexRows[index]) {
                        indexForNode = index;
                    } else {
                        indexForNode = index + tempCount / 2;
                        break;
                    }

                    if (index == 0) {
                        indexForNode = index + tempCount / 2;
                    }
                }
            }
        }

        indexForNode = Math.min(indexForNode, childNodes.size());

        int nodeWidth = node.getWidth();
        if (this.childNodes.size() == 0) {
            node.setIndexColumnAndAutoSetChildNodes(getMinIndexColumn() + nodeWidth / 2);
            // Move some node to right
            root.moveRight(getMaxIndexColumn() + 1, nodeWidth - 1);
        } else {
            if (indexForNode < childNodes.size()) {
                node.setIndexColumnAndAutoSetChildNodes(
                        childNodes.get(indexForNode).getMinIndexColumn() + nodeWidth / 2);
                // Move some node to right
                root.moveRight(childNodes.get(indexForNode).getMinIndexColumn(), nodeWidth);
            } else {
                node.setIndexColumnAndAutoSetChildNodes(
                        childNodes.get(indexForNode - 1).getMaxIndexColumn() + 1 + nodeWidth / 2);
                // Move some node to right
                root.moveRight(childNodes.get(indexForNode - 1).getMaxIndexColumn() + 1, nodeWidth);
            }

        }
        this.childNodes.add(indexForNode, node);

        // correct index in parent node for each node
        for (int index = 0; index < childNodes.size(); index++) {
            childNodes.get(index).setIndexInParentNode(index);
        }

        // Correct index column for root
        root = this;
        while (root != null) {
            root.correctIndexColumn();
            root = root.getParentNode();
        }
    }

    // move root subject and child subject to right
    public void moveRight(int indexColumnStart, int numberColumn) {
        if (getIndexColumn() >= indexColumnStart) {
            setIndexColumn(getIndexColumn() + numberColumn);
        }
        for (NodeInMap node : childNodes) {
            node.moveRight(indexColumnStart, numberColumn);
        }
    }

    // move root subject and child subject to left
    public void moveLeft(int indexColumnEnd, int numberColumn) {
        if (getIndexColumn() <= indexColumnEnd) {
            setIndexColumn(getIndexColumn() - numberColumn);
        }
        for (NodeInMap node : childNodes) {
            node.moveLeft(indexColumnEnd, numberColumn);
        }
    }

}
