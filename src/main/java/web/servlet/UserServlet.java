package web.servlet;


import model.*;
import model.enums.RoomType;
import service.CruiseInfoService;
import service.ShipService;
import service.UserService;

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

    private final UserService userService = new UserService();
    private final ShipService shipService = new ShipService();
    private final CruiseInfoService cruiseInfoService = new CruiseInfoService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = String.valueOf(req.getSession().getAttribute("login"));
        String password = String.valueOf(req.getSession().getAttribute("password"));
        User user = userService.getByLoginAndPassword(login, password);

        String[] orderedItems = req.getParameterValues("shipId");
        List<Ship> shipsInOrder = new ArrayList<>();
        for (String order : orderedItems) {
            shipsInOrder.add(shipService.getById(Integer.parseInt(order)));
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
            User user = userService.getByLoginAndPassword(login, password);
            List<CruiseInfo> userOrderedCruises = cruiseInfoService.getAllCruiseInfoByUserId(user.getId());

            req.setAttribute("cruisesInfo", userOrderedCruises);
            req.setAttribute("userShips", findAllUserShips(userOrderedCruises));
            req.setAttribute("ships", shipService.findAll());
            req.setAttribute("user", user);
            req.getRequestDispatcher("user.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }

    private List<Ship> findAllUserShips(List<CruiseInfo> cruiseInfos) {
        List<Ship> userShips = new ArrayList<>();
        for (CruiseInfo cruise : cruiseInfos) {
            userShips.add(shipService.getById(cruise.getShipId()));
        }
        return userShips;
    }
}
