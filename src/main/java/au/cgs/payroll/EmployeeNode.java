package au.cgs.payroll;

import java.util.List;

public class EmployeeNode {
    private String name;
    List<EmployeeNode> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeeNode> getChildren() {
        return children;
    }

    public void setChildren(List<EmployeeNode> children) {
        this.children = children;
    }

}
