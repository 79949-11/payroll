package au.cgs.payroll.servlet;

import au.cgs.payroll.EmployeeNode;
import au.cgs.payroll.OrganisationChart;
import au.cgs.payroll.dao.EmployeeDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class DisplayOrganisationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute("username");
        if (username == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        if (session.getAttribute("admin") != "admin") {
            request.getRequestDispatcher("/permission.html").forward(request, response);
        } else {

            List<EmployeeNode> nodes = new OrganisationChart().build(new EmployeeDAO().selectAll());
            request.setAttribute("node", nodes.get(0));
            request.getRequestDispatcher("/manager.jsp").forward(request, response);

        }
    }

    public void destroy() {

    }
}
