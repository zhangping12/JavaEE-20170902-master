package demo.servlet;

import demo.model.Book;
import demo.util.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mingfei.net@gmail.com
 * @link https://github.com/thu/JavaEE-20170902/
 * @since 22:53 14 Mar 2018
 */
@WebServlet("/book")
public class BookAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("add")) {
            add(req, resp);
        }
        if (action.equals("queryAll")) {
            queryAll(req, resp);
        }
        if (action.equals("search")) {
            search(req, resp);
        }
        if (action.equals("modify")) {
            modify(req, resp);
        }
        if (action.equals("remove")) {
            remove(req, resp);
        }
    }

    private void remove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Connection connection = DB.getConnection();
        String sql = "DELETE FROM db.book WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            DB.close(null, preparedStatement, connection);

            queryAll(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void modify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String pubTime = req.getParameter("pubTime");
        double price = Double.parseDouble(req.getParameter("price"));
        int amount = Integer.parseInt(req.getParameter("amount"));

        Connection connection = DB.getConnection();
        String sql = "UPDATE db.book SET title = ?, author = ?, pubTime = ?, price = ?, amount = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, pubTime);
            preparedStatement.setDouble(4, price);
            preparedStatement.setInt(5, amount);
            preparedStatement.setInt(6, id);

            preparedStatement.executeUpdate();

            DB.close(null, preparedStatement, connection);

            queryAll(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Connection connection = DB.getConnection();
        String sql = "SELECT * FROM db.book WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Book book = new Book(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("author"),
                    resultSet.getString("pubTime"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("amount"),
                    resultSet.getString("picture")
            );
            DB.close(resultSet, preparedStatement, connection);
            req.getSession().setAttribute("book", book);
            resp.sendRedirect("edit.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String pubTime = req.getParameter("pubTime");
        double price = Double.parseDouble(req.getParameter("price"));
        int amount = Integer.parseInt(req.getParameter("amount"));

        String sql = "INSERT INTO db.book(title, author, pubTime, price, amount) VALUE (?, ?, ?, ?, ?)";
        Connection connection = DB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, pubTime);
            preparedStatement.setDouble(4, price);
            preparedStatement.setInt(5, amount);

            preparedStatement.executeLargeUpdate();

            queryAll(req, resp);

            DB.close(null, preparedStatement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void queryAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sql = "SELECT * FROM db.book ORDER BY id DESC";
        Connection connection = DB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList();
            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("pubTime"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("amount"),
                        resultSet.getString("picture")
                );
                books.add(book);
            }
            DB.close(resultSet, preparedStatement, connection);
            req.getSession().setAttribute("books", books); //
            resp.sendRedirect("home.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
