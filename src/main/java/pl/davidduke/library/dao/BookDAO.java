package pl.davidduke.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pl.davidduke.library.models.Book;
import pl.davidduke.library.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, publication_year) VALUES (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getPublicationYear());
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void update(int id, Book updateBook) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, publication_year=? WHERE id=?",
                updateBook.getTitle(), updateBook.getAuthor(), updateBook.getPublicationYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }

    public Optional<Person> getBookOwner(int id){
       return jdbcTemplate.query("SELECT p.id, p.name, p.surname, p.birthyear FROM Book b " +
                       "JOIN Person p on b.person_id=p.id WHERE b.id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void assign(int id, Person person) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", person.getId(), id);
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id=null WHERE id=?", id);
    }
}
