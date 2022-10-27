package src.lab_assessment_02;

import java.io.IOException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws IOException {

        new Menu().fetchData().launchProgram().writeData();
    }
}
