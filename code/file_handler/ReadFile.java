package code.file_handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import code.objects.Account;
import code.objects.ConversionTable;
import code.objects.KnowledgePart;
import code.objects.Major;
import code.objects.Plan;
import code.objects.Subject;

public class ReadFile {
    public static final String PATH_DATA = "C:/GPA_Plan";
    public static final String PATH_DATA_TEMP = "C:/GPA_Plan/Temp";
    public static final String PATH_DATA_TEMP_1 = "C:/GPA_Plan/Temp/tempAccount.txt";
    public static final String PATH_DATA_ACCOUNT = "C:/GPA_Plan/Accounts";
    public static final String PATH_DATA_ACCOUNT_1 = "C:/GPA_Plan/Accounts/count.txt";

    // Get number current accounts
    public static int getNumberExistingAccounts() {
        WriteFile.createDefaultPathData();
        File file = new File(PATH_DATA_ACCOUNT);
        return file.list().length - 1;
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

    // Get string from a file more line
    public static List<String> getStringLinesFromFile(String path) {
        List<String> strings = new LinkedList<String>();
        try {
            FileInputStream file = new FileInputStream(path);
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

    // Get subject from file
    public static Subject getSubjectFromFile(String path) {
        List<String> data = getStringLinesFromFile(path);
        String code = data.get(0);
        String name = data.get(1);
        int credits = Integer.parseInt(data.get(2));
        List<String[]> parentSubjectCodes = new LinkedList<>();
        for (String str : data.get(3).split(";")) {
            parentSubjectCodes.add(str.split("/"));
        }
        int state = Integer.parseInt(data.get(4));
        String characterScore = data.get(5);
        String[] rgb = data.get(6).split(";");
        double score10 = Double.parseDouble(data.get(7));
        double score4 = Double.parseDouble(data.get(8));
        int yearStudy = Integer.parseInt(data.get(9));

        Subject subject = new Subject(name, code, credits);
        for (String[] parentCode : parentSubjectCodes) {
            subject.addParentSubjectCode(parentCode);
        }
        subject.setCharacterScore(characterScore);
        subject.setState(state);
        subject.setColor(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
        subject.setScore10(score10);
        subject.setScore4(score4);
        subject.setSemester(yearStudy);

        return subject;
    }

    // Get Plan from file
    public static Plan getPlanFromFile(String path) {
        List<String> data = getStringLinesFromFile(path + "/informations.txt");
        String name = data.get(0);
        int indexConversionTable = Integer.parseInt(data.get(1));
        List<Subject> subjects = new LinkedList<Subject>();
        File file = new File(path);
        for (String nameFile : file.list()) {
            String tempPath = path + "/" + nameFile;
            if (nameFile.matches("subject[0-9]{1,}[.]txt")) {
                subjects.add(getSubjectFromFile(tempPath));
            }
        }
        // Create relative between subjects
        for (Subject subject : subjects) {
            for (int i = 0; i < subject.getParentSubjectCodes().size(); i++) {
                String[] parentCodes = subject.getParentSubjectCodes().get(i);
                List<Subject> parentSubjects = new LinkedList<>();
                for (int j = 0; j < parentCodes.length; j++) {
                    String parentCode = parentCodes[j];
                    for (Subject checkSubject : subjects) {
                        if (checkSubject.getCode().equals(parentCode)) {
                            parentSubjects.add(checkSubject);
                        }
                    }

                }
                Subject[] arr = new Subject[parentSubjects.size()];
                for (int k = 0; k < arr.length; k++) {
                    arr[k] = parentSubjects.get(k);
                }
                subject.addParentSubject(arr);
            }
        }

        Plan plan = new Plan(name, subjects, indexConversionTable);
        return plan;
    }

    // Get Plans from file
    public static List<Plan> getPlans(String path) {
        List<Plan> plans = new LinkedList<Plan>();
        File file = new File(path);
        if (file.exists()) {
            String[] nameFolders = file.list();
            for (String nameFolder : nameFolders) {
                String path1 = path + "/" + nameFolder;
                File tempFile = new File(path1);
                if (tempFile.isDirectory()) {
                    plans.add(getPlanFromFile(path1));
                }
            }
        }
        return plans;
    }

    // Get account from file
    public static Account getAccountFromFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            // Get information
            List<String> lines = getStringLinesFromFile(path + "/" + "informations.txt");
            String name = lines.get(0);
            String username = lines.get(1);
            String password = lines.get(2);
            String timeAccountCreated = lines.get(3);
            // get plans
            List<Plan> plans = getPlans(path);
            // Create account
            Account account = new Account(name, username, password, timeAccountCreated);
            account.setPlans(plans);
            return account;
        }
        return null;

    }

    // Get existing accounts
    public static List<Account> getAccounts() {
        List<Account> accounts = new LinkedList<Account>();
        File file = new File(ReadFile.PATH_DATA_ACCOUNT);
        if (file.exists()) {
            String[] nameFolders = file.list();
            for (String nameFolder : nameFolders) {
                String path1 = ReadFile.PATH_DATA_ACCOUNT + "/" + nameFolder;
                File tempFile = new File(path1);
                if (tempFile.isDirectory() && tempFile.listFiles().length != 0) {
                    accounts.add(getAccountFromFile(path1));
                }
            }
        }
        return accounts;
    }

    // Find Account by username
    public static Account findAccountByUsername(String username) {
        List<Account> accounts = getAccounts();
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }

    // Check existing username
    public static boolean isExistingUsername(String username) {
        Account account = findAccountByUsername(username);
        return !(account == null);
    }

    // Get current Account
    public static Account getCurrentAccount() {
        WriteFile.createDefaultPathData();
        String currentUsername = getStringFromFile(PATH_DATA_TEMP_1);
        return findAccountByUsername(currentUsername);
    }

    // Gget path of current account
    public static String getPathCurrentAccount() {
        String currentUsername = getStringFromFile(PATH_DATA_TEMP_1);
        File file = new File(ReadFile.PATH_DATA_ACCOUNT);
        if (file.exists()) {
            String[] nameFolders = file.list();
            for (String nameFolder : nameFolders) {
                String path1 = ReadFile.PATH_DATA_ACCOUNT + "/" + nameFolder;
                File tempFile = new File(path1);
                if (tempFile.isDirectory()) {
                    String path2 = path1 + "/informations.txt";
                    String username = getStringLinesFromFile(path2).get(1);
                    if (username.equals(currentUsername)) {
                        return path1;
                    }
                }
            }
        }
        return "";
    }

    // Get index of curent account
    public static int getIndexCurrentAccount() {
        WriteFile.createDefaultPathData();
        String currentUsername = getStringFromFile(PATH_DATA_TEMP_1);
        int index = 0;
        for (Account account : getAccounts()) {
            if (account.getUsername().equals(currentUsername)) {
                return index;
            }
            index++;
        }
        return -1;
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
                        major.getKnowledgeParts().get(lastIndex).getCompulsorySubjects().add(new LinkedList<Subject>());
                        major.getKnowledgeParts().get(lastIndex).addDescriptionCompulsory(datas[1]);
                        major.getKnowledgeParts().get(lastIndex)
                                .addMinCreditsCompulsorySubjects(Integer.parseInt(datas[3]));
                    } else if (type.equals("mainDesOptional")) {
                        major.getKnowledgeParts().get(lastIndex).setMainDescriptionOptionalSubjects(datas[1]);
                    } else if (type.equals("optional")) {
                        major.getKnowledgeParts().get(lastIndex).getOptionalSubjects().add(new LinkedList<Subject>());
                        major.getKnowledgeParts().get(lastIndex).addDescriptionOptional(datas[1]);
                        major.getKnowledgeParts().get(lastIndex)
                                .addMinCreditsOptionalSubjects(Integer.parseInt(datas[3]));
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

    // Get conversion table from a file txt
    public static ConversionTable getConversionTableFromFile(String path) {
        List<String> data = getStringLinesFromFile(path);
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
}
