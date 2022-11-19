package code.curriculum;

import java.util.LinkedList;
import java.util.List;

public class Major {
    // Properties
    private String name;
    private List<KnowledgePart> knowledgeParts;

    // Constructor
    public Major(String name) {
        this.name = name;
        this.knowledgeParts = new LinkedList<KnowledgePart>();
    }

    // Getter
    public String getName() {
        return this.name;
    }

    public List<KnowledgePart> getKnowledgeParts() {
        return this.knowledgeParts;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    public void addKnowledgePart(KnowledgePart knowledgePart) {
        this.knowledgeParts.add(knowledgePart);
    }
}
