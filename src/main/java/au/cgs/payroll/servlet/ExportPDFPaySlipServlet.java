package au.cgs.payroll.servlet;

import au.cgs.payroll.ExportPDF;
import au.cgs.payroll.dao.PaySlipDAO;
import au.cgs.payroll.entity.PaySlip;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * This servlet class accesses the payslip database table and exports the contained information from the mySQL database management system
 * to a PDF file
 */
public class ExportPDFPaySlipServlet extends HttpServlet {
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

        try {
            new ExportPDF().exportPaySlip(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (session.getAttribute("admin") == "admin") {
            request.getRequestDispatcher("/show_payslip").forward(request, response);
        } else {
            int employeeId = (Integer) session.getAttribute("employeeId");
            List<PaySlip> payslip = new PaySlipDAO().selectPaySlipByEmployeeId(employeeId);
            request.setAttribute("payslipList", payslip);
            request.getRequestDispatcher("/only_payslip.jsp").forward(request, response);
        }

    }

    public void destroy() {

    }
}