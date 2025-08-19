public class Book {
    private String title;
    private String author;
    private float userRating;
    private long reviews;
    private int price;
    private int year;
    private Genre genre;

    public Book(String title, String author, float userRating, long reviews, int price, int year, Genre genre) {
        this.title = title;
        this.author = author;
        this.userRating = userRating;
        this.reviews = reviews;
        this.price = price;
        this.year = year;
        this.genre = genre;
    }

    public String toString() {
        return String.format("%s by %s (%.2f/5) (%d reviews) $%d in %d (%s)", title, author, userRating, reviews, price, year, genre);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getUserRating() {
        return userRating;
    }

    public void setUserRating(float userRating) {
        this.userRating = userRating;
    }

    public long getReviews() {
        return reviews;
    }

    public void setReviews(long reviews) {
        this.reviews = reviews;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
