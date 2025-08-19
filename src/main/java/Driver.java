import java.util.List;
import java.util.Scanner;

/**
 * Driver class - Main entry point of the application
 * Demonstrates all OOP principles through interaction with other classes
 */
public class Driver {
    private static final String CSV_FILE = "src/dataset/bestsellers with categories.csv";
    private static BookAnalyzer bookAnalyzer; // Using interface reference (Polymorphism)

    public static void main(String[] args) {
        System.out.println("=== Amazon Top 50 Bestselling Books Analysis ===\n");

        // Read dataset using abstraction (hiding file reading complexity)
        List<Book> books = DatasetReader.readDataset(CSV_FILE);

        if (books.isEmpty()) {
            System.err.println("No books loaded. Please check if " + CSV_FILE + " exists.");
            return;
        }

        // Create service using polymorphism (interface reference)
        bookAnalyzer = new BookService(books);

        System.out.println("Successfully loaded " + books.size() + " books from dataset.\n");

        // Display menu and handle user interactions
        Scanner scanner = new Scanner(System.in);
        displayMenu();

        while (true) {
            System.out.print("Enter your choice (1-7, or 0 to exit): ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (choice == 0) {
                    System.out.println("Thank you for using the Book Analysis System!");
                    break;
                }

                handleUserChoice(choice, scanner);

            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }

            System.out.println("\n" + "=".repeat(50) + "\n");
        }

        scanner.close();
    }

    /**
     * Displays the main menu options
     */
    private static void displayMenu() {
        System.out.println("Available Operations:");
        System.out.println("1. Get total number of books by an author");
        System.out.println("2. Display all authors in the dataset");
        System.out.println("3. Get all books by an author");
        System.out.println("4. Find books by user rating");
        System.out.println("5. Get book prices by author");
        System.out.println("6. Display dataset statistics");
        System.out.println("7. Display sample books");
        System.out.println("0. Exit");
        System.out.println("-".repeat(50));
    }

    /**
     * Handles user menu choices
     * Demonstrates polymorphism by calling interface methods
     */
    private static void handleUserChoice(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                handleTotalBooksByAuthor(scanner);
                break;
            case 2:
                handleDisplayAllAuthors();
                break;
            case 3:
                handleBooksByAuthor(scanner);
                break;
            case 4:
                handleBooksByRating(scanner);
                break;
            case 5:
                handleBookPricesByAuthor(scanner);
                break;
            case 6:
                handleDatasetStatistics();
                break;
            case 7:
                handleDisplaySampleBooks();
                break;
            default:
                System.out.println("Invalid choice. Please select 1-7 or 0 to exit.");
        }
    }

    /**
     * Task 1: Total number of books by an author
     */
    private static void handleTotalBooksByAuthor(Scanner scanner) {
        System.out.print("Enter author name: ");
        String author = scanner.nextLine().trim();

        if (author.isEmpty()) {
            System.out.println("Author name cannot be empty.");
            return;
        }

        // Using polymorphism - calling method through interface reference
        int totalBooks = bookAnalyzer.getTotalBooksByAuthor(author);

        if (totalBooks == 0) {
            System.out.println("No books found for author: " + author);
        } else {
            System.out.println("Total books by " + author + ": " + totalBooks);
        }
    }

    /**
     * Task 2: All authors in the dataset
     */
    private static void handleDisplayAllAuthors() {
        List<String> authors = bookAnalyzer.getAllAuthors();

        System.out.println("All authors in the dataset (" + authors.size() + " total):");
        System.out.println("-".repeat(40));

        for (int i = 0; i < authors.size(); i++) {
            System.out.println((i + 1) + ". " + authors.get(i));
        }
    }

    /**
     * Task 3: Names of all books by an author
     */
    private static void handleBooksByAuthor(Scanner scanner) {
        System.out.print("Enter author name: ");
        String author = scanner.nextLine().trim();

        if (author.isEmpty()) {
            System.out.println("Author name cannot be empty.");
            return;
        }

        List<String> books = bookAnalyzer.getBooksByAuthor(author);

        if (books.isEmpty()) {
            System.out.println("No books found for author: " + author);
        } else {
            System.out.println("Books by " + author + " (" + books.size() + " total):");
            System.out.println("-".repeat(40));
            for (int i = 0; i < books.size(); i++) {
                System.out.println((i + 1) + ". " + books.get(i));
            }
        }
    }

    /**
     * Task 4: Classify with a user rating
     */
    private static void handleBooksByRating(Scanner scanner) {
        System.out.print("Enter user rating (e.g., 4.7): ");
        try {
            float rating = scanner.nextFloat();
            scanner.nextLine(); // Consume newline

            List<Book> books = bookAnalyzer.getBooksByRating(rating);

            if (books.isEmpty()) {
                System.out.println("No books found with rating: " + rating);
            } else {
                System.out.println("Books with rating " + rating + " (" + books.size() + " total):");
                System.out.println("-".repeat(60));
                for (Book book : books) {
                    System.out.println("• " + book.getTitle() + " by " + book.getAuthor());
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid rating format. Please enter a decimal number.");
            scanner.nextLine(); // Clear invalid input
        }
    }

    /**
     * Task 5: Price of all books by an author
     */
    private static void handleBookPricesByAuthor(Scanner scanner) {
        System.out.print("Enter author name: ");
        String author = scanner.nextLine().trim();

        if (author.isEmpty()) {
            System.out.println("Author name cannot be empty.");
            return;
        }

        List<BookAnalyzer.BookPrice> bookPrices = bookAnalyzer.getBookPricesByAuthor(author);

        if (bookPrices.isEmpty()) {
            System.out.println("No books found for author: " + author);
        } else {
            System.out.println("Books and prices by " + author + ":");
            System.out.println("-".repeat(50));
            for (BookAnalyzer.BookPrice bookPrice : bookPrices) {
                System.out.println("• " + bookPrice);
            }
        }
    }

    /**
     * Display dataset statistics
     */
    private static void handleDatasetStatistics() {
        if (bookAnalyzer instanceof BookService) {
            // Demonstrating downcasting when needed
            BookService service = (BookService) bookAnalyzer;
            service.printDatasetStatistics();
        }
    }

    /**
     * Display sample books from the dataset
     */
    private static void handleDisplaySampleBooks() {
        // Get some sample books (first 5) to display
        List<String> authors = bookAnalyzer.getAllAuthors();

        System.out.println("Sample books from the dataset:");
        System.out.println("-".repeat(60));

        int count = 0;
        for (String author : authors) {
            if (count >= 5) break;

            List<String> books = bookAnalyzer.getBooksByAuthor(author);
            if (!books.isEmpty()) {
                System.out.println("Author: " + author);
                System.out.println("  Book: " + books.get(0));
                count++;
            }
        }
    }
}