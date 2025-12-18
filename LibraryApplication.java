package m2_group_project;

import java.util.Scanner;

/*
 * 1. Upon application start, ask user to create one User
 * 2. Create one Library object
 * 3. Initialize 5 Book objects and add it to all Library slots
 * 4. Display options:
 * 
 * - [1] Display All Books
 * - [2] Display Available Books
 * - [3] Display All Borrowed Books
 * - [4] Borrow Book
 * - [5] Return Book
 * - [6] Exit
 * 
 * - user selects the number of the option
 * ===============================================
 * 
 *	 [1] Display All Books
 * - Display all Books (ID, Title and Author) regardless if there is a Loan existing for that Book.
 *   
 *   [2] Display Available Books
 * - Display Books that do not have a Loan slot
 * 
 *   [3] Display All Borrowed Books 
 * - Display Books that have a Loan equivalent.
 * - Display the Book title and the User name of borrower
 *   
 *	 [4] Borrow Book
 * - Displays all available books and User selects what book to borrow
 * - Create a Loan object, set Loan id set Book and set User to current user
 * 
 * 	 [5] Return Book
 * - Display all Loans, user selects the Loan and removes that from the slot
 * 
 *   [6] Exit
 * - Stops the program  
 * */


public class LibraryApplication {

    private User user;
    private Library library;

    // Main Application Logic, call this in your Main.java
    public void start() {
        // initial user creation
        this.user = new User();

        // initial library creation
        this.library = new Library();

        // --- Input + setup ---
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Library System!");
        System.out.print("Enter your name: ");
        String nameInput = scanner.nextLine();
        if (nameInput == null || nameInput.trim().length() == 0) {
            // input validation (no exception handling)
            nameInput = "Guest";
        }
        user.setName(nameInput.trim());

        // preload 5 books (IDs 1..5)
        library.addBook(new Book(1, "Clean Code", "Robert C. Martin"));
        library.addBook(new Book(2, "Effective Java", "Joshua Bloch"));
        library.addBook(new Book(3, "Design Patterns", "Gamma et al."));
        library.addBook(new Book(4, "Java Concurrency in Practice", "Brian Goetz"));
        library.addBook(new Book(5, "Refactoring", "Martin Fowler"));

        // --- Main menu loop ---
        boolean running = true;
        while (running) {
            printMenu(user.getName());
            String choice = scanner.nextLine();

            if (!isNumeric(choice)) {
                System.out.println("Invalid input. Please enter a number shown in the menu.");
                continue;
            }

            int option = Integer.parseInt(choice);

            switch (option) {
                case 1:
                    library.displayAllBooks();
                    break;
                case 2:
                    library.displayAvailableBooks();
                    break;
                case 3:
                    library.displayBorrowedBooks();
                    break;
                case 4:
                    // Borrow flow
                    int borrowId = readInt(scanner, "Enter Book ID (1-5) to BORROW: ", 1, 5);
                    boolean borrowed = library.borrowBook(user, borrowId);
                    if (borrowed) {
                        System.out.println("Success: You borrowed book #" + borrowId + ".");
                    } else {
                        // reasons could be: invalid ID, already borrowed, capacity full, user limit reached
                        System.out.println("Borrow failed: Check if the book exists, is available, "
                                + "and you haven't reached your 5-book limit.");
                    }
                    break;
                case 5:
                    // Return flow
                    int returnId = readInt(scanner, "Enter Book ID (1-5) to RETURN: ", 1, 5);
                    boolean returned = library.returnBook(user, returnId);
                    if (returned) {
                        System.out.println("Success: You returned book #" + returnId + ".");
                    } else {
                        System.out.println("Return failed: Either the book doesn't exist or you haven't borrowed it.");
                    }
                    break;
                case 0:
                    running = false;
                    System.out.println("Goodbye, " + user.getName() + "!");
                    break;
                default:
                    System.out.println("Invalid option. Please pick from the menu.");
            }
        }

        // Do not close Scanner tied to System.in in small console apps
        // scanner.close();
    }

    // --- Helpers (no exceptions; simple validation) ---
    private void printMenu(String userName) {
        System.out.println("\n==========================================");
        System.out.println("Library Menu - User: " + userName);
        System.out.println("1) Display ALL books");
        System.out.println("2) Display AVAILABLE books");
        System.out.println("3) Display BORROWED books (Loan details)");
        System.out.println("4) Borrow a book");
        System.out.println("5) Return a book");
        System.out.println("0) Exit");
        System.out.print("Choose an option: ");
    }

    // Validate numeric input without try-catch (avoid exceptions per requirements)
    private boolean isNumeric(String text) {
        if (text == null || text.length() == 0) return false;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < '0' || c > '9') return false;
        }
        return true;
    }

    private int readInt(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine();
            if (!isNumeric(s)) {
                System.out.println("Please enter a valid number.");
                continue;
            }
            int value = Integer.parseInt(s); // safe because we validated digits only
                       if (value < min || value > max) {
                System.out.println("Please enter a number between " + min + " and " + max + ".");
                continue;
            }
            return value;
        }
    }
    
}

