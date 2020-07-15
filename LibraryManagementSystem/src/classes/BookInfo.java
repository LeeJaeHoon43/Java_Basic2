package classes;

public class BookInfo {

    protected String title;
    protected String isbn;
    protected String author;
    protected String publisher;
    private String categories;
    private String subcategories;
    private int year;
    private int total;

    public BookInfo(String title, String isbn, String author, String publisher, String categories, String subcategories, int year, int total) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.categories = categories;
        this.subcategories = subcategories;
        this.year = year;
        this.total = total;
    }
    public BookInfo(String title, String isbn, String author, String publisher, int total) {
		super();
		this.title = title;
		this.isbn = isbn;
		this.author = author;
		this.publisher = publisher;
		this.total = total;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(String subcategories) {
        this.subcategories = subcategories;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
