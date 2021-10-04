package web.servlet;

import dao.BonusDao;
import dao.UserDao;
import dao.impl.BonusDaoImpl;
import dao.impl.UserDaoImpl;
import model.Bonus;
import model.enums.RoomType;
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
    private final BonusDao bonusDao = new BonusDaoImpl();
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
            req.setAttribute("bonuses", bonusDao.findAllWithRoomTypeNull());
            req.setAttribute("users", userDaoImpl.findAll());
            req.getRequestDispatcher("admin.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] bonuses = req.getParameterValues("bonuses");
        String roomType = req.getParameter("roomType");
        String bonusAction = req.getParameter("bonusAction");
        if (bonusAction.equals("Add Bonuses")) {
            addBonuses(bonuses, roomType);

        } else if (bonusAction.equals("Remove Bonuses")) {
            removeBonuses(bonuses);
        }
        resp.sendRedirect("/login");
    }

    private void addBonuses(String[] bonuses, String roomType) {
        System.out.println(roomType);
        for (String bon : bonuses) {
            System.out.println(bon);
            Bonus bonus = bonusDao.getById(Integer.parseInt(bon));
            System.out.println(bonus.getName());
            bonus.setRoomType(RoomType.valueOf(roomType));
            bonusDao.update(bonus);
        }
    }

    private void removeBonuses(String[] bonuses) {
        for (String bon : bonuses) {
            bonusDao.deleteById(Integer.parseInt(bon));
        }
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
