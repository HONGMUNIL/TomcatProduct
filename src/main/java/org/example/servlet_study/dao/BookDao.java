package org.example.servlet_study.dao;

import org.example.servlet_study.config.DBConnectionMgr;
import org.example.servlet_study.entity.Author;
import org.example.servlet_study.entity.Book;
import org.example.servlet_study.entity.BookCategory;
import org.example.servlet_study.entity.Publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class BookDao {
    private DBConnectionMgr mgr;
    private static BookDao bookDao;

    public BookDao() {
        mgr = DBConnectionMgr.getInstance();

    }


    public static BookDao getInstance() {
        if (bookDao == null) {
            bookDao = new BookDao();
        }
        return bookDao;
    }


    public Optional<Author> saveAuthor(Author author){
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = mgr.getConnection();
            String sql = """
                    insert into author values(default, ?)
                    """;
            ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, author.getAuthorName());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                author.setAuthorId(id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(author);
    }

    public Optional<Publisher> savePublisher(Publisher publisher){
        return Optional.ofNullable(publisher);
    }

    public Optional<BookCategory> saveBookCategory(BookCategory bookCategory){
        return Optional.ofNullable(bookCategory);
    }

    public Optional<Book> saveBook(Book book){
        return Optional.ofNullable(book);
    }



}
