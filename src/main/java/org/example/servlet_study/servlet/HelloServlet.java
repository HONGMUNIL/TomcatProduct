package org.example.servlet_study.servlet;
//주소에 hello?name=aaa&age=32 <<?는 파라미터 전달
//webapp에 jsp 파일 만들고 html사용하듯이 사용
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

   //  System.out.println(req.getParameter("name"));//주소에 hello?name=aaa&age=32 <<?는 파라미터 전달하면
     //   resp.getWriter().println("<html>"+ "<head>"+"<title>Hello Servlet</title>"+"</head>"+"<body>"+"<h1>hello servlet</h1>"+"</body>"+"</html>");//창에 출력됨
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     req.getRequestDispatcher("/WEB-INF/hello.jsp").forward(req, resp);

    }
}

