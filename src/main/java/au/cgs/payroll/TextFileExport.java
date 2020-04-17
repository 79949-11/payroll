package au.cgs.payroll;

import au.cgs.payroll.entity.Employee;
import au.cgs.payroll.entity.PaySlip;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class TextFileExport {

    public void exportPaySlip(List<PaySlip> paySlipList) {
        try {
            File file = new File("C:/IB/CS IA/Payslip.txt");
            if (file.exists()) {
                file.delete();
            }
            RandomAccessFile add = new RandomAccessFile("C:/IB/CS IA/Payslip.txt", "rw");
            add.seek(add.length());
            for (PaySlip payslip : paySlipList) {
                add.writeBytes(payslip + "\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void exportEmployee(List<Employee> employeeList) {
        try {
            File file = new File("C:/IB/CS IA/Employee.txt");
            if (file.exists()) {
                file.delete();
            }
            RandomAccessFile add = new RandomAccessFile("C:/IB/CS IA/Employee.txt", "rw");
            add.seek(add.length());
            for (Employee employees : employeeList) {
                add.writeBytes(employees + "\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
