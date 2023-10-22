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
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public void save (Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, surname, birthyear) VALUES (?, ?, ?)", person.getName(),
                person.getSurName(), person.getBirthYear());
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void update (int id, Person updatePerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, surname=?, birthyear=? WHERE id=?", updatePerson.getName(),
                updatePerson.getSurName(), updatePerson.getBirthYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public Optional<Person> getPersonByFullName(String name, String surname) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE name=? AND surname=?", new Object[]{name, surname},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }
}
