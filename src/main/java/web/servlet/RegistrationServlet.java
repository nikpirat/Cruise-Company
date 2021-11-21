package web.servlet;

import service.SecurityService;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private final SecurityService securityService = new SecurityService();
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        User user = new User();
        user.setLogin(req.getParameter("login"));
        user.setName(req.getParameter("name"));
        user.setPassword(req.getParameter("password"));
        user.setSurname(req.getParameter("surname"));

        if (!securityService.checkLogin(user.getLogin())) {
            userService.create(user);
            resp.sendRedirect(getServletContext().getContextPath() + "/");
        } else {
            req.setAttribute("loginIsExist", true);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
