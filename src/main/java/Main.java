import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        DatasetReader reader = new DatasetReader();


        try {
            books = reader.readBooks();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Query query = new Query(books);
        query.getBooksByAuthor("George R.R. Martin");

    }
}
