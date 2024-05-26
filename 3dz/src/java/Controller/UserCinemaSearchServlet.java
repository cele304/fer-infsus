package Controller;

import Model.Cinema;
import Model.DataBase;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserCinemaSearch")
public class UserCinemaSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cinemaName = request.getParameter("cinemaName");
        DataBase dt = new DataBase();
        List<Cinema> cines;

        if (cinemaName != null && !cinemaName.isEmpty()) {
            cines = dt.getCinemasByName(cinemaName);
        } else {
            cines = dt.getTodosCines();
        }

        request.setAttribute("cines", cines);
        request.getRequestDispatcher("/user_viewCities.jsp").forward(request, response);
    }
}
