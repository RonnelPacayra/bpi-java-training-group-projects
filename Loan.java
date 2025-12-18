package m2_group_project;

public class Loan implements Describable {

	private User user;
	private Book book;
	
	public Loan(User user, Book book) {
        this.user = user;
        this.book = book;
    }

    // Getters / Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        if (user != null) {
            this.user = user;
        }
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        if (book != null) {
            this.book = book;
        }
    }

    @Override
    public String describe() {
        String userName = (user != null && user.getName() != null) ? user.getName() : "(unknown user)";
        String bookInfo = (book != null) ? String.format("#%d \"%s\" by %s",
                book.getId(), book.getTitle(), book.getAuthor()) : "(no book)";
        return String.format("Loan -> %s borrowed Book %s", userName, bookInfo);
    }

	
}
