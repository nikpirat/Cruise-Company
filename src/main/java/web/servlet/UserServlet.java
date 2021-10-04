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
    private UserDaoImpl userDaoImpl = new UserDaoImpl();
    private ShipDaoImpl shipDaoImpl = new ShipDaoImpl();
    private CruiseInfoDaoDaoImpl cruiseInfoDaoImpl = new CruiseInfoDaoDaoImpl();
    private ExcursionDaoImpl excursionDaoImpl = new ExcursionDaoImpl();
    private ExcursionInfoDaoImpl excursionInfoDaoImpl = new ExcursionInfoDaoImpl();
    private BonusDaoImpl bonusDao = new BonusDaoImpl();

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
        req.setAttribute("excursions", excursionDaoImpl.findAll());
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
            List<ExcursionInfo> excursionInfos = excursionInfoDaoImpl.findAllByUserId(user.getId());

            req.setAttribute("bonuses", findAllUserBonuses(userOrderedCruises));
            req.setAttribute("excursionsInfo", excursionInfoDaoImpl.findAllByUserId(user.getId()));
            req.setAttribute("userExcursions", findAllUserExcursions(excursionInfos));
            req.setAttribute("excursions", excursionDaoImpl.findAll());
            req.setAttribute("cruisesInfo", userOrderedCruises);
            req.setAttribute("userShips", findAllUserShips(userOrderedCruises));
            req.setAttribute("ships", shipDaoImpl.findAll());
            req.setAttribute("user", user);
            req.getRequestDispatcher("user.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }

    private List<Bonus> findAllUserBonuses(List<CruiseInfo> userOrderedCruises) {
        RoomType roomType = null;
        List<Bonus> bonuses = new ArrayList<>();
        for (CruiseInfo usr : userOrderedCruises) {
            if (usr.getRoomType() != roomType) {
                roomType = usr.getRoomType();
                for (Bonus bonus : bonusDao.findAllByRoomType(roomType)) {
                    bonuses.add(bonus);
                }
            }
        }
        return bonuses;
    }

    private List<Ship> findAllUserShips(List<CruiseInfo> cruiseInfos) {
        List<Ship> userShips = new ArrayList<>();
        for (CruiseInfo cruise : cruiseInfos) {
            userShips.add(shipDaoImpl.getById(cruise.getShipId()));
        }
        return userShips;
    }

    private List<Excursion> findAllUserExcursions(List<ExcursionInfo> excursionInfos) {
        List<Excursion> userExcursions = new ArrayList<>();
        for (ExcursionInfo excursion : excursionInfos) {
            userExcursions.add(excursionDaoImpl.getById(excursion.getExcursionId()));
        }
        return userExcursions;
    }
}
