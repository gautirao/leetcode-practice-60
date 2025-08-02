package com.goutham.datastructures.trees;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/*
* ✅ Scenario: Company Org Chart (Employee Hierarchy)
In many real-world systems—like HR software, project management tools, or access control systems—modeling an organization’s structure is a common use case for trees.

🌳 Structure:
Each Employee has:

an id

a name

a managerId (null if CEO)

list of directReports (tree structure)

🧠 Core Features:
We'll implement the following in Java:

Build the hierarchy from a flat list of employees.

Print the org chart in a tree-like structure.

Find all reports (direct + indirect) for a given employee.

Move an employee to a different manager.

Unit tests for all of the above.
* */
public class OrgChartService {
  public Employee buildHierarchy(List<Employee> employees) {

    Map<String, Employee> idToEmployee = new HashMap<>();
    Employee root = null;

    employees.forEach(e -> idToEmployee.put(e.getId(), e));
    for (Employee e : employees) {
      if (e.getManagerId() == null) {
        root = e;
      } else {
        Optional.ofNullable(idToEmployee.get(e.getManagerId()))
            .ifPresent(manager -> manager.addDirectReport(e));
      }
    }
    return root;
  }

  public void printHierarchy(Employee root){
      printRecursive(root,0);
  }

    private void printRecursive(Employee e, int level) {
    System.out.println("  ".repeat(level) + "- " + e);
    for (Employee report : e.getDirectReports()){
        printRecursive(report,level+1);
    }
    }

    public static void main(String[] args){
        OrgChartService service = new OrgChartService();
        List<Employee> employees = List.of(
                new Employee("1", "Alice", null),       // CEO
                new Employee("2", "Bob", "1"),
                new Employee("3", "Charlie", "1"),
                new Employee("4", "David", "2"),
                new Employee("5", "Eve", "2"),
                new Employee("6", "Frank", "3")
        );

        Employee ceo = service.buildHierarchy(employees);
        service.printHierarchy(ceo);
    }
}
