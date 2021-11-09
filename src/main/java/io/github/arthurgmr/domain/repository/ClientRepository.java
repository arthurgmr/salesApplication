package io.github.arthurgmr.domain.repository;

import io.github.arthurgmr.domain.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClientRepository {

    private static String INSERT = "insert into client (name) values (?)";
    private static String SELECT_ALL = "select * from client";
    private static String UPDATE = "update client set name = ? where id = ?";
    private static String DELETE = "delete from client where id = ?";

    // springDataJPA provider JdbcTemplate
    // that has connection config to db;
    // @Autowired inject JdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Client save(Client client) {
        jdbcTemplate.update(INSERT, new Object[]{client.getName()} );
        return client;
    }

    public Client update(Client client) {
        jdbcTemplate.update(UPDATE, new Object[] {client.getName(), client.getId()});
        return client;
    }

    public void delete(Client client) {
        delete(client.getId());
    }

    public void delete(Integer id) {
        jdbcTemplate.update(DELETE, new Object[]{id});
    }

    public List<Client> filterByName(String name) {
        return jdbcTemplate.query(SELECT_ALL.concat("where name like ?"),
                new Object[] {"%" + name + "%"},
                getClientMapper());
    }

    public List<Client> getAll() {
        return jdbcTemplate.query(SELECT_ALL, getClientMapper());
    }

    private RowMapper<Client> getClientMapper() {
        return new RowMapper<Client> () {
            @Override
            public Client mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                return new Client(id, name);
            }
        };
    }
}
