package src.lab_assessment_02;

import java.io.*;
import java.util.Scanner;

public class FileFunctions {

    String fileName;
    int counter = 0;
        static int sum;

    public void createFile(String fileName) throws IOException {
        File file = new File(fileName);

        if (file.exists()) {
            System.out.println("File already exists");
        } else {
            file.createNewFile();
            System.out.println("New File Created!");
        }

        this.fileName = fileName;
    }

    public void writeIntoFile(FileWriter fw, String data) throws IOException {

        fw = new FileWriter(this.fileName, false);
        fw.write(data);
    }

    public String[] readFromFile(int size) throws IOException {

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
