import java.util.List;

public interface BookAnalyzer {

    int getTotalBooksByAuthor(String author);

    List<String> getAllAuthors();

    List<String> getBooksByAuthor(String author);

    List<Book> getBooksByRating(float rating);

    List<BookPrice> getBookPricesByAuthor(String author);

    class BookPrice {
        private String bookName;
        private int price;

        public BookPrice(String bookName, int price) {
            this.bookName = bookName;
            this.price = price;
        }

        public String getBookName() {
            return bookName;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return String.format("Book: %s, Price: $%d", bookName, price);
        }
    }
}