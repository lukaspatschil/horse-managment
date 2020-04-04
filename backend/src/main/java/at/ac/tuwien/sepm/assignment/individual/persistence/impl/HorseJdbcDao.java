package at.ac.tuwien.sepm.assignment.individual.persistence.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.persistence.HorseDao;
import java.lang.invoke.MethodHandles;
import java.sql.*;
import java.util.List;

import at.ac.tuwien.sepm.assignment.individual.util.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
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
    public Horse findOneById(Long id) throws NotFoundException, PersistenceException {
        LOGGER.trace("Get horse with id {}", id);
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
        List<Horse> horses;

        try {
            horses = jdbcTemplate.query(sql, new Object[] { id }, this::mapRow);
        } catch (DataAccessException e) {
            LOGGER.error("Could not read horse (PersistenceException)");
            throw new PersistenceException("Could not read horse with id" + id, e);
        }

        if (horses.isEmpty()) {
            LOGGER.error("Horse not found.");
            throw new NotFoundException("Could not find owner with id " + id);
        }

        return horses.get(0);
    }

    @Override
    public List<Horse> getAllHorse() throws PersistenceException {
        LOGGER.trace("Get all the horses from the database");
        final String sql = "SELECT * FROM " + TABLE_NAME;
        List<Horse> horses;

        try {
            horses = jdbcTemplate.query(sql, this::mapRow);
        } catch (DataAccessException e) {
            LOGGER.error("Could not read horses (PersistenceException)");
            throw new PersistenceException("Could not read horses", e);
        }

        return horses;
    }

    @Override
    public List<Horse> getHorsefromOwner(Long id) throws PersistenceException {
        LOGGER.trace("Get horses from owner with if {}", id);
        final String sql = "SELECT * FROM " +TABLE_NAME + " WHERE owner=?";
        List<Horse> horses;

        try {
            horses = jdbcTemplate.query(sql, new Object[] { id }, this::mapRow);
        } catch (DataAccessException e) {
            LOGGER.error("Could not read horses (PersistenceException)");
            throw new PersistenceException("Could not read horses from owner with id " + id, e);
        }

        return horses;
    }

    private Horse mapRow(ResultSet resultSet, int i) throws SQLException {
        final Horse horse = new Horse();
        horse.setId(resultSet.getLong("id"));
        horse.setName(resultSet.getString("name"));
        horse.setNotes(resultSet.getString("notes"));
        horse.setRating(resultSet.getInt("rating"));
        horse.setBirthday(resultSet.getDate("birthday").toLocalDate());
        horse.setRace(resultSet.getString("race"));
        horse.setOwner(resultSet.getLong("owner"));
        horse.setImage(resultSet.getBytes("image"));
        horse.setType(resultSet.getString("type"));
        horse.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
        horse.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
        return horse;
    }

    @Override public Horse save(Horse horse) throws PersistenceException {
        LOGGER.trace("Save horse with name {}", horse.getName());

        LocalDateTime currentTime = LocalDateTime.now();

        horse.setCreatedAt(currentTime);
        horse.setUpdatedAt(currentTime);
        final String sql = "INSERT INTO " + TABLE_NAME + " (name, race, notes, rating, birthday, created_at, updated_at, owner, image, type)" + " VALUES (?,?,?,?,?,?,?,?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, horse.getName());
                stmt.setString(2, horse.getRace());
                stmt.setString(3, horse.getNotes());
                stmt.setInt(4, horse.getRating());
                stmt.setObject(5, horse.getBirthday());
                stmt.setObject(6 , horse.getCreatedAt());
                stmt.setObject(7, horse.getUpdatedAt());
                stmt.setLong(8, horse.getOwner());
                stmt.setObject(9, horse.getImage(), Types.BLOB);
                stmt.setString(10, horse.getType());
                return stmt;
            }, keyHolder);
        } catch (DataAccessException e) {
            LOGGER.error("Could not save horse (PersistenceException)");
            throw new PersistenceException("Could not read horses with name " + horse.getName(), e);
        }

        horse.setId(((Number)keyHolder.getKeys().get("id")).longValue());
        return horse;
    }

    @Override
    public void delete(Long id) throws PersistenceException, ValidationException {
        LOGGER.trace("Delete horse with id {}", id);
        final String sql = "DELETE FROM " + TABLE_NAME + " WHERE id=?";

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setLong(1, id);
                return stmt; });
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Could not delete horse (DataIntegrityViolationException)");
            throw new ValidationException("Could not delete horse (DataIntegrityViolationException)");
        } catch (DataAccessException e) {
            LOGGER.error("Could not delete horse (PersistenceException)");
            throw new PersistenceException("Could not delete horse with id" + id, e);
        }
    }

    @Override
    public Horse update(Long id, Horse horse) throws NotFoundException, PersistenceException {
        LOGGER.trace("Update horse with id {}", horse.getId());

        horse.setUpdatedAt(LocalDateTime.now());
        final String sql = "UPDATE " + TABLE_NAME +
            " SET name = ?, race=?,notes=?,rating=?,birthday=?,updated_at=?, owner=?,image=? WHERE id = ? ";

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, horse.getName());
                stmt.setString(2, horse.getRace());
                stmt.setObject(3, horse.getNotes(), Types.VARCHAR);
                stmt.setInt(4, horse.getRating());
                stmt.setObject(5, horse.getBirthday());
                stmt.setObject(6, horse.getUpdatedAt());
                stmt.setLong(7, horse.getOwner());
                stmt.setObject(8, horse.getImage(), Types.BLOB);
                stmt.setLong(9, id);
                return stmt;
            });
        } catch (DataAccessException e) {
            LOGGER.error("Could not update horse (PersistenceException)");
            throw new PersistenceException("Could not update horse with id " + id, e);
        }

        try {
            return findOneById(id);
        } catch (NotFoundException e) {
            LOGGER.error("Horse not found.");
            throw new NotFoundException("Could not find the horse with id " + id);
        }

    }

    @Override
    public List<Horse> searchHorse(Horse param) throws NotFoundException, PersistenceException {
        LOGGER.trace("Search horses with params {}", param);

        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE UPPER(name) LIKE ? AND UPPER(notes) like ? " +
            "AND UPPER(race) like ? AND rating like ? AND birthday >= ?";

        if (param.getName() == null)
            param.setName("");
        if (param.getNotes() == null)
            param.setNotes("");

        param.setName("%" + param.getName().toUpperCase() + "%");
        param.setNotes("%" + param.getNotes().toUpperCase() + "%");

        if (param.getRace() == null)
            param.setRace("%");
        if (param.getRace().isEmpty())
            param.setRace("%");
        param.setRace(param.getRace().toUpperCase());

        List<Horse> horses;

        try {
            horses = jdbcTemplate.query(sql, new Object[] { param.getName(), param.getNotes(), param.getRace(), (param.getRating() != null) ? param.getRating() : "%" , (param.getBirthday() != null) ? param.getBirthday() : "1900-01-01"  }, this::mapRow);
        } catch (DataAccessException e) {
            LOGGER.error("Could not read horse (PersistenceException)");
            throw new PersistenceException("Could not read the horses", e);
        }

        if (horses.isEmpty()) {
            LOGGER.error("Horse not found.");
            throw new NotFoundException("Could not find horse with given params");
        }

        return horses;
    }
}
