package org.example.servlet_study.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//servlet 만들고 datas만들고 product_register 만들고
// http://localhost:8080/servlet_study/product/register (GET)
@WebServlet("/product/register")
public class ProductRegisterPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //web-inf 안에있는 register.jsp파일을 열게 하기위한 코드
        req.getRequestDispatcher("/WEB-INF/product_register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
