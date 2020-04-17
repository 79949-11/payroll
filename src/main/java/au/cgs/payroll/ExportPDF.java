package au.cgs.payroll;

import au.cgs.payroll.dao.EmployeeDAO;
import au.cgs.payroll.dao.PaySlipDAO;
import au.cgs.payroll.entity.PaySlip;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;


/**
 * This class class converts payslip information from
 * database tables and displays it in a PDF file. It automatically
 * creates the PDF files
 */
public class ExportPDF {

    /**
     * Creates a PDF file in a pre-determined location on the C drive
     * and displays all the information from the payslip database table
     * in the PDF file created
     *
     * @param id primary key of payslip database table
     * @throws Exception exception is thrown
     */
    public void exportPaySlip(int id) throws Exception {

        String dest = "C:/IB/CS IA/PaySlip.pdf";
        PdfWriter writer = new PdfWriter(dest);

        PdfDocument pdf = new PdfDocument(writer);

        Document document = new Document(pdf);

        PaySlip payslip = new PaySlipDAO().findByID(id);

        EmployeeDAO employeeDAO = new EmployeeDAO();
        double totalAmount = payslip.getTaxAmount() + payslip.getSalary() + payslip.getSuperAmount();

        List list = new List();
        list.add("Period Starting: " + payslip.getPayPeriodStart() + "    Period Ending: " + payslip.getPayPeriodEnd() + "    Date Paid: " + payslip.getPaymentDate());
        list.add("Name: " + employeeDAO.findByID(payslip.getEmployeeId()).getFirstName() + " " + employeeDAO.findByID(payslip.getEmployeeId()).getSurname());
        list.add("          Pay Slip Components");
        list.add("Taxes: " + payslip.getTaxAmount());
        list.add("Superannuation Breakdown: " + payslip.getSuperAmount());
        list.add("This Pay: " + payslip.getSalary());
        list.add("Total: " + totalAmount);
        document.add(list);

        document.close();
    }
}
