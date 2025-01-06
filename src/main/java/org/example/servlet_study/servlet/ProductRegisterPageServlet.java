package org.example.servlet_study.servlet;

import org.example.servlet_study.datas.DataList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/*servlet 만들고 datas만들고 product_register 만들고
 * entity 폴더만든다음 카테고리클래스 생성 후 작성
 * 후 DataList생성 후 작성
 * product_register로 돌아와서 <%List<Category> categories = DataList.getCategoryList(); %>안 작성
 * 다음 select for 안 작성
 * -------------------------------위는 구조빼고 생략
 * JSTL검색후 설치(mvn에) 안대면 옆에maven download source누르고
 * 리로드(좌측하단 external에 jstl이 떠야함
 * EL표현식 써서 select 부분 작성*/


// http://localhost:8080/servlet_study/product/register (GET)
@WebServlet("/product/register")
public class ProductRegisterPageServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        servletContext.setAttribute("serverName", "서블릿학습");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("상품으록 페이지 doGet 호출");
        HttpSession session = req.getSession();
        session.setAttribute("userName", "MoonIl");//페이지새로고쳐도 데이터는저장되어있다

        //이것까지 작성하면 스크립틀리가 다 사라짐
        req.setAttribute("categories", DataList.getCategoryList());
        //web-inf 안에있는 register.jsp파일을 열게 하기위한 코드
        req.getRequestDispatcher("/WEB-INF/product_register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        System.out.println(req.getParameter("category"));
        System.out.println(req.getParameter("productName"));
        System.out.println(req.getParameter("price"));
        System.out.println(req.getParameter("registerDate"));
        resp.setContentType("application/json");
        resp.setStatus(200);
        resp.getWriter().println("{\"name\":\"홍문일\"}");
//        resp.getWriter().println(
//                "<script>"
//                        + "alert(\"등록이 완료되었습니다.\");"
//                        + "location.href='http://localhost:8080/servlet_study_war/product/register';"
//                        + "</script>"
//        );

    }


}
