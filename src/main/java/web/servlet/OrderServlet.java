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

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private final UserService userService = new UserService();
    private final CruiseInfoService cruiseInfoService = new CruiseInfoService();
    private final ShipService shipService = new ShipService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CruiseInfo cruiseInfo = new CruiseInfo();
        float finalPrice = 0;

        User user = userService.getById((int) req.getSession().getAttribute("id"));
        String[] orderedItems = req.getParameterValues("shipId");

        for (String orderId : orderedItems) {
            Ship ship = shipService.getById(Integer.parseInt(orderId));
            cruiseInfo.setShipId(ship.getId());
            if (req.getParameter("type" + orderId) == null ||
                    req.getParameter("type" + orderId).isEmpty()) {
                req.setAttribute("chooseRoomType", true);
                req.getRequestDispatcher("orderDetails.jsp").forward(req, resp);
            }
            RoomType roomType = RoomType.valueOf(req.getParameter("type" + orderId));

            cruiseInfo.setTotalPrice(ship.getPrice() + roomType.getPrice());
            cruiseInfo.setUserId((int) req.getSession().getAttribute("id"));
            cruiseInfo.setRoomType(roomType);
            finalPrice += ship.getPrice() + roomType.getPrice();

            if (user.getBalance() - finalPrice >= 0)
                cruiseInfo = cruiseInfoService.create(cruiseInfo);
        }
        if ((user.getBalance() - finalPrice) >= 0) {
            user.setBalance(user.getBalance() - finalPrice);
            userService.update(user);
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
            User user = userService.getByLoginAndPassword(login, password);
            req.setAttribute("user", user);
            req.getRequestDispatcher("orderDetails.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }
}
