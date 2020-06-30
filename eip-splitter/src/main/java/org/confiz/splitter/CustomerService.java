package org.confiz.splitter;

import org.confiz.model.Customer;
import org.confiz.model.Department;

import java.util.List;

public class CustomerService {
    public List<Department> splitDepartments(Customer customer) {
        return customer.getDepartments();
    }
}
