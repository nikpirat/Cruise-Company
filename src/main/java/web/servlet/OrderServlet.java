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

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private UserDaoImpl userDaoImpl = new UserDaoImpl();
    private CruiseInfoDaoDaoImpl cruiseInfoDaoImpl = new CruiseInfoDaoDaoImpl();
    private ShipDaoImpl shipDaoImpl = new ShipDaoImpl();
    private ExcursionDaoImpl excursionDaoImpl = new ExcursionDaoImpl();
    private ExcursionInfoDaoImpl excursionInfoDaoImpl = new ExcursionInfoDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CruiseInfo cruiseInfo = new CruiseInfo();
        List<Excursion> userExcursions = new ArrayList<>();
        ExcursionInfo excursionInfo = new ExcursionInfo();
        float finalPrice = 0;

        User user = userDaoImpl.getById( (int) req.getSession().getAttribute("id"));
        String[] orderedItems = req.getParameterValues("shipId");

        if (req.getParameterValues("excursions") != null) {
            String[] excursions = req.getParameterValues("excursions");
            userExcursions = initializeAllUserExcursions(excursions);
        }
        for (String orderId : orderedItems) {
            Ship ship = shipDaoImpl.getById(Integer.parseInt(orderId));
            cruiseInfo.setShipId(ship.getId());
            if (req.getParameter("type" + orderId) == null ||
                    req.getParameter("type" + orderId).toString().isEmpty()) {
                req.setAttribute("chooseRoomType", true);
                req.getRequestDispatcher("orderDetails.jsp").forward(req, resp);
            }
            RoomType roomType = RoomType.valueOf(req.getParameter("type" + orderId));

            cruiseInfo.setTotalPrice(ship.getPrice() + roomType.getPrice());
            cruiseInfo.setUserId((int) req.getSession().getAttribute("id"));
            cruiseInfo.setRoomType(roomType);
            finalPrice += ship.getPrice() + roomType.getPrice();

            if (user.getBalance() - finalPrice >= 0) {
                cruiseInfo = cruiseInfoDaoImpl.create(cruiseInfo);
                    for (Excursion ex : userExcursions) {
                        if (Integer.parseInt(orderId) == ex.getShipId()) {
                            excursionInfo.setExcursionId(ex.getId());
                            excursionInfo.setUserId(user.getId());
                            excursionInfo.setCruiseInfoId(cruiseInfo.getId());
                            excursionInfoDaoImpl.create(excursionInfo);
                            finalPrice += excursionDaoImpl.getById(ex.getId()).getPrice();
                        }
                    }
                }
            }
            if ((user.getBalance() - finalPrice) >= 0) {
                user.setBalance(user.getBalance() - finalPrice);
                userDaoImpl.update(user);
            } else {
                req.setAttribute("notEnoughMoney", true);
                req.setAttribute("orderPrice", finalPrice);
                req.setAttribute("user", user);
                req.getRequestDispatcher("orderDetails.jsp").forward(req, resp);
            }
        resp.sendRedirect("/cabinet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = String.valueOf(req.getSession().getAttribute("role"));
        String login = String.valueOf(req.getSession().getAttribute("login"));
        String password = String.valueOf(req.getSession().getAttribute("password"));
        if (role.equals("USER")) {
            User user = userDaoImpl.getByLoginAndPassword(login, password);
            req.setAttribute("user", user);
            req.getRequestDispatcher("orderDetails.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }

    private List<Excursion> initializeAllUserExcursions(String[] excursions) {
        List<Excursion> userExcursions = new ArrayList<>();
        for (String excursion : excursions) {
            userExcursions.add(excursionDaoImpl.getById(Integer.parseInt(excursion)));
        }
        return userExcursions;
    }
}
