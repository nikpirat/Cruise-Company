package web.servlet.filter;

import service.SecurityService;
import model.enums.Role;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter("/login")
public class LoginFilter implements Filter {
    private final SecurityService securityService = new SecurityService();
    private final UserService userService = new UserService();

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
        } else if (securityService.checkUser(login, password)) {
            Role role = userService.getByLoginAndPassword(login, password).getRole();
            int id = userService.getByLoginAndPassword(login, password).getId();
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
            req.getRequestDispatcher("index.jsp").forward(req, res);
        }

    }

    @Override
    public void destroy() {
    }
}