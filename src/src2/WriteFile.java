package src2;

import src2.object.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class WriteFile {
    // Create default path data to save data of this application
    public static void createDefaultPathData() {
        try {
            // Create "C:/GPA_Plan"
            File file = new File(ReadFile.PATH_DATA);
            if (!file.exists()) {
                file.mkdirs();
            }
            // Create "C:/GPA_Plan/Accounts"
            File file1 = new File(ReadFile.PATH_DATA_ACCOUNT);
            if (!file1.exists()) {
                file1.mkdirs();
                // Create "C:/GPA_Plan/Accounts/count.txt"
                File file11 = new File(ReadFile.PATH_DATA_ACCOUNT_1);
                if (!file11.exists()) {
                    file11.createNewFile();
                }
                writeStringToFile(ReadFile.PATH_DATA_ACCOUNT_1, "1", false);
                // Create "C:/GPA_Plan/Temp"
                File file2 = new File(ReadFile.PATH_DATA_TEMP);
                if (file2.exists()) {
                    // Format "C:/GPA_Plan/Accounts/tempAccount.txt", save username of current
                    // account use application
                    File file21 = new File(ReadFile.PATH_DATA_TEMP_1);
                    if (file21.exists()) {
                        writeStringToFile(ReadFile.PATH_DATA_TEMP_1, "", false);
                    }
                }
            }
            // Create "C:/GPA_Plan/Temp"
            File file2 = new File(ReadFile.PATH_DATA_TEMP);
            if (!file2.exists()) {
                file2.mkdirs();
                // Create "C:/GPA_Plan/Accounts/tempAccount.txt", save username of current
                // account use application
                File file21 = new File(ReadFile.PATH_DATA_TEMP_1);
                if (!file21.exists()) {
                    file2.createNewFile();
                }
            }
        } catch (Exception e) {

        }
    }

    // Write String to file
    public static void writeStringToFile(String path, String text, boolean append) {
        try {
            File checkFile = new File(path);
            if (!checkFile.exists()) {
                checkFile.createNewFile();
            }
            FileOutputStream file = new FileOutputStream(path, append);
            OutputStreamWriter writer = new OutputStreamWriter(file, "UTF-8");
            writer.write(text);
            writer.flush();
            writer.close();
            file.close();
        } catch (Exception e) {

        }
    }

    // Create new empty account
    public static void createNewAccount(Account account) {
        createDefaultPathData();
        // Create name for new folder
        int count = Integer.parseInt(ReadFile.getStringFromFile(ReadFile.PATH_DATA_ACCOUNT_1));
        String nameFolder = String.format("account%03d", count);
        count++;
        // Write new data for C:/GPA_Plan/Accounts/count.txt
        writeStringToFile(ReadFile.PATH_DATA_ACCOUNT_1, count + "", false);

        // Create new folder - where save new account
        File file = new File(ReadFile.PATH_DATA_ACCOUNT + "/" + nameFolder);
        file.mkdir();
        // Create count.txt for new account (use to create for plans of this account
        // after)
        File file2 = new File(ReadFile.PATH_DATA_ACCOUNT + "/" + nameFolder + "/count.txt");
        try {
            file2.createNewFile();
            writeStringToFile(ReadFile.PATH_DATA_ACCOUNT + "/" + nameFolder + "/count.txt",
                    "1", false);
        } catch (Exception e) {

        }
        // Create informations.txt to save name and other
        File file3 = new File(ReadFile.PATH_DATA_ACCOUNT + "/" + nameFolder + "/informations.txt");
        try {
            file3.createNewFile();
            writeStringToFile(ReadFile.PATH_DATA_ACCOUNT + "/" + nameFolder + "/informations.txt",
                    account.getName() + "\n" + account.getUsername() + "\n" + account.getPassword() + "\n"
                            + account.getTimeAccountCreated(),
                    false);
        } catch (Exception e) {

        }
    }

    // Remove folder
    public static void removeFolder(File file) {
        if (file.isFile()) {
            file.delete();
        } else {
            File[] files = file.listFiles();
            if (files.length > 0) {
                for (File file1 : files) {
                    removeFolder(file1);
                }
            }
            file.delete();
        }
    }

    // Remove account
    public static void removeAccount(Account account) {
        createDefaultPathData();
        File file = new File(ReadFile.PATH_DATA_ACCOUNT);
        if (file.exists()) {
            // Folders account
            for (String accountFolderNames : file.list()) {
                String path1 = ReadFile.PATH_DATA_ACCOUNT + "/" + accountFolderNames;
                File file1 = new File(path1);
                if (file1.exists() && file1.isDirectory()) {
                    // file in a folder account
                    for (String fileName : file1.list()) {
                        String path2 = path1 + "/" + fileName;
                        if (fileName.equals("informations.txt")) {
                            String tempUsername = ReadFile.getStringLinesFromFile(path2).get(1);
                            if (tempUsername.equals(account.getUsername())) {
                                removeFolder(file1);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    // Change information of account
    public static void changeInformationAccount(Account oldAccount, Account newAccount, String type) {
        // Create new infor
        String name = oldAccount.getName();
        String userName = oldAccount.getUsername();
        String password = oldAccount.getPassword();
        String timeCreated = oldAccount.getTimeAccountCreated();

        switch (type) {
            case "name":
                name = newAccount.getName();
                break;
            case "password":
                password = newAccount.getPassword();
                break;
            case "timeCreated":
                timeCreated = newAccount.getTimeAccountCreated();
                break;
            default:
                break;
        }

        // Update
        createDefaultPathData();
        File file = new File(ReadFile.PATH_DATA_ACCOUNT);
        if (file.exists()) {
            String[] nameFolders = file.list();
            for (String nameFolder : nameFolders) {
                String path1 = ReadFile.PATH_DATA_ACCOUNT + "/" + nameFolder;
                File tempFile = new File(path1);
                if (tempFile.isDirectory()) {
                    String path2 = path1 + "/" + "informations.txt";
                    File tempFile2 = new File(path2);
                    if (tempFile2.exists()) {
                        List<String> data = ReadFile.getStringLinesFromFile(path2);
                        // Check username to find account
                        if (data.get(1).equals(userName)) {
                            writeStringToFile(path2, name + "\n" + userName + "\n" + password + "\n" + timeCreated,
                                    false);
                            break;
                        }

                    }

                }
            }
        }
    }

    // Create new subject file
    public static void createNewSubject(String path, Subject subject) {
        String code = subject.getCode();
        String name = subject.getName();
        int credits = subject.getNumberCredits();
        int semester = subject.getSemester();

        String parentSubjectCodes = "";
        for (int count1 = 0; count1 < subject.getParentSubjectCodes().size(); count1++) {
            String[] parentCodes = subject.getParentSubjectCodes().get(count1);
            String temp = "";
            for (int count = 0; count < parentCodes.length; count++) {
                if (count > 0) {
                    temp += "/";
                }
                temp += parentCodes[count];
            }
            if (count1 > 0) {
                parentSubjectCodes += ";";
            }
            parentSubjectCodes += temp;
        }
        if (subject.getParentSubjectCodes().size() == 0) {
            parentSubjectCodes = ";";
        }

        int state = subject.getState();
        String characterScore = subject.getCharacterScore();
        String color = subject.getColor().getRed() + ";" + subject.getColor().getGreen() + ";"
                + subject.getColor().getBlue();
        double score10 = subject.getScore10();
        double score4 = subject.getScore4();
        int level = subject.getLevel();
        int rowIndexSorted = subject.getRowIndexSorted();
        int columnIndexSorted = subject.getColumnIndexSorted();
        int enable = (subject.getEnable()) ? 1 : 0;
        String timeTable = "";
        for (int index = 0; index < subject.getListTimeNames().size(); index++) {
            String timeName = subject.getListTimeNames().get(index);
            String timeEnable = (subject.getListEnableTimeLessons().get(index)) ? "1" : "0";
            String temp = timeName + "\n" + timeEnable + "\n";
            List<Integer> times = subject.getListTimes().get(index);
            for (int time : times) {
                temp += time + " ";
            }
            timeTable += temp + "\n";
        }

        String data = String.format("%s\n%s\n%d\n%s\n%d\n%s\n%s\n%s\n%s\n%d\n%d\n%d\n%d\n%d\n",
                code, name, credits, parentSubjectCodes, state, characterScore, color, score10, score4, semester,
                level, rowIndexSorted, columnIndexSorted, enable) + timeTable;

        writeStringToFile(path, data, false);
    }

    // Edit subject
    public static void editSubject(int indexPlan, int indexSubject, Subject subject) {
        String path = ReadFile.getPathCurrentAccount();
        String[] planFolderNames = (new File(path)).list();
        int tempIndexPlan = 0;
        for (String planFolderName : planFolderNames) {
            String path1 = path + "/" + planFolderName;
            File file = new File(path1);
            if (file.isDirectory()) {
                if (indexPlan == tempIndexPlan) {
                    int tempIndexSubject = 0;
                    File file1 = new File(path1);
                    for (String nameFile : file1.list()) {
                        if (nameFile.matches("subject[0-9]{1,}[.]txt")) {
                            if (tempIndexSubject == indexSubject) {
                                String path2 = path1 + "/" + nameFile;
                                createNewSubject(path2, subject);
                            }
                            tempIndexSubject++;
                        }
                    }

                }
                tempIndexPlan++;
            }
        }
    }

    // Create new subject in time table of plan
    public static void createNewSubjectTimeTable(int indexPlan, Subject subject) {
        String path = ReadFile.getPathCurrentAccount();
        String[] planFolderNames = (new File(path)).list();
        String path1 = "";
        int tempIndexPlan = 0;
        for (String planFolderName : planFolderNames) {
            String tempPath = path + "/" + planFolderName;
            File file = new File(tempPath);
            if (file.isDirectory()) {
                if (indexPlan == tempIndexPlan) {
                    path1 = tempPath;

                }
                tempIndexPlan++;
            }
        }

        // Create folder 'TImeTable'
        String path3 = path1 + "/TimeTable";
        File file0 = new File(path3);
        if (!file0.exists()) {
            file0.mkdirs();
        }

        // Create name folder for subject
        String path4 = path3 + "/count.txt";
        File file4 = new File(path4);
        if (!file4.exists()) {
            try {
                file4.createNewFile();
            } catch (Exception e) {

            }
            writeStringToFile(path4, "0", false);
        }
        int count = Integer.parseInt(ReadFile.getStringFromFile(path4));
        writeStringToFile(path4, (count + 1) + "", false);

        String path5 = path3 + "/" + String.format("subject%03d.txt", count);

        createNewSubject(path5, subject);

    }

    // Edit subject in time table of plan
    public static void editSubjectTimeTable(int indexPlan, int indexSubject, Subject subject) {
        String path = ReadFile.getPathCurrentAccount();
        String[] planFolderNames = (new File(path)).list();
        int tempIndexPlan = 0;
        for (String planFolderName : planFolderNames) {
            String path1 = path + "/" + planFolderName;
            File file = new File(path1);
            if (file.isDirectory()) {
                if (indexPlan == tempIndexPlan) {
                    int tempIndexSubject = 0;
                    String path2 = path1 + "/TimeTable";
                    File file1 = new File(path2);
                    for (String nameFile : file1.list()) {
                        if (nameFile.matches("subject[0-9]{1,}[.]txt")) {
                            if (tempIndexSubject == indexSubject) {
                                String path3 = path2 + "/" + nameFile;
                                createNewSubject(path3, subject);
                            }
                            tempIndexSubject++;
                        }
                    }

                }
                tempIndexPlan++;
            }
        }
    }

    // Remove subject in time table of plan
    public static void removeSubjectTimeTable(int indexPlan, int indexSubject) {
        String path = ReadFile.getPathCurrentAccount();
        String[] planFolderNames = (new File(path)).list();
        int tempIndexPlan = 0;
        for (String planFolderName : planFolderNames) {
            String path1 = path + "/" + planFolderName;
            File file = new File(path1);
            if (file.isDirectory()) {
                if (indexPlan == tempIndexPlan) {
                    int tempIndexSubject = 0;
                    String path2 = path1 + "/TimeTable";
                    File file1 = new File(path2);
                    for (String nameFile : file1.list()) {
                        if (nameFile.matches("subject[0-9]{1,}[.]txt")) {
                            if (tempIndexSubject == indexSubject) {
                                String path3 = path2 + "/" + nameFile;
                                removeFolder(new File(path3));
                            }
                            tempIndexSubject++;
                        }
                    }

                }
                tempIndexPlan++;
            }
        }
    }

    // Create new plan for current account
    public static void createNewPlan(Plan plan) {
        // Create folder for plan
        String path = ReadFile.getPathCurrentAccount();
        int count = Integer.parseInt(ReadFile.getStringFromFile(path + "/count.txt"));
        String namePlanFolder = String.format("plan%03d", count);
        writeStringToFile(path + "/count.txt", (count + 1) + "", false);
        String path1 = path + "/" + namePlanFolder;

        File file = new File(path1);
        if (!file.exists()) {
            file.mkdir();
        }

        // Create file information.txt in folder plan
        String path2 = path1 + "/informations.txt";
        File file2 = new File(path2);
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (Exception e) {

            }
        }
        // Write data for information.txt
        writeStringToFile(path2, String.format("%s\n%s\n%s\n%s\n%d", plan.getName(),
                plan.getSchoolName(), plan.getDepartmentName(), plan.getMajorName(), plan.getIndexConversionTable()),
                false);

        // Create files for each subject in plan
        List<Subject> subjects = plan.getSubjects();
        for (int i = 0; i < subjects.size(); i++) {
            // Create file for subject
            String tempPath = path1 + String.format("/subject%03d.txt", i);
            File tempFile = new File(tempPath);
            if (!tempFile.exists()) {
                try {
                    tempFile.createNewFile();
                } catch (Exception e) {

                }
            }
            // Write data

            createNewSubject(tempPath, subjects.get(i));
        }

        // Create folder 'TImeTable'
        String path3 = path1 + "/TimeTable";
        File file0 = new File(path3);
        if (!file0.exists()) {
            file0.mkdir();
        }

        // Create file information.txt in folder TimeTable
        String path4 = path3 + "/informations.txt";
        File file4 = new File(path4);
        if (!file4.exists()) {
            try {
                file4.createNewFile();
            } catch (Exception e) {

            }
        }

        // Write data for information.txt
        writeStringToFile(path4, String.format("%d", plan.getTimeTable().getMaxLessonPerDay()), false);

        // Create subject of timeTable
        List<Subject> subjectTimeTables = plan.getTimeTable().getSubjects();
        for (int i = 0; i < subjectTimeTables.size(); i++) {
            // Create file for subject
            String tempPath = path3 + String.format("/subject%03d.txt", i);
            File tempFile = new File(tempPath);
            if (!tempFile.exists()) {
                try {
                    tempFile.createNewFile();
                } catch (Exception e) {

                }
            }
            // Write data

            createNewSubject(tempPath, subjects.get(i));
        }

    }

    // Create new copy of plan for current account
    public static void createCopyPlan(Plan plan, String name) {
        // Create folder for plan
        String path = ReadFile.getPathCurrentAccount();
        int count = Integer.parseInt(ReadFile.getStringFromFile(path + "/count.txt"));
        String namePlanFolder = String.format("plan%03d", count);
        writeStringToFile(path + "/count.txt", (count + 1) + "", false);
        String path1 = path + "/" + namePlanFolder;

        File file = new File(path1);
        if (!file.exists()) {
            file.mkdir();
        }

        // Create file information.txt in folder plan
        String path2 = path1 + "/informations.txt";
        File file2 = new File(path2);
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (Exception e) {

            }
        }
        // Write data for information.txt
        writeStringToFile(path2, String.format("%s\n%s\n%s\n%s\n%d", name,
                plan.getSchoolName(), plan.getDepartmentName(), plan.getMajorName(), plan.getIndexConversionTable()),
                false);

        // Create files for each subject in plan
        List<Subject> subjects = plan.getSubjects();
        for (int i = 0; i < subjects.size(); i++) {
            // Create file for subject
            String tempPath = path1 + String.format("/subject%03d.txt", i);
            File tempFile = new File(tempPath);
            if (!tempFile.exists()) {
                try {
                    tempFile.createNewFile();
                } catch (Exception e) {

                }
            }
            // Write data
            createNewSubject(tempPath, subjects.get(i));
        }

        // Create folder 'TimeTable'
        String path3 = path1 + "/TimeTable";
        File file3 = new File(path3);
        if (!file3.exists()) {
            file3.mkdir();
        }

        // Create file information.txt in folder TimeTable
        String path4 = path3 + "/informations.txt";
        File file4 = new File(path4);
        if (!file4.exists()) {
            try {
                file4.createNewFile();
            } catch (Exception e) {

            }
        }

        // Write data for information.txt
        writeStringToFile(path4, String.format("%d", plan.getTimeTable().getMaxLessonPerDay()), false);

        List<Subject> subjectTimeTables = plan.getTimeTable().getSubjects();
        for (int i = 0; i < subjectTimeTables.size(); i++) {
            // Create file for subject
            String tempPath = path3 + String.format("/subject%03d.txt", i);
            File tempFile = new File(tempPath);
            if (!tempFile.exists()) {
                try {
                    tempFile.createNewFile();
                } catch (Exception e) {

                }
            }
            // Write data

            createNewSubject(tempPath, subjects.get(i));
        }
    }

    // Rename plan
    public static void renamePlan(Plan plan, int indexPlan, String name) {
        String path = ReadFile.getPathCurrentAccount();
        File file = new File(path);
        int count = 0;
        for (String nameFolder : file.list()) {
            String path1 = path + "/" + nameFolder;
            File tempFile = new File(path1);
            if (tempFile.isDirectory()) {
                if (count == indexPlan) {
                    String path2 = path1 + "/" + "informations.txt";
                    writeStringToFile(path2, String.format("%s\n%s\n%s\n%s\n%d", name,
                            plan.getSchoolName(), plan.getDepartmentName(), plan.getMajorName(),
                            plan.getIndexConversionTable()), false);
                }
                count++;
            }
        }
    }

    // Remove plan
    public static void removePlan(int indexPlan) {
        String path = ReadFile.getPathCurrentAccount();
        File file = new File(path);
        int count = 0;
        File removeFile = null;
        for (String nameFolder : file.list()) {
            String path1 = path + "/" + nameFolder;
            File tempFile = new File(path1);
            if (tempFile.isDirectory()) {
                if (count == indexPlan) {
                    removeFile = tempFile;
                }
                count++;
            }
        }
        removeFolder(removeFile);
    }

}
