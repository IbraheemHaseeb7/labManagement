package src.lab_assessment_02;

import java.io.*;
import java.util.Scanner;

public class FileFunctions {

    String fileName;
    int counter = 0;
        static int sum;

    public void createFile(String fileName) {

        try {

            File file = new File(fileName);

            if (file.exists()) {
                System.out.println("File already exists");
            } else {
                file.createNewFile();
                System.out.println("New File Created!");
            }

            this.fileName = fileName;
        } catch (Exception e) {
            System.out.println("Some unknown error occurred...");
        }

    }

    public void writeIntoFile(FileWriter fw, String data) {
        try {
            fw = new FileWriter(this.fileName, false);
            fw.write(data);
        } catch (Exception e) {
            System.out.println("Some error occurred hence exited without writing");
        }
    }

    public String[] readFromFile(int size) {

        try {
            String data[] = new String[size];
            File read = new File(this.fileName);
            Scanner file = new Scanner(read);

            sum = sum + counter;

            if (counter != 0) {
                for (int i = 0; i < sum; i++) {
                    file.nextLine();
                }
            }

            counter = 0;

            while (file.hasNextLine() && counter < size) {
                data[counter++] = file.nextLine();
            }

            file.close();

            return data;
        } catch (Exception e) {
            System.out.println("Some error occurred so nothing was read...");
        }
        return new String[10];
    }

    // side testing functions
    // dont bother looking into it
    public String findRecord(String fileName, int record) throws FileNotFoundException {

        File read = new File(fileName);
        Scanner file = new Scanner(read);
        String data = "";

        int i = 0;

        while (i < record) {
            data = file.nextLine();
            ++i;
        }

        return data;
    }

    public void deleteRecord() {

    }

}
