package web.servlet;

import dao.impl.BonusDaoImpl;
import model.Bonus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createBonus")
public class CreateBonusServlet  extends HttpServlet {
    private  BonusDaoImpl bonusDao = new BonusDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Bonus bonus = new Bonus();
        if (!req.getParameter("description").isEmpty() && req.getParameter("description") != null &&
                !req.getParameter("name").isEmpty() && req.getParameter("name") != null) {
            bonus.setDescription(req.getParameter("description"));
            bonus.setName(req.getParameter("name"));
            bonusDao.create(bonus);
            resp.sendRedirect("/admin");
        } else {
            req.setAttribute("error", "Fill bonus fields");
            req.getRequestDispatcher("admin.jsp").forward(req, resp);
        }
    }
}
