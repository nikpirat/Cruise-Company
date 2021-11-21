package web.servlet;

import dao.CruiseInfoDao;
import dao.UserDao;
import dao.impl.CruiseInfoDaoImpl;
import dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manageCruises")
public class ManageCruisesServlet extends HttpServlet {
    private CruiseInfoDao cruiseInfoDao = new CruiseInfoDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("userShips", cruiseInfoDao.getAllCruiseInfoByUserId(Integer.parseInt(request.getParameter("id"))));


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
