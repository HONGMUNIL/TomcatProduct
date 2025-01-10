package org.example.servlet_study.service;

public class BookService {
    private static BookService bookService;

    private BookService() {



    }

    //싱클톤 생성 이유
    public static BookService getInstance() {
        if (bookService == null) {
            bookService = new BookService();
        }
        return bookService;
    }




}
