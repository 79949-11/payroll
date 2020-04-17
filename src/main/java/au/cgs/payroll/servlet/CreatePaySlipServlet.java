package au.cgs.payroll.servlet;

import au.cgs.payroll.PayCalculator;
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
 * This servlet class creates a new payslip and stores it into the payslip database table
 */
public class CreatePaySlipServlet extends HttpServlet {

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

            PaySlip payslip = new PaySlip();
            payslip.setEmployeeId(Integer.parseInt(request.getParameter("employeeId")));
            payslip.setPaymentDate(Date.valueOf(request.getParameter("paymentDate")));
            payslip.setSalary(new PayCalculator().payCalculate(Double.parseDouble(request.getParameter("payReceived")), request.getParameter("cycle")));
            payslip.setTaxAmount(new PayCalculator().taxCalculate(Double.parseDouble(request.getParameter("payReceived")), request.getParameter("cycle")));
            payslip.setSuperAmount(new PayCalculator().superCalculate(Double.parseDouble(request.getParameter("payReceived"))));
            payslip.setPayPeriodStart(Date.valueOf(request.getParameter("payPeriodStart")));
            payslip.setPayPeriodEnd(Date.valueOf(request.getParameter("payPeriodEnd")));

            new PaySlipDAO().insert(payslip);
            request.getRequestDispatcher("/show_payslip").forward(request, response);
        }
    }

    public void destroy() {

    }
}