import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatasetReader extends Reader{
    private final String filePath = "/Users/venkateshalampally/IdeaProjects/BookReader/src/dataset/bestsellers with categories.csv";
    public List<Book> readBooks() throws IOException {
        List<Book> books = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {
            String line = reader.readLine(); // Skip header

            while ((line = reader.readLine()) != null) {
                try {
                    books.add(createBook(line));
                } catch (IllegalArgumentException e) {
//                    System.err.println("Error parsing line: " + line);
//                    System.err.println("Error message: " + e.getMessage());
                }
            }
        }

        return books;
    }

    private Genre parseGenre(String genreStr) {
        return switch (genreStr.toLowerCase().replace(" ", "_")) {
            case "fiction" -> Genre.FICTION;
            case "non_fiction" -> Genre.NON_FICTION;
            default -> throw new IllegalArgumentException("Unknown genre: " + genreStr);
        };
    }

    private Book createBook(String line) {
        String[] parts = line.split(",");
        if (parts.length != 7) {
            throw new IllegalArgumentException("Invalid number of fields");
        }

        try {
            String title = parts[0];
            String author = parts[1];
            float rating = Float.parseFloat(parts[2]);
            long reviews = Long.parseLong(parts[3]);
            int price = Integer.parseInt(parts[4]);
            int year = Integer.parseInt(parts[5]);
            Genre genre = parseGenre(parts[6]);

            return new Book(title, author, rating, reviews, price, year, genre);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format: " + e.getMessage());
        }
    }

}
