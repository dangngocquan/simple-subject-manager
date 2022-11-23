package code.file_handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

import code.curriculum.KnowledgePart;
import code.curriculum.Major;
import code.curriculum.Subject;

public class ReadFile {
    // Get string from a file
    public static String getStringFromFile(String path) {
        String result = "";
        try {
            FileInputStream file = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(file, "UTF-8");
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                result += line;
                line = reader.readLine();
            }
            // Close
            reader.close();
            inputStreamReader.close();
            file.close();
        } catch (Exception e) {

        }
        return result;
    }

    // Get a Major instance from a folder, this folder contains a file txt and a
    // file csv
    public static Major getMajorFromFolder(String path) {
        // Get file names
        File folder = new File(path);
        String path1 = ""; // path of file csv
        String path2 = ""; // path to file txt
        String[] nameFiles = folder.list();
        if (nameFiles[0].matches("\\w{0,}\\.csv")) {
            path1 = path + "\\" + nameFiles[0];
            path2 = path + "\\" + nameFiles[1];
        } else {
            path1 = path + "\\" + nameFiles[1];
            path2 = path + "\\" + nameFiles[0];
        }

        // get major name
        String majorName = getStringFromFile(path2);
        Major major = new Major(majorName);

        // get major departments
        try {
            // Open file csv
            FileInputStream file = new FileInputStream(path1);
            InputStreamReader inputStreamReader = new InputStreamReader(file, "UTF-8");
            BufferedReader reader = new BufferedReader(inputStreamReader);

            // read data
            String line = reader.readLine(); // Title
            line = reader.readLine();
            String typeSubject = ""; // "compulsory" | "optional"
            while (line != null) {
                String[] datas = line.split(",");
                String type = datas[0];
                if (type.equals("subject")) {
                    // Create subject
                    String code = datas[1];
                    String name = datas[2];
                    int credits = Integer.parseInt(datas[3]);
                    String[] parentSubjectCodes = datas[4].split(";");
                    Subject subject = new Subject(name, code, credits);
                    // Add parent subject codes for this subject
                    for (String parentSubjectCode : parentSubjectCodes) {
                        subject.addParentSubjectCode(parentSubjectCode.split("/"));
                    }
                    // Add subject to KnowlegePart of Major
                    int lastIndex = major.getKnowledgeParts().size() - 1;
                    if (typeSubject.equals("compulsory")) {
                        major.getKnowledgeParts().get(lastIndex).addCompulsorySubject(subject);
                    } else if (typeSubject.equals("optional")) {
                        major.getKnowledgeParts().get(lastIndex).addOptionalSubject(
                                subject,
                                major.getKnowledgeParts().get(lastIndex).getNumberOfOptionalSubjectsList() - 1);
                    }
                } else if (type.equals("knowledge")) {
                    // Create and add new KnowledgePart to major
                    major.addKnowledgePart(new KnowledgePart(datas[1]));
                } else {
                    // Add compulsory | optional to last knowledgePart of major
                    typeSubject = type;
                    int lastIndex = major.getKnowledgeParts().size() - 1;
                    if (type.equals("compulsory")) {
                        major.getKnowledgeParts().get(lastIndex).setDescriptionCompulsory(datas[1]);
                        major.getKnowledgeParts().get(lastIndex)
                                .setMinCreditsCompulsorySubjects(Integer.parseInt(datas[3]));
                    } else if (type.equals("mainDesOptional")) {
                        major.getKnowledgeParts().get(lastIndex).setMainDescriptionOptionalSubjects(datas[1]);
                    } else if (type.equals("optional")) {
                        major.getKnowledgeParts().get(lastIndex).getOptionalSubjects().add(new LinkedList<Subject>());
                        major.getKnowledgeParts().get(lastIndex).addDescriptionOptional(datas[1]);
                        major.getKnowledgeParts().get(lastIndex)
                                .setMinCreditsOptionalSubjects(Integer.parseInt(datas[3]));
                    }
                }
                // Go to next line
                line = reader.readLine();
            }

            // Add relative between subject
            for (Subject subject : major.getSubjects()) {
                for (String[] parentSubjectCodes : subject.getParentSubjectCodes()) {
                    Subject[] parentSubjects = new Subject[parentSubjectCodes.length];
                    int count = 0;
                    for (String parentSubjectCode : parentSubjectCodes) {
                        for (Subject checkingSubject : major.getSubjects()) {
                            if (parentSubjectCode.equals(checkingSubject.getCode())) {
                                if (count < parentSubjectCodes.length) {
                                    parentSubjects[count] = checkingSubject;
                                    count++;
                                }
                            }
                        }
                    }
                    subject.addParentSubject(parentSubjects);
                }

            }

            // Close file
            reader.close();
            inputStreamReader.close();
            file.close();
        } catch (Exception e) {

        }
        return major;
    }
}
