package au.cgs.payroll.servlet;

import au.cgs.payroll.dao.PaySlipDAO;
import au.cgs.payroll.entity.PaySlip;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

/**
 * This servlet class edits one of the payslip database table rows from the original values into new values inputted
 * by the user
 */
public class EditPaySlipServlet extends HttpServlet {
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

            PaySlip payslip = new PaySlip();
            payslip.setId(Integer.parseInt(request.getParameter("idForUpdate")));
            payslip.setEmployeeId(Integer.parseInt(request.getParameter("employeeId")));
            payslip.setPaymentDate(Date.valueOf(request.getParameter("paymentDate")));
            payslip.setSalary(Double.parseDouble(request.getParameter("salary")));
            payslip.setTaxAmount(Double.parseDouble(request.getParameter("taxAmount")));
            payslip.setSuperAmount(Double.parseDouble(request.getParameter("superAmount")));
            payslip.setPayPeriodStart(Date.valueOf(request.getParameter("payPeriodStart")));
            payslip.setPayPeriodEnd(Date.valueOf(request.getParameter("payPeriodEnd")));

            new PaySlipDAO().edit(payslip);
            request.getRequestDispatcher("/show_payslip").forward(request, response);


        }
    }

    public void destroy() {

    }
}

