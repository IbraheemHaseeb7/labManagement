package lab_assessment_02;

import java.io.*;
import java.util.Scanner;

public class FileFunctions {

    String fileName;
    int counter = 0;

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

    public void writeIntoFile(String data) throws IOException {

        FileWriter fw = new FileWriter(this.fileName, false);
        fw.write(data);
        fw.close();

    }

    public String[] readFromFile(int size) throws FileNotFoundException {

        String data[] = new String[size];
        File read = new File(this.fileName);
        Scanner file = new Scanner(read);

        while (file.hasNextLine() && counter < 10) {
            data[counter++] = file.nextLine();
        }

        return data;
    }

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
