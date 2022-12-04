package pl.marcel.plan_elektronik.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.marcel.plan_elektronik.model.Deadline;

import java.util.List;

@Repository
public class DeadlineRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Deadline> getAll() {
        return jdbcTemplate.query("SELECT * FROM deadlines",
                BeanPropertyRowMapper.newInstance(Deadline.class));
    }

    public Deadline getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM deadlines WHERE " +
                "id = ?", BeanPropertyRowMapper.newInstance(Deadline.class), id);
    }

    public int save(List<Deadline> deadlines) {
        deadlines.forEach(deadline -> jdbcTemplate
                .update("INSERT INTO deadlines(subject, type, title, description, date) VALUES(?, ?, ?, ?, ?)",
                        deadline.getSubject(),
                        deadline.getType(),
                        deadline.getTitle(),
                        deadline.getDescription(),
                        deadline.getDate()
                ));

        return 1;
    }

    public int update(Deadline deadline) {
        return jdbcTemplate.update("UPDATE deadlines SET subject=?, type=? title=? description=? date=? WHERE id=?",
                deadline.getSubject(),
                deadline.getType(),
                deadline.getTitle(),
                deadline.getDescription(),
                deadline.getDate(),
                deadline.getId()
        );
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM deadlines WHERE id=?", id);
    }
}
