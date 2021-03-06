package web.servlet;

import model.User;
import org.apache.commons.lang3.math.NumberUtils;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/topUp")
public class TopUpServlet extends HttpServlet {

    private final UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getById((int) request.getSession().getAttribute("id"));

        String balance = request.getParameter("amount");
        if (NumberUtils.isNumber(balance) && Float.parseFloat(balance) >= 0) {
                user.setBalance(user.getBalance()+Float.parseFloat(balance));
                userService.update(user);
        } else {
            request.setAttribute("balanceNotValid", true);
            request.getRequestDispatcher("user.jsp").forward(request, response);
        }

        response.sendRedirect("/cabinet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("topUp.jsp").forward(request, response);
    }
}
