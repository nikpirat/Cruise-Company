package web.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        final HttpSession session = req.getSession();
        session.removeAttribute("password");
        session.removeAttribute("login");
        session.removeAttribute("role");
        resp.sendRedirect(getServletContext().getContextPath() + "/");
    }
}
