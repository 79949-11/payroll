package au.cgs.payroll.dao;

import au.cgs.payroll.entity.PaySlip;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * This class accesses the payslip table in the mySQL database
 */
public class PaySlipDAO implements DAO<PaySlip>{

    /**
     * Inserts a new row into the payslip database table based on provided values
     *
     */
    public void insert(PaySlip payslip) {

        String sql = "insert into payslip(employee_id, payment_date, salary, tax_amount, super_amount, pay_period_start, pay_period_end) values(?,?,?,?,?,?,?)";
        Connection conn = null;

        try {

            conn = DatabaseAccess.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, payslip.getEmployeeId());
            pstmt.setDate(2, new java.sql.Date(payslip.getPaymentDate().getTime()));
            pstmt.setDouble(3, payslip.getSalary());
            pstmt.setDouble(4, payslip.getTaxAmount());
            pstmt.setDouble(5, payslip.getSuperAmount());
            pstmt.setDate(6, new java.sql.Date(payslip.getPayPeriodStart().getTime()));
            pstmt.setDate(7, new java.sql.Date(payslip.getPayPeriodEnd().getTime()));

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    /**
     * Deletes a row from the payslip database table based on
     * user provided values
     *
     * @param id primary key
     */
    public void delete(int id) {

        String sql = "delete from payslip where id=?";
        Connection conn = null;

        try {

            conn = DatabaseAccess.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);


            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Finds and edits a row from the payslip database table based on
     * user provided values
     *
     */
    public void edit(PaySlip payslip) {

        Connection conn = null;
        String sql = "update payslip set employee_id=?, payment_date=?, salary=?, tax_amount=?, super_amount=?, pay_period_start=?, pay_period_end=? where id=?";

        try {

            conn = DatabaseAccess.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, payslip.getEmployeeId());
            pstmt.setDate(2, new java.sql.Date(payslip.getPaymentDate().getTime()));
            pstmt.setDouble(3, payslip.getSalary());
            pstmt.setDouble(4, payslip.getTaxAmount());
            pstmt.setDouble(5, payslip.getSuperAmount());
            pstmt.setDate(6, new java.sql.Date(payslip.getPayPeriodStart().getTime()));
            pstmt.setDate(7, new java.sql.Date(payslip.getPayPeriodEnd().getTime()));
            pstmt.setInt(8, payslip.getId());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Returns all the values stored in the payslip database table
     *
     * @return a list of payslip row values
     */
    public List<PaySlip> selectAll() {

        List<PaySlip> payslipList = new ArrayList<>();
        ResultSet rs;

        String sql = "select * from payslip";

        try {
            Connection conn = DatabaseAccess.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                PaySlip payslip = new PaySlip();
                payslip.setId(rs.getInt(1));
                payslip.setEmployeeId(rs.getInt(2));
                payslip.setPaymentDate(rs.getDate(3));
                payslip.setSalary(rs.getDouble(4));
                payslip.setTaxAmount(rs.getDouble(5));
                payslip.setSuperAmount(rs.getDouble(6));
                payslip.setPayPeriodStart(rs.getDate(7));
                payslip.setPayPeriodEnd(rs.getDate(8));

                payslipList.add(payslip);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return payslipList;

    }

    /**
     * Returns one row from the payslip database table based on
     * the primary key inserted by users
     *
     * @param id primary key
     * @return a payslip object which contains all the values of one row
     */
    public PaySlip findByID(int id) {

        ResultSet rs;

        String sql = "select * from payslip where id=?";

        try {
            Connection conn = DatabaseAccess.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {

                PaySlip payslip = new PaySlip();
                payslip.setId(rs.getInt(1));
                payslip.setEmployeeId(rs.getInt(2));
                payslip.setPaymentDate(rs.getDate(3));
                payslip.setSalary(rs.getDouble(4));
                payslip.setTaxAmount(rs.getDouble(5));
                payslip.setSuperAmount(rs.getDouble(6));
                payslip.setPayPeriodStart(rs.getDate(7));
                payslip.setPayPeriodEnd(rs.getDate(8));

                return payslip;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Returns all the rows from the payslip database table which
     * are pertinent to the specific employee id
     *
     * @param employeeId id of employee (foreign key)
     * @return a list of payslip information that is relevant to the employee
     */
    public List<PaySlip> selectPaySlipByEmployeeId(int employeeId) {

        List<PaySlip> payslipList = new ArrayList<>();
        ResultSet rs;

        String sql = "select * from payslip where employee_id=?";

        try {
            Connection conn = DatabaseAccess.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, employeeId);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                PaySlip payslip = new PaySlip();
                payslip.setId(rs.getInt(1));
                payslip.setEmployeeId(rs.getInt(2));
                payslip.setPaymentDate(rs.getDate(3));
                payslip.setSalary(rs.getDouble(4));
                payslip.setTaxAmount(rs.getDouble(5));
                payslip.setSuperAmount(rs.getDouble(6));
                payslip.setPayPeriodStart(rs.getDate(7));
                payslip.setPayPeriodEnd(rs.getDate(8));

                payslipList.add(payslip);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return payslipList;

    }
}
