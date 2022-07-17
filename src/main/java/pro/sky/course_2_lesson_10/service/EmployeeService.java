package pro.sky.course_2_lesson_8.service;

import org.springframework.stereotype.Service;
import pro.sky.course_2_lesson_8.exception.EmployeeAlreadyAddedException;
import pro.sky.course_2_lesson_8.exception.EmployeeNotFoundException;
import pro.sky.course_2_lesson_8.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    private final Map<String, Employee> employees = new HashMap<>();

    private String getKey(String firstName, String lastName) {
        return firstName + lastName;
    }

    public Employee add(String firstName, String lastName, int department, double salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(getKey(firstName, lastName), employee);
        return employee;
    }

    public Employee remove(String firstName, String lastName) {
        if (employees.containsKey(getKey(firstName, lastName))) {
            return employees.remove(getKey(firstName, lastName));
        }
        throw new EmployeeNotFoundException();
    }

    public Employee find(String firstName, String lastName) {
        if (employees.containsKey(getKey(firstName, lastName))) {
            return employees.get(getKey(firstName, lastName));
        }
        throw new EmployeeNotFoundException();
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees.values());
    }
}
