import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatasetReader {

    public static List<Book> readDataset(String filename) {
        List<Book> books = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                // Skip header line
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                Book book = parseLine(line);
                if (book != null) {
                    books.add(book);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return books;
    }

    /**
     * Parses a single line from CSV and creates a Book object
     * @param line CSV line to parse
     * @return Book object or null if parsing fails
     */
    private static Book parseLine(String line) {
        try {
            // Split by comma, but handle commas within quotes
            String[] fields = parseCSVLine(line);

            if (fields.length != 7) {
                System.err.println("Malformed line (expected 7 fields): " + line);
                return null;
            }

            String title = fields[0].trim();
            String author = fields[1].trim();
            float userRating = Float.parseFloat(fields[2].trim());
            long reviews = Long.parseLong(fields[3].trim());
            int price = Integer.parseInt(fields[4].trim());
            int year = Integer.parseInt(fields[5].trim());
            Genre genre = Genre.fromString(fields[6].trim());

            return new Book(title, author, userRating, reviews, price, year, genre);

        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            System.err.println("Error parsing line: " + line + " - " + e.getMessage());
            return null;
        }
    }

    /**
     * Parses CSV line handling commas within quotes
     * @param line CSV line to parse
     * @return array of field values
     */
    private static String[] parseCSVLine(String line) {
        List<String> fields = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder field = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                fields.add(field.toString());
                field = new StringBuilder();
            } else {
                field.append(c);
            }
        }

        // Add the last field
        fields.add(field.toString());

        return fields.toArray(new String[0]);
    }
}