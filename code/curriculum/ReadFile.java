package code.curriculum;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ReadFile {
    public static Major createMajorFromFile(String path, String majorName,
            String[] knowledgePartNames, String[] descriptionKnowledgeParts,
            String[] subjectNames) {
        Major major = new Major(majorName);
        try {
            FileReader file = new FileReader(path);
            BufferedReader reader = new BufferedReader(file);
            String line = reader.readLine(); // the first line is titles
            line = reader.readLine();
            int countSubjects = 0;
            int countKnowledgeParts = 0;
            int countDescriptions = 0;
            String typeKnowledge = ""; // "compulsory1" or "compulsory2" or "optional1" or "optional2"
            while (line != null) {
                String[] fields = line.split(",");
                String type = fields[0];
                if (type.equals("subject")) {
                    // Create new Subject
                    String code = fields[1];
                    int credits = Integer.parseInt(fields[2]);
                    String[] parentSubjectCodes = fields[3].split(";");
                    Subject subject = new Subject("", code, credits);
                    if (countSubjects < subjectNames.length) {
                        subject.setName(subjectNames[countSubjects]);
                    }
                    for (String name : parentSubjectCodes) {
                        subject.addParentSubjectCode(name);
                    }
                    // Add subject to current knowledgePart
                    if (typeKnowledge.equals("compulsory1")) {
                        major.getKnowledgeParts().get(countKnowledgeParts - 1).addCompulsorySubject1(subject);
                    } else if (typeKnowledge.equals("compulsory2")) {
                        major.getKnowledgeParts().get(countKnowledgeParts - 1).addCompulsorySubject2(subject);
                    } else if (typeKnowledge.equals("optional1")) {
                        major.getKnowledgeParts().get(countKnowledgeParts - 1).addOptionalSubject1(subject);
                    } else if (typeKnowledge.equals("optional2")) {
                        major.getKnowledgeParts().get(countKnowledgeParts - 1).addOptionalSubject2(subject);
                    }
                    countSubjects++;
                } else if (type.equals("knowledge")) {
                    major.addKnowledgePart(new KnowledgePart(knowledgePartNames[countKnowledgeParts]));
                    countKnowledgeParts++;
                } else if (type.equals("compulsory1")) {
                    typeKnowledge = type;
                    int credits = Integer.parseInt(fields[2]);
                    major.getKnowledgeParts().get(countKnowledgeParts - 1).setMinCreditsCompulsorySubjects(credits);
                    if (countDescriptions < descriptionKnowledgeParts.length) {
                        major.getKnowledgeParts().get(countKnowledgeParts - 1).setDescriptionCompulsory1(
                                descriptionKnowledgeParts[countDescriptions]);
                        countDescriptions++;
                    }
                } else if (type.equals("compulsory2")) {
                    typeKnowledge = type;
                    int credits = Integer.parseInt(fields[2]);
                    major.getKnowledgeParts().get(countKnowledgeParts - 1).setMinCreditsCompulsorySubjects(credits);
                    if (countDescriptions < descriptionKnowledgeParts.length) {
                        major.getKnowledgeParts().get(countKnowledgeParts - 1).setDescriptionCompulsory2(
                                descriptionKnowledgeParts[countDescriptions]);
                        countDescriptions++;
                    }
                } else if (type.equals("optional1")) {
                    typeKnowledge = type;
                    int credits = Integer.parseInt(fields[2]);
                    major.getKnowledgeParts().get(countKnowledgeParts - 1).setMinCreditsOptionalSubjects(credits);
                    if (countDescriptions < descriptionKnowledgeParts.length) {
                        major.getKnowledgeParts().get(countKnowledgeParts - 1).setDescriptionOptional1(
                                descriptionKnowledgeParts[countDescriptions]);
                        countDescriptions++;
                    }
                } else if (type.equals("optional2")) {
                    typeKnowledge = type;
                    int credits = Integer.parseInt(fields[2]);
                    major.getKnowledgeParts().get(countKnowledgeParts - 1).setMinCreditsOptionalSubjects(credits);
                    if (countDescriptions < descriptionKnowledgeParts.length) {
                        major.getKnowledgeParts().get(countKnowledgeParts - 1).setDescriptionOptional2(
                                descriptionKnowledgeParts[countDescriptions]);
                        countDescriptions++;
                    }
                }

                // Read next line
                line = reader.readLine();
            }

            // Add relative between subjects
            List<Subject> subjects = major.getSubjects();
            for (Subject subject : subjects) {
                for (String parentSubjectCode : subject.getParentSubjectCodes()) {
                    for (Subject checkingSubject : subjects) {
                        if (checkingSubject.getCode().equals(parentSubjectCode)) {
                            subject.addParentSubject(checkingSubject);
                        }
                    }
                }
            }

            reader.close();
            file.close();
        } catch (IOException e) {

        }
        return major;
    }
}
