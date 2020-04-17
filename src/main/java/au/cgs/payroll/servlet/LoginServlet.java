package au.cgs.payroll.servlet;

import au.cgs.payroll.dao.EmployeeDAO;
import au.cgs.payroll.dao.PaySlipDAO;
import au.cgs.payroll.entity.Employee;
import au.cgs.payroll.entity.PaySlip;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * This servlet class checks first if the username and password entered are correct. If the login details are correct,
 * it accesses the employee database table to return if the login has admin status. If the login is for an employee,
 * they will only be able to access their own payslip information. Otherwise, the admin is able to access and change everything
 */
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Employee employee = new EmployeeDAO().findUser(request.getParameter("username"), request.getParameter("password"));
        if (employee == null) {
            request.getRequestDispatcher("/failed.html").forward(request, response);
        } else {
            HttpSession session = request.getSession(true);
            session.setAttribute("username", request.getParameter("username"));
            if (employee.getAdmin() == 1) {
                session.setAttribute("admin", "admin");
                request.getRequestDispatcher("/admin.jsp").forward(request, response);
            } else {
                session.setAttribute("employeeId", employee.getId());
                List<PaySlip> payslip = new PaySlipDAO().selectPaySlipByEmployeeId(employee.getId());
                request.setAttribute("payslipList", payslip);
                request.getRequestDispatcher("/only_payslip.jsp").forward(request, response);
            }
        }
    }

    public void destroy() {

    }

}