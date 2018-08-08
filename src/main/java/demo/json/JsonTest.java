package demo.json;

import com.alibaba.fastjson.JSON;
import demo.model.Book;

import java.io.IOException;

public class JsonTest {
    public static void main(String[] args) throws IOException {

        // model array
        Book[] books = new Book[3];
        for (int i = 0; i < 3; i++) {
            Book book = new Book(i, "JSON", "Tom", "2018-1-1", 123.45, 100, null);
            books[i] = book;
        }

        // model object
        Book book = new Book(1, "JSON", "Tom", "2018-1-1", 123.45, 100, null);

        String jsonObjectString = JSON.toJSONString(book, true);

//        System.out.println(jsonObjectString);

        String jsonArrayString = JSON.toJSONString(books, true);

//        System.out.println(jsonArrayString);

//        System.out.println(JSON.parseObject(jsonObjectString, Book.class));

        System.out.println(JSON.parseArray(jsonArrayString, Book.class));

    }
}
