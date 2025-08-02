package com.goutham.datastructures.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Employee {
    private final String id;
    private final String name;
    private String managerId;
    private final List<Employee> directReports = new ArrayList<>();

    public Employee(String id, String name, String managerId) {
        this.id = id;
        this.name = name;
        this.managerId = managerId;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getManagerId() { return managerId; }
    public List<Employee> getDirectReports() { return directReports; }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public void addDirectReport(Employee e) {
        this.directReports.add(e);
    }

    @Override
    public String toString() {
        return name + " (" + id + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee that = (Employee) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

