package org.example.data;

import org.apache.log4j.Logger;
import org.flywaydb.core.Flyway;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static org.example.Config.*;

public class OsbbCrud implements Closeable {
    private static final Logger logger = Logger.getLogger(OsbbCrud.class);
    private Connection conn = null;

    private static final String sqlOwnershipWithAutoNotAllowedQuery = "SELECT \n" +
            "t.id, \n" +
            "t1.flat_number, t1.number_of_rooms, t1.square, \n" +
            "t2.street, t2.building_number,\n" +
            "t3.name, t3.tel, t3.e_mail, t3.entry_by_car,\n" +
            "t4.role\n" +
            "FROM crud_fedorovska.ownership t\n" +
            "LEFT JOIN crud_fedorovska.flats t1 ON t1.id = t.flats_id\n" +
            "LEFT JOIN crud_fedorovska.buildings t2 ON t2.id = t1.building_id\n" +
            "LEFT JOIN crud_fedorovska.residents_rc t3 ON t3.id = t.residents_RC_id\n" +
            "LEFT JOIN crud_fedorovska.member_osbb t4 ON t4.id = t3.member_osbb_id\n" +
            "LEFT JOIN (\n" +
            "\tSELECT residents_RC_id, COUNT(id) AS flat_count\n" +
            "\tFROM crud_fedorovska.ownership\n" +
            "\tGROUP BY residents_RC_id\n" +
            ") AS t5 ON t.residents_RC_id = t5.residents_RC_id\n" +
            "WHERE t3.entry_by_car = '-' AND t5.flat_count < 2;";

    private void migrationFlyway() {
        logger.debug("Flyway migration execute");

        Flyway.configure()
                .dataSource(jdbcUrl, userName, password)
                .locations("classpath:flyway/scripts")
                .load()
                .migrate();
    }

    public OsbbCrud initFlyway() throws SQLException {
        logger.info("Crud has initialized");
        migrationFlyway();

        conn = DriverManager.getConnection(jdbcUrl, userName, password);
        return this;
    }

    @Override
    public void close() throws IOException {
        try {
            conn.close();
            conn = null;
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    public List<Ownership> getOwnershipWithAutoNotAllowed() throws SQLException {
        logger.trace("Call getting ownership with auto not allowed method");

        final List<Ownership> result = new LinkedList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(sqlOwnershipWithAutoNotAllowedQuery)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                result.add(
                        new Ownership()
                                .setId(resultSet.getInt("id"))
                                .setName(resultSet.getString("name"))
                                .setFlatNumber(resultSet.getInt("flat_number"))
                                .setNumberOfRooms(resultSet.getInt("number_of_rooms"))
                                .setSquare(resultSet.getInt("square"))
                                .setStreet(resultSet.getString("street"))
                                .setBuildingNumber(resultSet.getInt("building_number"))
                                .setTel(resultSet.getInt("tel"))
                                .setEMail(resultSet.getString("e_mail")));
        }
        return result;
    }
}
