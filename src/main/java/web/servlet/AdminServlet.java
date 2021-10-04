package web.servlet;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private final UserDao userDaoImpl = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = String.valueOf(req.getSession().getAttribute("role"));

        if (role.equals("ADMIN")) {
            int recordsPerPage = 3;
            int currentPage = 1;
            String recordsPerPageString = req.getParameter("recordsPerPage");
            if (recordsPerPageString != null && !recordsPerPageString.isEmpty()) {
                recordsPerPage = Integer.parseInt(req.getParameter("recordsPerPage"));
                currentPage = Integer.parseInt(req.getParameter("currentPage"));
            }
            List<User> users = userDaoImpl.findUsersUsingLimitAndOffset(currentPage, recordsPerPage);
            req.setAttribute("usersPag", users);
            req.setAttribute("noOfPages", countNumberOfPage(recordsPerPage));
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("recordsPerPage", recordsPerPage);
            req.setAttribute("users", userDaoImpl.findAll());
            req.getRequestDispatcher("admin.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/login");
    }

    private int countNumberOfPage(int recordsPerPage){
        int rows = userDaoImpl.getNumberOfRows();
        int nOfPages = rows / recordsPerPage;
        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }
        return nOfPages;
    }
}
