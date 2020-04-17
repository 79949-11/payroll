package au.cgs.payroll.entity;

import java.util.Date;

/**
 * This class represents a payslip
 */
public class PaySlip implements Entity{

    private int id;
    private int employeeId;
    private Date paymentDate;
    private double Salary;
    private double taxAmount;
    private double superAmount;
    private Date payPeriodStart;
    private Date payPeriodEnd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        this.Salary = salary;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getSuperAmount() {
        return superAmount;
    }

    public void setSuperAmount(double superAmount) {
        this.superAmount = superAmount;
    }

    public Date getPayPeriodStart() {
        return payPeriodStart;
    }

    public void setPayPeriodStart(Date payPeriodStart) {
        this.payPeriodStart = payPeriodStart;
    }

    public Date getPayPeriodEnd() {
        return payPeriodEnd;
    }

    public void setPayPeriodEnd(Date payPeriodEnd) {
        this.payPeriodEnd = payPeriodEnd;
    }

    @Override
    public String toString() {
        return "PaySlip{" +
                "employeeId=" + employeeId +
                ", paymentDate=" + paymentDate +
                ", Salary=" + Salary +
                ", taxAmount=" + taxAmount +
                ", superAmount=" + superAmount +
                ", payPeriodStart=" + payPeriodStart +
                ", payPeriodEnd=" + payPeriodEnd +
                '}';
    }
}
