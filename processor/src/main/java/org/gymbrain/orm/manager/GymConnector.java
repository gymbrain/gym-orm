package org.gymbrain.orm.manager;

import org.gymbrain.orm.utils.Resource;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GymConnector {
    private static Connection connection = null;

    private GymConnector() {
    }

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    Resource.getValue("url"),
                    Resource.getValue("user"),
                    Resource.getValue("password")

            );
            return connection;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
