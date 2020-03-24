package at.ac.tuwien.sepm.assignment.individual.persistence.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.persistence.HorseDao;
import java.lang.invoke.MethodHandles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
public class HorseJdbcDao implements HorseDao {

    private static final String TABLE_NAME = "Horse";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public HorseJdbcDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Horse findOneById(Long id) {
        LOGGER.trace("Get owner with id {}", id);
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
        List<Horse> horses = jdbcTemplate.query(sql, new Object[] { id }, this::mapRow);

        if (horses.isEmpty()) throw new NotFoundException("Could not find owner with id " + id);

        return horses.get(0);
    }

    @Override
    public List<Horse> getAllHorse() {
        LOGGER.trace("Get all the horses from the database");
        final String sql = "SELECT * FROM " + TABLE_NAME;
        List<Horse> horses = jdbcTemplate.query(sql, this::mapRow);

        return horses;
    }

    private Horse mapRow(ResultSet resultSet, int i) throws SQLException {
        final Horse horse = new Horse();
        horse.setId(resultSet.getLong("id"));
        horse.setName(resultSet.getString("name"));
        horse.setNotes(resultSet.getString("notes"));
        horse.setRating(resultSet.getInt("rating"));
        horse.setBirthday(resultSet.getDate("birthday").toLocalDate());
        horse.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
        horse.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
        return horse;
    }

    @Override public Horse save(Horse horse) {
        LOGGER.trace("Save horse with name {}", horse.getName());

        LocalDateTime currentTime = LocalDateTime.now();

        horse.setCreatedAt(currentTime);
        horse.setUpdatedAt(currentTime);
        final String sql = "INSERT INTO " + TABLE_NAME + " (name, race, notes, rating, birthday, created_at, updated_at, owner)" + " VALUES (?,?,?,?,?,?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, horse.getName());
            stmt.setString(2, horse.getRace().toString());
            stmt.setObject(3, horse.getNotes());
            stmt.setObject(4, horse.getRating());
            stmt.setObject(5, horse.getBirthday());
            stmt.setObject(6 , horse.getCreatedAt());
            stmt.setObject(7, horse.getUpdatedAt());
            stmt.setObject(8, horse.getOwner());
            return stmt;
        }, keyHolder);
        horse.setId(((Number)keyHolder.getKeys().get("id")).longValue());
        return horse;
    }

    @Override
    public void delete(Long id) {
        LOGGER.trace("Delete horse with id {}", id);
        final String sql = "DELETE FROM " + TABLE_NAME + " WHERE id=?";
        jdbcTemplate.update(sql, new Object[] { id });
    }
}
