package Lesson3.homework;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class task {
    public static void main(String[] args) throws FileSystemException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your Last name, First name, Middle name, date of birth, phone number, gender:");
        String input = scanner.nextLine();

        try {
            String[] data = input.split(" ");
            if (data.length < 5) {
                throw new IllegalArgumentException("There is not enough data. Please check and add!");
            }
            String lastName = data[0];
            String firstName = data[1];
            String middleName = data[2];
            Date dateOfBirth = parseDate(data[3]);
            int phoneNumber = Integer.parseInt(data[4]);
            String gender = data[5];
            if (!gender.toLowerCase().equals("m") && !gender.toLowerCase().equals("f")) {
                throw new RuntimeException("The recipient was entered incorrectly");
            }

            System.out.println("Last name: " + lastName);
            System.out.println("First name: " + firstName);
            System.out.println("Middle name: " + middleName);
            System.out.println("Date of birth: " + formatDate(dateOfBirth));
            System.out.println("Phone number: " + phoneNumber);
            System.out.println("Gender: " + gender);

            String fileName = "files\\" + lastName.toLowerCase() + ".txt";
            File file = new File(fileName);
            try (FileWriter fileWriter = new FileWriter(file, true)) {
                if (file.length() > 0) {
                    fileWriter.write('\n');
                }
                fileWriter.write(
                        String.format("%s %s %s %s %s %s", lastName, firstName, middleName, formatDate(dateOfBirth),
                                phoneNumber, gender));
            } catch (IOException e) {
                throw new FileSystemException("An error occurred while working with the file");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Error: Invalid date of birth format");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Mistake: Insufficient data");
        }

    }

    private static Date parseDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return (Date) format.parse(date);
    }

    private static String formatDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(date);
    }

}
