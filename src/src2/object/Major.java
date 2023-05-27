package src2.object;

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

    public List<Subject> getSubjects() {
        List<Subject> subjects = new LinkedList<Subject>();
        for (KnowledgePart knowledgePart : this.knowledgeParts) {
            List<Subject> subjectsOfKnowledge = knowledgePart.getSubjects();
            for (Subject subject : subjectsOfKnowledge) {
                subjects.add(subject);
            }
        }
        return subjects;
    }
}
