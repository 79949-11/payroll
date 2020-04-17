package au.cgs.payroll.dao;

import au.cgs.payroll.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class accesses the employee table in the mySQL database
 */
public class EmployeeDAO implements DAO<Employee> {

    /**
     * Creates a new row in employee database table from provided values
     */
    public void insert(Employee employee) {

        String sql = "insert into employee(surname, first_name, tax_number, username, password, admin, manager_id) " +
                "values(?,?,?,?,?,?,?)";

        Connection conn = null;

        try {

            conn = DatabaseAccess.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, employee.getSurname());
            pstmt.setString(2, employee.getFirstName());
            pstmt.setInt(3, employee.getTaxNumber());
            pstmt.setString(4, employee.getUsername());
            pstmt.setString(5, employee.getPassword());
            pstmt.setInt(6, employee.getAdmin());
            pstmt.setObject(7, employee.getManagerId(), java.sql.Types.INTEGER);

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
     * Deletes a row from employee database table based on provided
     * primary key
     *
     * @param id primary key
     */
    public void delete(int id) {
        Connection conn = null;

        String sql = "delete from employee where id=?";

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
     * Edits a row in the employee database table based on provided values
     */
    public void edit(Employee employee) {

        Connection conn = null;

        String sql = "update employee set surname=?, first_name=?, tax_number=?, manager_id=? where id=?";

        try {

            conn = DatabaseAccess.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, employee.getSurname());
            pstmt.setString(2, employee.getFirstName());
            pstmt.setInt(3, employee.getTaxNumber());
            pstmt.setObject(4, employee.getManagerId(), java.sql.Types.INTEGER);
            pstmt.setInt(5, employee.getId());

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
     * Returns the values of all rows of employee database table
     *
     * @return list of employees
     */
    public List<Employee> selectAll() {

        List<Employee> employees = new ArrayList<>();
        ResultSet rs;

        String sql = "select * from employee";

        try {
            Connection conn = DatabaseAccess.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                Employee employee = new Employee();
                employee.setId(rs.getInt(1));
                employee.setSurname(rs.getString(2));
                employee.setFirstName(rs.getString(3));
                employee.setTaxNumber(rs.getInt(4));
                employee.setUsername(rs.getString(5));
                employee.setPassword(rs.getString(6));
                employee.setAdmin(rs.getInt(7));
                int managerId = rs.getInt(8);
                if (!rs.wasNull()) {
                    employee.setManagerId(managerId);
                }

                employees.add(employee);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return employees;

    }

    /**
     * Checks if the row exists and
     * returns one row from employee database table based on provided value
     *
     * @param id primary key
     * @return employee object
     */
    public Employee findByID(int id) {

        ResultSet rs;

        String sql = "select * from employee where id=?";

        try {
            Connection conn = DatabaseAccess.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {

                Employee employee = new Employee();
                employee.setId(rs.getInt(1));
                employee.setSurname(rs.getString(2));
                employee.setFirstName(rs.getString(3));
                employee.setTaxNumber(rs.getInt(4));

                return employee;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Checks if the employee username and password exist and if user is
     * an admin get employee object, if it exists
     *
     * @param username employee username
     * @param password employee password
     * @return employee object
     */
    public Employee findUser(String username, String password) {
        ResultSet rs;

        String sql = "select * from employee where username=? and password=?";
        try {
            Connection conn = DatabaseAccess.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            rs = pstmt.executeQuery();

            if (rs.next()) {

                Employee employee = new Employee();
                employee.setId(rs.getInt(1));
                employee.setUsername(rs.getString(5));
                employee.setPassword(rs.getString(6));
                employee.setAdmin(rs.getInt(7));

                return employee;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}