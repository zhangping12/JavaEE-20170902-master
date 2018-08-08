package demo.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionTest {
    public static void main(String[] args) {
        Connection connection = DB.getConnection();
        PreparedStatement preparedStatement = null;

        if (connection == null) {
            return;
        }

        try {
            connection.setAutoCommit(false);

            String sql = "DELETE FROM db.book";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

            int i = 1 / 0;

            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            DB.close(null, preparedStatement, connection);
        }
    }
}
