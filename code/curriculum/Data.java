package code.curriculum;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

public class Data {
    public static SchoolManager SCHOOLS = null;

    public Data() {
        if (SCHOOLS == null) {
            SCHOOLS = new SchoolManager();
            try {
                String path = "assets\\data\\SCHOOLS";
                File[] schoolFolders = (new File(path)).listFiles();
                for (File schoolFolder : schoolFolders) {
                    String path1 = path + "\\" + schoolFolder.getName();
                    // Create a School instance
                    School school = new School("");

                    // Add Department to School
                    File[] departmentFolders = schoolFolder.listFiles();
                    for (File departmentFolder : departmentFolders) {
                        String path2 = path1 + "\\" + departmentFolder.getName();
                        if (departmentFolder.isFile()) {
                            // this is file txt contains name of school
                            String schoolName = getStringFromFile(path2);
                            school.setName(schoolName);
                        } else {
                            // this is a folder Department of school
                            // Create a Department instance
                            Department department = new Department("");

                            // Add Major to Department
                            File[] majorFolders = departmentFolder.listFiles();
                            for (File majorFolder : majorFolders) {
                                String path3 = path2 + "\\" + majorFolder.getName();
                                if (majorFolder.isFile()) {
                                    // this is a file txt contains name of department
                                    String departmentName = getStringFromFile(path3);
                                    department.setName(departmentName);
                                } else {
                                    // this is a folder Major of department
                                    // Create Major instance by read file data
                                    Major major = getMajorFromFolder(path3);
                                    // Add Major to Department
                                    department.addMajor(major);
                                }
                            }
                            // Add Department to School
                            school.addDepartment(department);
                        }
                    }
                    // Add School to SchoolManager
                    SCHOOLS.addSchool(school);
                }
            } catch (Exception e) {

            }
        }
    }

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
            String typeSubject = ""; // "compulsory1" | "compulsory2" | "optional1" | "optional2"
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
                        subject.addParentSubjectCode(parentSubjectCode);
                    }
                    // Add subject to KnowlegePart of Major
                    int lastIndex = major.getKnowledgeParts().size() - 1;
                    if (typeSubject.equals("compulsory1")) {
                        major.getKnowledgeParts().get(lastIndex).addCompulsorySubject1(subject);
                    } else if (typeSubject.equals("compulsory2")) {
                        major.getKnowledgeParts().get(lastIndex).addCompulsorySubject2(subject);
                    } else if (typeSubject.equals("optional1")) {
                        major.getKnowledgeParts().get(lastIndex).addOptionalSubject1(subject);
                    } else if (typeSubject.equals("optional2")) {
                        major.getKnowledgeParts().get(lastIndex).addOptionalSubject2(subject);
                    }
                } else if (type.equals("knowledge")) {
                    // Create and add new KnowledgePart to major
                    major.addKnowledgePart(new KnowledgePart(datas[1]));
                } else {
                    // Add compulsory | optional to last knowledgePart of major
                    typeSubject = type;
                    int lastIndex = major.getKnowledgeParts().size() - 1;
                    if (type.equals("compulsory1")) {
                        major.getKnowledgeParts().get(lastIndex).setDescriptionCompulsory1(datas[1]);
                        major.getKnowledgeParts().get(lastIndex)
                                .setMinCreditsCompulsorySubjects(Integer.parseInt(datas[3]));
                    } else if (type.equals("compulsory2")) {
                        major.getKnowledgeParts().get(lastIndex).setDescriptionCompulsory2(datas[1]);
                        major.getKnowledgeParts().get(lastIndex)
                                .setMinCreditsCompulsorySubjects(Integer.parseInt(datas[3]));
                    } else if (type.equals("optional1")) {
                        major.getKnowledgeParts().get(lastIndex).setDescriptionOptional1(datas[1]);
                        major.getKnowledgeParts().get(lastIndex)
                                .setMinCreditsOptionalSubjects(Integer.parseInt(datas[3]));
                    } else if (type.equals("optional2")) {
                        major.getKnowledgeParts().get(lastIndex).setDescriptionOptional2(datas[1]);
                        major.getKnowledgeParts().get(lastIndex)
                                .setMinCreditsOptionalSubjects(Integer.parseInt(datas[3]));
                    }
                }
                // Go to next line
                line = reader.readLine();
            }

            // Add relative between subject
            for (Subject subject : major.getSubjects()) {
                List<String> parentSubjectCodes = subject.getParentSubjectCodes();
                for (Subject checkingSubject : major.getSubjects()) {
                    if (parentSubjectCodes.contains(
                            checkingSubject.getCode())) {
                        subject.addParentSubject(checkingSubject);
                    }
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
