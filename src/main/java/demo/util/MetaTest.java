package demo.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class MetaTest {
    public static void main(String[] args) throws SQLException {
        Connection connection = DB.getConnection();
        DatabaseMetaData databaseMetaData = connection.getMetaData();

        System.out.println(databaseMetaData.getDatabaseMajorVersion());
        System.out.println(databaseMetaData.getDatabaseMinorVersion());
        System.out.println(databaseMetaData.getDatabaseProductName());
        System.out.println(databaseMetaData.getDatabaseProductVersion());

        DB.close(null, null, connection);
    }
}
