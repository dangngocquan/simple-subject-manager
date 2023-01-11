package src.launcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import src.objects.Account;
import src.objects.Plan;
import src.objects.Subject;
import src.objects.TimeTable;

public class ReadFile {
    public static final String PATH_DATA = "C:/SimpleSubjectManager";
    public static final String PATH_DATA_TEMP = "C:/SimpleSubjectManager/Temp";
    public static final String PATH_DATA_TEMP_1 = "C:/SimpleSubjectManager/Temp/tempAccount.txt";
    public static final String PATH_DATA_ACCOUNT = "C:/SimpleSubjectManager/Accounts";
    public static final String PATH_DATA_ACCOUNT_1 = "C:/SimpleSubjectManager/Accounts/count.txt";

    public static ReadFile instance;

    public ReadFile() {
        instance = this;
    }

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
        int semester = Integer.parseInt(data.get(9));
        int level = Integer.parseInt(data.get(10));
        int rowIndexSorted = Integer.parseInt(data.get(11));
        int columnIndexSorted = Integer.parseInt(data.get(12));
        boolean enable = data.get(13).equals("1");
        List<List<Integer>> listTimes = new LinkedList<>();
        List<String> listTimeNames = new LinkedList<>();
        List<Boolean> listTimeLessonEnable = new LinkedList<>();

        for (int indexLine = 14; indexLine < data.size(); indexLine += 3) {
            // time name
            String line0 = data.get(indexLine);
            listTimeNames.add(line0);

            // Time enable
            Boolean line1 = data.get(indexLine + 1).equals("1");
            listTimeLessonEnable.add(line1);

            // time lesson
            String line = data.get(indexLine + 2);
            List<Integer> tempList = new LinkedList<>();
            String[] times = line.split(" ");
            for (String num : times) {
                if (num.matches("[0-9]{1,}")) {
                    tempList.add(Integer.parseInt(num));
                }
            }
            listTimes.add(tempList);
        }

        Subject subject = new Subject(name, code, credits);
        for (String[] parentCode : parentSubjectCodes) {
            subject.addParentSubjectCode(parentCode);
        }
        subject.setCharacterScore(characterScore);
        subject.setState(state);
        subject.setColor(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
        subject.setScore10(score10);
        subject.setScore4(score4);
        subject.setSemester(semester);
        subject.setLevel(level);
        subject.setRowIndexSorted(rowIndexSorted);
        subject.setColumnIndexSorted(columnIndexSorted);
        subject.setEnable(enable);
        subject.setListTimeNames(listTimeNames);
        subject.setListTimes(listTimes);
        subject.setLIstEnableTimeLessons(listTimeLessonEnable);
        return subject;
    }

    // Get Plan from file
    public static Plan getPlanFromFile(String path) {
        List<String> data = getStringLinesFromFile(path + "/informations.txt");
        String name = data.get(0);
        String schoolName = "", departmentName = "", majorName = "";
        schoolName = data.get(1);
        departmentName = data.get(2);
        majorName = data.get(3);
        int indexConversionTable = Integer.parseInt(data.get(4));
        List<Subject> subjects = new LinkedList<Subject>();
        TimeTable timeTable = new TimeTable();

        File file = new File(path);
        for (String nameFile : file.list()) {
            String tempPath = path + "/" + nameFile;
            if (nameFile.matches("subject[0-9]{1,}[.]txt")) {
                subjects.add(getSubjectFromFile(tempPath));
            } else if (nameFile.equals("TimeTable")) {
                File file1 = new File(tempPath);
                for (String nameFile2 : file1.list()) {
                    String tempPath2 = tempPath + "/" + nameFile2;
                    if (nameFile2.matches("subject[0-9]{1,}[.]txt")) {
                        timeTable.addSubject(getSubjectFromFile(tempPath2));
                    } else if (nameFile2.equals("informations.txt")) {
                        timeTable.setMaxLessonPerDay(Integer.parseInt(getStringFromFile(tempPath2)));
                    }
                }
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
        plan.setSchoolName(schoolName);
        plan.setDepartmentName(departmentName);
        plan.setMajorName(majorName);
        plan.setTimeTable(timeTable);
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

}
