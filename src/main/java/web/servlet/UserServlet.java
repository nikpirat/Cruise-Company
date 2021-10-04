package web.servlet;


import dao.impl.*;
import model.*;
import model.enums.RoomType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cabinet")
public class UserServlet extends HttpServlet {
    private final UserDaoImpl userDaoImpl = new UserDaoImpl();
    private final ShipDaoImpl shipDaoImpl = new ShipDaoImpl();
    private final CruiseInfoDaoImpl cruiseInfoDaoImpl = new CruiseInfoDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = String.valueOf(req.getSession().getAttribute("login"));
        String password = String.valueOf(req.getSession().getAttribute("password"));
        User user = userDaoImpl.getByLoginAndPassword(login, password);

        String[] orderedItems = req.getParameterValues("shipId");
        List<Ship> shipsInOrder = new ArrayList<>();
        for (String order : orderedItems) {
            shipsInOrder.add(shipDaoImpl.getById(Integer.parseInt(order)));
        }
        req.setAttribute("orderedCruises", shipsInOrder);
        req.setAttribute("PRESIDENT", RoomType.PRESIDENT.getPrice());
        req.setAttribute("COMFORT", RoomType.COMFORT.getPrice());
        req.setAttribute("STANDART", RoomType.STANDART.getPrice());
        req.setAttribute("user", user);
        req.getRequestDispatcher("orderDetails.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = String.valueOf(req.getSession().getAttribute("role"));
        String login = String.valueOf(req.getSession().getAttribute("login"));
        String password = String.valueOf(req.getSession().getAttribute("password"));

        if (role.equals("USER")) {
            User user = userDaoImpl.getByLoginAndPassword(login, password);
            List<CruiseInfo> userOrderedCruises = cruiseInfoDaoImpl.getAllCruiseInfoByUserId(user.getId());

            req.setAttribute("cruisesInfo", userOrderedCruises);
            req.setAttribute("userShips", findAllUserShips(userOrderedCruises));
            req.setAttribute("ships", shipDaoImpl.findAll());
            req.setAttribute("user", user);
            req.getRequestDispatcher("user.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }

    private List<Ship> findAllUserShips(List<CruiseInfo> cruiseInfos) {
        List<Ship> userShips = new ArrayList<>();
        for (CruiseInfo cruise : cruiseInfos) {
            userShips.add(shipDaoImpl.getById(cruise.getShipId()));
        }
        return userShips;
    }
}
