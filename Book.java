package m2_group_project;

public class Book implements Describable {

	// adding this "id" to give you an idea on what options you can do
	private Integer id;
	private String title;
	private String author;
	// feel free to add fields that may help
	private boolean borrowed;
	

	public Book(Integer id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.borrowed = false; // default: available
    }


	// Getters / Setters
	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        // simple validation: id must be 1..5 for this project
	        if (id != null && id >= 1 && id <= 5) {
	            this.id = id;
	        }
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        if (title != null && title.trim().length() > 0) {
	            this.title = title.trim();
	        }
	    }

	    public String getAuthor() {
	        return author;
	    }

	    public void setAuthor(String author) {
	        if (author != null && author.trim().length() > 0) {
	            this.author = author.trim();
	        }
	    }

	    public boolean isBorrowed() {
	        return borrowed;
	    }

	    public void setBorrowed(boolean borrowed) {
	        this.borrowed = borrowed;
	    }

	    @Override
	    public String describe() {
	        String status = borrowed ? "BORROWED" : "AVAILABLE";
	        return String.format("Book #%d | \"%s\" by %s | %s", id, title, author, status);
	    }

}
