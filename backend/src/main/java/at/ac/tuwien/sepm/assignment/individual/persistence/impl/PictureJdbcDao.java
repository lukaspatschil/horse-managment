package at.ac.tuwien.sepm.assignment.individual.persistence.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Picture;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import java.lang.invoke.MethodHandles;
import java.sql.*;
import java.util.List;

import at.ac.tuwien.sepm.assignment.individual.persistence.PictureDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class PictureJdbcDao implements PictureDao {

    private static final String TABLE_NAME = "Picture";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public PictureJdbcDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Picture findOneById(Long id) {
        LOGGER.trace("Get owner with id {}", id);
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
        List<Picture> picture = jdbcTemplate.query(sql, new Object[] { id }, this::mapRow);

        if (picture.isEmpty()) throw new NotFoundException("Could not find owner with id " + id);

        return picture.get(0);
    }

    private Picture mapRow(ResultSet resultSet, int i) throws SQLException {
        final Picture picture = new Picture();
        picture.setId(resultSet.getLong("id"));
        picture.setName(resultSet.getString("name"));
        picture.setFile(resultSet.getBytes("picture"));

        return picture;
    }

    @Override
    public Picture save(Picture picture) {
        LOGGER.trace("Save owner with name {}", picture.getName());

        final String sql = "INSERT INTO " + TABLE_NAME + " (name, picture)" + " VALUES (?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, picture.getName());         stmt.setObject(2 , picture.getFile());
            return stmt;
        }, keyHolder);
        picture.setId(((Number)keyHolder.getKeys().get("id")).longValue());
        return picture;
    }
}
