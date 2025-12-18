package m2_group_project;

public class Library {
	// can contain up to 5 books	

private Book[] books = new Book[5];
    private Loan[] loans = new Loan[5];
    private int bookCount = 0;
    private int loanCount = 0;

    // Add a book into the library (setup phase)
    public boolean addBook(Book book) {
        if (book == null) return false;
        if (bookCount >= books.length) return false; // max 5
        // place in first null slot
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                books[i] = book;
                bookCount++;
                return true;
            }
        }
        return false;
    }

    public Book findBookById(int id) {
        for (int i = 0; i < books.length; i++) {
            Book b = books[i];
            if (b != null && b.getId() != null && b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    public boolean isBookAvailable(int id) {
        Book b = findBookById(id);
        return b != null && !b.isBorrowed();
    }

    // Borrow book: creates a Loan, marks book borrowed, records in user + library
    public boolean borrowBook(User user, int bookId) {
        if (user == null) return false;
        if (user.getLoanCount() >= 5) return false; // user's limit

        Book b = findBookById(bookId);
        if (b == null) return false; // invalid id
        if (b.isBorrowed()) return false; // already borrowed

        if (loanCount >= loans.length) return false; // library loan capacity full

        Loan loan = new Loan(user, b);

        // add to library loans (first null slot)
        boolean placed = false;
        for (int i = 0; i < loans.length; i++) {
            if (loans[i] == null) {
                loans[i] = loan;
                loanCount++;
                placed = true;
                break;
            }
        }
        if (!placed) return false;

        // update book + user
        b.setBorrowed(true);
        user.addLoan(loan);
        return true;
    }

    // Return book: remove matching loan, mark book available, remove from user
    public boolean returnBook(User user, int bookId) {
        if (user == null) return false;
        Book b = findBookById(bookId);
        if (b == null) return false;

        // locate loan in library loans
        for (int i = 0; i < loans.length; i++) {
            Loan loan = loans[i];
            if (loan != null
                && loan.getBook() != null
                && loan.getBook().getId() == bookId
                && loan.getUser() == user) {
                loans[i] = null;
                loanCount--;
                b.setBorrowed(false);
                user.removeLoanByBookId(bookId);
                return true;
            }
        }
        return false; // no loan found for this user/book
    }

    // Display helpers (print directly to console)
    public void displayAllBooks() {
        System.out.println("\n=== ALL BOOKS ===");
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null) {
                System.out.println(books[i].describe());
            }
        }
    }

    public void displayAvailableBooks() {
        System.out.println("\n=== AVAILABLE BOOKS ===");
        for (int i = 0; i < books.length; i++) {
            Book b = books[i];
            if (b != null && !b.isBorrowed()) {
                System.out.println(b.describe());
            }
        }
    }

    public void displayBorrowedBooks() {
        System.out.println("\n=== BORROWED BOOKS & LOAN DETAILS ===");
        for (int i = 0; i < loans.length; i++) {
            Loan loan = loans[i];
            if (loan != null) {
                System.out.println(loan.describe());
            }
        }
    }

}
