package at.ac.tuwien.sepm.assignment.individual.persistence.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.persistence.OwnerDao;
import java.lang.invoke.MethodHandles;
import java.sql.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
public class OwnerJdbcDao implements OwnerDao {

    private static final String TABLE_NAME = "Owner";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public OwnerJdbcDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Owner findOneById(Long id) throws DataAccessException, NotFoundException {
        LOGGER.trace("Get owner with id {}", id);
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
        List<Owner> owners = jdbcTemplate.query(sql, new Object[] { id }, this::mapRow);

        if (owners.isEmpty())
            throw new NotFoundException("Could not find owner with id %s" + id);

        return owners.get(0);
    }

    @Override
    public List<Owner> getAllOwner() throws DataAccessException {
        LOGGER.trace("Get all the owner from the database");
        final String sql = "SELECT * FROM " + TABLE_NAME;
        List<Owner> owners = jdbcTemplate.query(sql, this::mapRow);

        return owners;
    }


    private Owner mapRow(ResultSet resultSet, int i) throws SQLException {
        final Owner owner = new Owner();
        owner.setId(resultSet.getLong("id"));
        owner.setName(resultSet.getString("name"));
        owner.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
        owner.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
        return owner;
    }

    @Override
    public Owner save(Owner owner) throws DataAccessException {
        LOGGER.trace("Save owner with name {}", owner.getName());

        LocalDateTime currentTime = LocalDateTime.now();

        owner.setCreatedAt(currentTime);
        owner.setUpdatedAt(currentTime);
        final String sql = "INSERT INTO " + TABLE_NAME + " (name, created_at, updated_at)" + " VALUES (?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, owner.getName());
        stmt.setObject(2 , owner.getCreatedAt());
        stmt.setObject(3, owner.getUpdatedAt());
        return stmt;
        }, keyHolder);
        owner.setId(((Number)keyHolder.getKeys().get("id")).longValue());
        return owner;
    }

    @Override
    public void delete(Long id) throws DataAccessException {
        LOGGER.trace("Delete owner with id {}", id);

        final String sql = "DELETE FROM " + TABLE_NAME + " WHERE id=?";
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, id);
            return stmt;
        });
    }

    @Override
    public Owner update(Long id, Owner owner) throws DataAccessException, NotFoundException {
        LOGGER.trace("Update owner with id {}", owner.getId());

        owner.setUpdatedAt(LocalDateTime.now());
        final String sql = "UPDATE " + TABLE_NAME + " SET name=?,updated_at=? WHERE id=?";

        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, owner.getName());
            stmt.setObject(2 , owner.getUpdatedAt());
            stmt.setLong(3, id);
            return stmt;
        });

        return findOneById(id);
    }

    @Override
    public List<Owner> searchOwner(Owner param) throws DataAccessException, NotFoundException {
        LOGGER.trace("Search owners with params {}", param);

        final String sql= "SELECT * FROM " + TABLE_NAME + " WHERE UPPER(name) LIKE UPPER(?)";

        param.setName("%" + param.getName() + "%");

        List<Owner> owners = jdbcTemplate.query(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, param.getName());
            return stmt;
        }, this::mapRow);

        if (owners.isEmpty())
            throw new NotFoundException("Could not find owner with parmeters.");

        return owners;
    }
}
