package au.cgs.payroll;

import au.cgs.payroll.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class OrganisationChart {

    public List<EmployeeNode> build(List<Employee> employees) {
        List<EmployeeNode> roots = buildChildren(employees, null);
        return roots;
    }

    private List<EmployeeNode> buildChildren(List<Employee> employees, Integer managerId){
        List<EmployeeNode> subordinateList = new ArrayList<>();
        for(Employee employee : employees){
            if(employee.getManagerId() == managerId){
                EmployeeNode node = new EmployeeNode();
                node.setName(employee.getFirstName() + " " + employee.getSurname());
                node.setChildren(buildChildren(employees, employee.getId()));
                subordinateList.add(node);
            }
        }
        return subordinateList;
    }
}
