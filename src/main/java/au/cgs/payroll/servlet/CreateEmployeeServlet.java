package au.cgs.payroll.servlet;

import au.cgs.payroll.dao.EmployeeDAO;
import au.cgs.payroll.entity.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This servlet class creates a new employee and saves it to the employee database table
 */
public class CreateEmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
            int adminFlag;
            if (request.getParameter("adminFlag") == null) {
                adminFlag = 0;
            } else {
                adminFlag = Integer.parseInt(request.getParameter("adminFlag"));
            }

            String managerIdString = request.getParameter("manager_Id");
            Integer managerId;

            if(managerIdString.equals("")){
                managerId = null;
            } else {
                managerId = Integer.parseInt(managerIdString);
            }

            Employee employee = new Employee();
            employee.setSurname(request.getParameter("surname"));
            employee.setFirstName(request.getParameter("first_name"));
            employee.setTaxNumber(Integer.parseInt(request.getParameter("tax_number")));
            employee.setUsername(request.getParameter("username"));
            employee.setPassword(request.getParameter("password"));
            employee.setAdmin(adminFlag);
            employee.setManagerId(managerId);


            new EmployeeDAO().insert(employee);
            request.getRequestDispatcher("/show_employee").forward(request, response);
        }
    }

    public void destroy() {

    }

}
