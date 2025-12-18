package m1_group_project;

import java.util.Scanner;

public class M1_Group_Project {

	static Scanner input = new Scanner(System.in);
	
    static String studentName = "";
    static int studentId = 0;
    static int numberOfSubjects = 0;
    static double[] grades = null;
    static double average = 0.0;
    static String status = "";
    
    public static void main(String[] args) {

        char choice = ' ';
        
        while (choice != 'D') {
            displayMenu();
            System.out.print("Enter choice: ");
            choice = input.next().toUpperCase().charAt(0);

            switch (choice) {
                case 'A':
                    inputStudentInfo();
                    break;

                case 'B':
                    if (grades == null) {
                        System.out.println("No student data found. Please add student info first.");
                    } else {
                        average = computeAverage(grades);
                        status = getStatus(average);
                        System.out.println("Average: " + average);
                        System.out.println("Status: " + status);
                    }
                    break;

                case 'C':
                    displayStudentInfo();
                    break;

                case 'D':
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter A, B, C, or D.");
            } // end of switch

        } 

        input.close();
    } // end of main method

    static void displayMenu() {
        System.out.println("\n===== STUDENT GRADING SYSTEM =====");
        System.out.println("A - Add Student Information");
        System.out.println("B - Compute Student Average");
        System.out.println("C - Display Student Information");
        System.out.println("D - Exit");
    }

    static void inputStudentInfo() {
        System.out.print("Enter student name: ");
        input.nextLine();
        studentName = input.nextLine();

        System.out.print("Enter student ID: ");
        studentId = input.nextInt();

        System.out.print("Enter number of subjects: ");
        numberOfSubjects = input.nextInt();

        grades = new double[numberOfSubjects];

        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.print("Enter grade for subject " + (i + 1) + ": ");
            grades[i] = input.nextDouble();
        }

        System.out.println("===== STUDENT SAVED =====");
    }

    static double computeAverage(double[] grades) {
        double sum = 0;
        for (int i = 0; i < grades.length; i++) {
            sum += grades[i];
        }
        return sum / grades.length;
    }

    static String getStatus(double avg) {
    	if (avg >= 75) {
    		return "PASS";
    	} else {
    		return "FAIL";
    	}
    }

    static void displayStudentInfo() {
        if (grades == null) {
            System.out.println("No student data found. Please add student info first.");
            return;
        }

        System.out.println("\n===== STUDENT SUMMARY =====");
        System.out.println("Student Name: " + studentName);
        System.out.println("Student ID: " + studentId);
        System.out.println("Average Grade: " + average);
        System.out.println("Status: " + status);
        System.out.println("===========================");
    } 

}
