package web.servlet;

import dao.impl.UserDaoImpl;
import service.Validate;
import model.User;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private Validate validate = new Validate();
    private UserDaoImpl userDaoImpl = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
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

        String balance = req.getParameter("balance");
        if (NumberUtils.isNumber(balance) && Float.parseFloat(balance) >= 0) {
                user.setBalance(Float.parseFloat(balance));
        } else {
            req.setAttribute("balanceNotValid", true);
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
        }
        if (!validate.checkLogin(user.getLogin())) {
            userDaoImpl.create(user);
            resp.sendRedirect(getServletContext().getContextPath() + "/");
        } else {
            req.setAttribute("loginIsExist", true);
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
        }
    }
}
