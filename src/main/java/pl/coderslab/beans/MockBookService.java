package pl.coderslab.beans;


import org.springframework.stereotype.Component;
import pl.coderslab.beans.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MockBookService {
    private List<Book> list;
    public MockBookService() {
        list = new ArrayList<>();
        list.add(new Book(1L, "9788324631766", "Thiniking	in	Java", "Bruce	Eckel", "Helion", "programming"));
        list.add(new Book(2L, "9788324627738", "Rusz	glowa	Java.", "Sierra	Kathy,	Bates	Bert", "Helion",
                "programming"));
        list.add(new Book(3L, "9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary	Cornell", "Helion",
                "programming"));
    }

    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }

    public Book getBookById(int id) {
        long idAsLong = (long) id;
        try {
            return this.getList().stream().filter(e -> e.getId() == idAsLong).collect(Collectors.toList()).get(0);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public void deleteById(int id) {
        long idAsLong = (long) id;
        this.list = this.getList().stream().filter(e -> e.getId() != idAsLong).collect(Collectors.toList());
    }

    public Book getLastBook() {
        Book book = this.list.stream().sorted(Comparator.comparingLong(Book::getId)).collect(Collectors.toList()).get(this.list.size() - 1);
        return book;
    }

    public void add(Book book) {
        int lastIndex = Integer.parseInt(String.valueOf(this.getLastBook().getId()));
        book.setId((long) lastIndex+1);
        list.add(book);
    }

    public void replace(Book book, long id) {
        this.deleteById((int)id);
        book.setId(id);
        this.list.add(book);

    }
}
