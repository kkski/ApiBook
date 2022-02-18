package pl.coderslab.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import pl.coderslab.beans.Book;
import pl.coderslab.beans.MockBookService;

import java.util.List;


@RestController
    @RequestMapping("/books")
    public class BookController {
        private MockBookService mbs = new MockBookService();
        private List<Book> books;
        @GetMapping
        public List<Book> getBooks() {
            return mbs.getList();
        }

        @GetMapping("/form")
        public String displayAForm(Model model) {
            model.addAttribute("books",books);
            return "form";


        }

        @GetMapping("/{id}")
        public Book getSpecificBook(@PathVariable("id") String id) {
            int idAsInt = Integer.parseInt(id);
            return mbs.getBookById(idAsInt);
        }

        @PostMapping
        public RedirectView createNewBook(@RequestParam String isbn,
                                  @RequestParam String title,
                                  @RequestParam String author,
                                  @RequestParam String publisher,
                                  @RequestParam String type) {
            Book bookToAdd = new Book(isbn, title, author, publisher, type);
            mbs.add(bookToAdd);
            return new RedirectView("/books");

        }

        @PutMapping
        public RedirectView changeTheBook(@RequestParam String isbn,
                                          @RequestParam String title,
                                          @RequestParam String author,
                                          @RequestParam String publisher,
                                          @RequestParam String type,
                                          @RequestParam String id) {
            Book bookToAdd = new Book(isbn, title, author, publisher, type);
            mbs.replace(bookToAdd, Long.parseLong(id));
            return new RedirectView("/books");
        }

        @DeleteMapping("/{id}")
        public RedirectView deleteSpecificBook(@PathVariable("id") String id) {
            mbs.deleteById(Integer.parseInt(id));
            return new RedirectView("/books");
        }



        @RequestMapping("/helloBook")
        public Book helloBook() {
            return new Book(1L, "9788324631766", "Thinking in Java",
                    "Bruce Eckel", "Helion", "programming");
        }

    public void setBooks(List<Book> books) {
        this.books = mbs.getList();
    }
}

