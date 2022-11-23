package code.file_handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import code.objects.Account;

public class WriteFile {
    // Create default path data to save data of this application
    public static void createDefaultPathData() {
        try {
            File file = new File(ReadFile.PATH_DATA);
            if (!file.exists()) {
                file.mkdirs();
                File file2 = new File(ReadFile.PATH_DATA_1);
                if (!file2.exists()) {
                    file2.createNewFile();
                }
                writeStringToFile(ReadFile.PATH_DATA_1, "1", false);
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
        int count = Integer.parseInt(ReadFile.getStringFromFile(ReadFile.PATH_DATA_1));
        String nameFolder = "account" + count;
        count++;
        // Write new data for C:/GPA_Plan/Accounts/count.txt
        writeStringToFile(ReadFile.PATH_DATA_1, count + "", false);

        // Create new folder - where save new account
        File file = new File(ReadFile.PATH_DATA + "/" + nameFolder);
        file.mkdir();
        // Create count.txt for new account (use to create for plans of this account
        // after)
        File file2 = new File(ReadFile.PATH_DATA + "/" + nameFolder + "/count.txt");
        try {
            file2.createNewFile();
            writeStringToFile(ReadFile.PATH_DATA + "/" + nameFolder + "/count.txt",
                    "1", false);
        } catch (Exception e) {

        }
        // Create informations.txt to save name and other
        File file3 = new File(ReadFile.PATH_DATA + "/" + nameFolder + "/informations.txt");
        try {
            file3.createNewFile();
            writeStringToFile(ReadFile.PATH_DATA + "/" + nameFolder + "/informations.txt",
                    account.getName(), false);
        } catch (Exception e) {

        }
    }

}
