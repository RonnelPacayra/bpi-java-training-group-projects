package m2_group_project;

public class User {

	private String name;

	private Loan[] loans = new Loan[5]; // user can borrow up to 5 books
    private int loanCount = 0;

    public User() { }

    public User(String name) {
        setName(name);
    }

    // Getters / Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && name.trim().length() > 0) {
            this.name = name.trim();
        }
    }

    public int getLoanCount() {
        return loanCount;
    }

    public Loan[] getLoans() {
        return loans;
    }

    // Add/remove loan helpers (no collections)
    public boolean addLoan(Loan loan) {
        if (loan == null) return false;
        if (loanCount >= loans.length) return false; // max 5
        // put in first null slot
        for (int i = 0; i < loans.length; i++) {
            if (loans[i] == null) {
                loans[i] = loan;
                loanCount++;
                return true;
            }
        }
        return false;
    }

    public boolean removeLoanByBookId(int bookId) {
        for (int i = 0; i < loans.length; i++) {
            Loan loan = loans[i];
            if (loan != null && loan.getBook() != null && loan.getBook().getId() == bookId) {
                loans[i] = null;
                loanCount--;
                // compacting not strictly required; keep simple
                return true;
            }
        }
        return false;
    }

    public boolean hasLoanForBookId(int bookId) {
        for (int i = 0; i < loans.length; i++) {
            Loan loan = loans[i];
            if (loan != null && loan.getBook() != null && loan.getBook().getId() == bookId) {
                return true;
            }
        }
        return false;
    }

}
