import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Query {

    private List<Book> books;
    HashMap<String, List<Book>> authorBooks = new HashMap<>();

    public Query(List<Book> books) {
        this.books = books;
        for (Book book : books) {
            if(!authorBooks.containsKey(book.getAuthor())) {
                authorBooks.put(book.getAuthor(), new ArrayList<>());
            }
            authorBooks.get(book.getAuthor()).add(book);
        }
    }

    public void getBooksByAuthor(String author) {
        for (Book book : authorBooks.get(author)) {
            System.out.println(book);
        }
    }
}
