import java.util.*;
import java.util.stream.Collectors;

/**
 * BookService class implementing BookAnalyzer interface
 * Demonstrates polymorphism through interface implementation
 * Uses abstraction to hide implementation details
 */
public class BookService implements BookAnalyzer {
    private List<Book> books;

    // Constructor demonstrating dependency injection
    public BookService(List<Book> books) {
        this.books = new ArrayList<>(books); // Defensive copying for encapsulation
    }

    /**
     * Implementation of getTotalBooksByAuthor from BookAnalyzer interface
     * Demonstrates polymorphism - this method can be called through interface reference
     */
    @Override
    public int getTotalBooksByAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            return 0;
        }

        return (int) books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author.trim()))
                .count();
    }

    /**
     * Implementation of getAllAuthors from BookAnalyzer interface
     */
    @Override
    public List<String> getAllAuthors() {
        return books.stream()
                .map(Book::getAuthor)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Implementation of getBooksByAuthor from BookAnalyzer interface
     */
    @Override
    public List<String> getBooksByAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            return new ArrayList<>();
        }

        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author.trim()))
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    /**
     * Implementation of getBooksByRating from BookAnalyzer interface
     */
    @Override
    public List<Book> getBooksByRating(float rating) {
        return books.stream()
                .filter(book -> Math.abs(book.getUserRating() - rating) < 0.01f) // Handle float precision
                .collect(Collectors.toList());
    }

    /**
     * Implementation of getBookPricesByAuthor from BookAnalyzer interface
     */
    @Override
    public List<BookPrice> getBookPricesByAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            return new ArrayList<>();
        }

        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author.trim()))
                .map(book -> new BookPrice(book.getTitle(), book.getPrice()))
                .collect(Collectors.toList());
    }

    // Additional utility methods demonstrating encapsulation

    /**
     * Gets books by genre (additional functionality)
     */
    public List<Book> getBooksByGenre(Genre genre) {
        if (genre == null) {
            return new ArrayList<>();
        }

        return books.stream()
                .filter(book -> book.getGenre() == genre)
                .collect(Collectors.toList());
    }

    /**
     * Gets books within a price range
     */
    public List<Book> getBooksByPriceRange(int minPrice, int maxPrice) {
        return books.stream()
                .filter(book -> book.getPrice() >= minPrice && book.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    /**
     * Gets top rated books (rating >= threshold)
     */
    public List<Book> getTopRatedBooks(float ratingThreshold) {
        return books.stream()
                .filter(book -> book.getUserRating() >= ratingThreshold)
                .sorted((b1, b2) -> Float.compare(b2.getUserRating(), b1.getUserRating()))
                .collect(Collectors.toList());
    }

    /**
     * Gets total number of books in dataset
     */
    public int getTotalBooks() {
        return books.size();
    }

    /**
     * Prints statistics about the dataset
     */
    public void printDatasetStatistics() {
        System.out.println("=== Dataset Statistics ===");
        System.out.println("Total books: " + getTotalBooks());
        System.out.println("Total authors: " + getAllAuthors().size());

        Map<Genre, Long> genreCount = books.stream()
                .collect(Collectors.groupingBy(Book::getGenre, Collectors.counting()));

        System.out.println("Books by genre:");
        genreCount.forEach((genre, count) ->
                System.out.println("  " + genre + ": " + count));

        OptionalDouble avgRating = books.stream()
                .mapToDouble(Book::getUserRating)
                .average();

        if (avgRating.isPresent()) {
            System.out.println("Average rating: " + String.format("%.2f", avgRating.getAsDouble()));
        }

        OptionalDouble avgPrice = books.stream()
                .mapToDouble(Book::getPrice)
                .average();

        if (avgPrice.isPresent()) {
            System.out.println("Average price: $" + String.format("%.2f", avgPrice.getAsDouble()));
        }

        System.out.println("========================");
    }
}