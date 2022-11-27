package code.file_handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import code.objects.Account;

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
        String nameFolder = "account" + count;
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

}
