package web.servlet.filter;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import service.Validate;
import model.enums.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter("/login")
public class LoginFilter implements Filter {
    private final Validate validate = new Validate();
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public void init(FilterConfig filterConfig){}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        req.setCharacterEncoding("UTF-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {
            Role role = (Role) session.getAttribute("role");
            forwardToPage(req, res, role);
        } else if (validate.checkUser(login, password)) {
            Role role = userDao.getByLoginAndPassword(login, password).getRole();
            int id = userDao.getByLoginAndPassword(login, password).getId();
            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("role", role);
            req.getSession().setAttribute("id", id);
            forwardToPage(req, res, role);
        } else {
            forwardToPage(req, res, Role.UNKNOWN);
        }
    }


    private void forwardToPage(HttpServletRequest req, HttpServletResponse res, Role role)
            throws ServletException, IOException {
        if (role.equals(Role.ADMIN)) {
            res.sendRedirect("/admin");
        } else if (role.equals(Role.USER)) {
            res.sendRedirect("/cabinet");
        } else {
            req.getRequestDispatcher("login.jsp").forward(req, res);
        }
    }

    @Override
    public void destroy() {
    }
}
