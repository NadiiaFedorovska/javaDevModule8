package org.example;

import org.apache.log4j.Logger;
import org.example.data.OsbbCrud;
import org.example.data.Ownership;

import java.io.IOException;
import java.sql.SQLException;

public class App {
    private static final Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("The program has started");

        try (OsbbCrud crud = new OsbbCrud().initFlyway()) {
            for (Ownership ownership : crud.getOwnershipWithAutoNotAllowed()) {
                final StringBuffer sb = new StringBuffer();
                sb.append(ownership.getId())
                        .append(" : ")
                        .append(ownership.getName())
                        .append(" : ")
                        .append(ownership.getFlatNumber())
                        .append(" : ")
                        .append(ownership.getNumberOfRooms())
                        .append(" : ")
                        .append(ownership.getSquare())
                        .append(" : ")
                        .append(ownership.getStreet())
                        .append(" : ")
                        .append(ownership.getBuildingNumber())
                        .append(" : ")
                        .append(ownership.getTel())
                        .append(" : ")
                        .append(ownership.getEMail());
                System.out.println(sb);
            }
        } catch (SQLException | IOException e) {
            logger.fatal(e);
        }
    }
}
