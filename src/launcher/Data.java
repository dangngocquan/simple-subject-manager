package src.launcher;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import src.objects.ConversionTable;
import src.objects.ConversionTableManager;
import src.objects.Department;
import src.objects.KnowledgePart;
import src.objects.Major;
import src.objects.School;
import src.objects.SchoolManager;
import src.objects.Subject;

public class Data {
    public static SchoolManager SCHOOLS = null;
    public static ConversionTableManager CONVERSIONS = null;
    public static Data instance = null;
    public static int maxCount = 6, tempCount = 0;

    public Data() {
        instance = this;
        if (SCHOOLS == null) {
            SCHOOLS = new SchoolManager();
            try {
                String path = "assets/data/SCHOOLS";

                School school1 = new School("");
                school1.setName(getStringFromFile(path + "/SCHOOL001/schoolName.txt", true));

                Department department1s1 = new Department("");
                department1s1.setName(getStringFromFile(path + "/SCHOOL001/DEPARTMENT001/departmentName.txt", true));
                department1s1.addMajor(getMajorFromFolder(path + "/SCHOOL001/DEPARTMENT001/MAJOR001"));

                try {
                    Thread.sleep(800);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tempCount++;

                department1s1.addMajor(getMajorFromFolder(path + "/SCHOOL001/DEPARTMENT001/MAJOR002"));
                department1s1.addMajor(getMajorFromFolder(path + "/SCHOOL001/DEPARTMENT001/MAJOR003"));

                try {
                    Thread.sleep(800);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tempCount++;

                try {
                    Thread.sleep(300);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tempCount++;

                department1s1.addMajor(getMajorFromFolder(path + "/SCHOOL001/DEPARTMENT001/MAJOR004"));
                department1s1.addMajor(getMajorFromFolder(path + "/SCHOOL001/DEPARTMENT001/MAJOR005"));
                school1.addDepartment(department1s1);

                Department department4s1 = new Department("");
                department4s1.setName(getStringFromFile(path + "/SCHOOL001/DEPARTMENT004/departmentName.txt", true));
                department4s1.addMajor(getMajorFromFolder(path + "/SCHOOL001/DEPARTMENT004/MAJOR001"));
                school1.addDepartment(department4s1);

                SCHOOLS.addSchool(school1);
                try {
                    Thread.sleep(300);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tempCount++;

                School school2 = new School("");
                school2.setName(getStringFromFile(path + "/SCHOOL002/schoolName.txt", true));

                Department department1s2 = new Department("");
                department1s2.setName(getStringFromFile(path + "/SCHOOL002/DEPARTMENT001/departmentName.txt", true));
                department1s2.addMajor(getMajorFromFolder(path + "/SCHOOL002/DEPARTMENT001/MAJOR001"));
                department1s2.addMajor(getMajorFromFolder(path + "/SCHOOL002/DEPARTMENT001/MAJOR004"));
                school2.addDepartment(department1s2);

                SCHOOLS.addSchool(school2);
                try {
                    Thread.sleep(700);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tempCount++;
            } catch (Exception e) {
                System.out.println("Error when read major " + e.getMessage());
            }
        }

        // CONVERSION_TABLES
        if (CONVERSIONS == null) {
            CONVERSIONS = new ConversionTableManager();
            try {
                String path = "assets/data/CONVERSIONS";
                String[] nameFiles = new String[] {
                        "conversion001.txt",
                        "conversion002.txt"
                };
                for (String nameFile : nameFiles) {
                    CONVERSIONS.addConversionTable(getConversionTableFromFile(path + "/" + nameFile));
                }

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                tempCount++;
            } catch (Exception e) {

            }
        }
    }

    // Get string from a file (only for default data)
    public static String getStringFromFile(String path, boolean defaultData) {
        String result = "";
        try {
            InputStream file = instance.getClass().getResourceAsStream(path);
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

    // Get string from a file more line (only use for default data)
    public static List<String> getStringLinesFromFile(String path, boolean defaultData) {
        List<String> strings = new LinkedList<String>();
        try {
            InputStream file = instance.getClass().getResourceAsStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(file, "UTF-8");
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                strings.add(line);
                line = reader.readLine();
            }
            // Close
            reader.close();
            inputStreamReader.close();
            file.close();
        } catch (Exception e) {

        }
        return strings;
    }

    // Get a Major instance from a folder, this folder contains a file txt and a
    // file csv
    public static Major getMajorFromFolder(String path) {
        // Get file names
        String path1 = path + "/MAJOR.csv"; // path of file csv
        String path2 = path + "/majorName.txt"; // path to file txt

        // get major name
        String majorName = getStringFromFile(path2, true);
        Major major = new Major(majorName);

        // get major departments
        try {
            // Open file csv
            InputStream file = instance.getClass().getResourceAsStream(path1);
            InputStreamReader inputStreamReader = new InputStreamReader(file, "UTF-8");
            BufferedReader reader = new BufferedReader(inputStreamReader);

            // read data
            String line = reader.readLine(); // Title
            line = reader.readLine();
            String typeSubject = ""; // "compulsory" | "optional"
            String typeOptinal = "mainDesOptional"; // "OR" | "AND"
            while (line != null) {
                String[] datas = line.split(",");
                String type = datas[0];
                if (type.equals("subject")) {
                    // Create subject
                    String code = datas[1];
                    String name = datas[2];
                    int credits = Integer.parseInt(datas[3]);
                    int defaultYearStudy = Integer.parseInt(datas[5]);
                    String[] parentSubjectCodes = datas[4].split(";");
                    Subject subject = new Subject(name, code, credits);
                    subject.setSemester(defaultYearStudy);
                    // Add parent subject codes for this subject
                    for (String parentSubjectCode : parentSubjectCodes) {
                        subject.addParentSubjectCode(parentSubjectCode.split("/"));
                    }
                    // Add subject to KnowlegePart of Major
                    int lastIndex = major.getKnowledgeParts().size() - 1;
                    if (typeSubject.equals("compulsory")) {
                        major.getKnowledgeParts().get(lastIndex).addCompulsorySubject(
                                subject,
                                major.getKnowledgeParts().get(lastIndex).getNumberOfCompulsorySubjectsList() - 1);
                    } else if (typeSubject.equals("optional")) {
                        if (typeOptinal.equals("mainDesOptional")) {
                            major.getKnowledgeParts().get(lastIndex).addOptionalSubject(
                                    subject,
                                    major.getKnowledgeParts().get(lastIndex).getNumberOfOptionalSubjectsList() - 1);
                        } else if (typeOptinal.equals("mainDesOptionalAND")) {
                            major.getKnowledgeParts().get(lastIndex).addOptionalSubjectAND(
                                    subject,
                                    major.getKnowledgeParts().get(lastIndex).getNumberOfOptionalSubjectsANDList() - 1);
                        }

                    }
                } else if (type.equals("knowledge")) {
                    // Create and add new KnowledgePart to major
                    major.addKnowledgePart(new KnowledgePart(datas[1]));
                } else {
                    // Add compulsory | optional to last knowledgePart of major
                    int lastIndex = major.getKnowledgeParts().size() - 1;
                    if (type.equals("compulsory")) {
                        typeSubject = type;
                        major.getKnowledgeParts().get(lastIndex).getCompulsorySubjects().add(new LinkedList<Subject>());
                        major.getKnowledgeParts().get(lastIndex).addDescriptionCompulsory(datas[1]);
                        major.getKnowledgeParts().get(lastIndex)
                                .addMinCreditsCompulsorySubjects(Integer.parseInt(datas[3]));
                    } else if (type.equals("mainDesOptional")) {
                        typeOptinal = type;
                        major.getKnowledgeParts().get(lastIndex).setMainDescriptionOptionalSubjects(datas[1]);
                    } else if (type.equals("mainDesOptionalAND")) {
                        typeOptinal = type;
                        major.getKnowledgeParts().get(lastIndex).setMainDescriptionOptionalSubjectsAND(datas[1]);
                    } else if (type.equals("optional")) {
                        typeSubject = type;
                        if (typeOptinal.equals("mainDesOptional")) {
                            major.getKnowledgeParts().get(lastIndex).getOptionalSubjects()
                                    .add(new LinkedList<Subject>());
                            major.getKnowledgeParts().get(lastIndex).addDescriptionOptional(datas[1]);
                            major.getKnowledgeParts().get(lastIndex)
                                    .addMinCreditsOptionalSubjects(Integer.parseInt(datas[3]));
                        } else if (typeOptinal.equals("mainDesOptionalAND")) {
                            major.getKnowledgeParts().get(lastIndex).getOptionalSubjectsAND()
                                    .add(new LinkedList<Subject>());
                            major.getKnowledgeParts().get(lastIndex).addDescriptionOptionalAND(datas[1]);
                            major.getKnowledgeParts().get(lastIndex)
                                    .addMinCreditsOptionalSubjectsAND(Integer.parseInt(datas[3]));
                        }
                    }
                }
                // Go to next line
                line = reader.readLine();
            }

            // Add relative between subject
            for (Subject subject : major.getSubjects()) {
                for (String[] parentSubjectCodes : subject.getParentSubjectCodes()) {
                    List<Subject> parentSubjectsList = new LinkedList<>();
                    for (String parentSubjectCode : parentSubjectCodes) {
                        for (Subject checkingSubject : major.getSubjects()) {
                            if (parentSubjectCode.equals(checkingSubject.getCode())) {
                                parentSubjectsList.add(checkingSubject);
                            }
                        }
                    }
                    Subject[] parentSubjects = new Subject[parentSubjectsList.size()];
                    for (int index = 0; index < parentSubjectsList.size(); index++) {
                        parentSubjects[index] = parentSubjectsList.get(index);
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

    // Get conversion table from a file txt
    public static ConversionTable getConversionTableFromFile(String path) {
        List<String> data = getStringLinesFromFile(path, true);
        String name = data.get(0);
        String[] score10 = data.get(1).split(",");
        String[] characterScore = data.get(2).split(",");
        String[] score4 = data.get(3).split(",");
        ConversionTable conversionTable = new ConversionTable(name);
        conversionTable.setScore10(score10);
        conversionTable.setCharacterScore(characterScore);
        conversionTable.setScore4(score4);
        return conversionTable;
    }

    // Get School by name
    public static School getSchool(String schoolName) {
        for (School school : SCHOOLS.getSchools()) {
            if (school.getName().equals(schoolName)) {
                return school;
            }
        }
        return null;
    }

    // Get Department by name
    public static Department getDepartment(String departmentName, School school) {
        for (Department department : school.getDepartments()) {
            if (department.getName().equals(departmentName)) {
                return department;
            }
        }
        return null;
    }

    // Get Major by name
    public static Major getMajor(String majorName, Department department) {
        for (Major major : department.getMajors()) {
            if (major.getName().equals(majorName)) {
                return major;
            }
        }
        return null;
    }

    // Get index Conversion by name
    public static int getIndexConnversion(String name) {
        for (int i = 0; i < CONVERSIONS.getConversionTables().size(); i++) {
            ConversionTable conversion = CONVERSIONS.getConversionTables().get(i);
            if (conversion.getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

}
