import java.io.IOException;
import java.util.List;

public abstract class Reader {
    public abstract List<Book> readBooks() throws IOException;
}
