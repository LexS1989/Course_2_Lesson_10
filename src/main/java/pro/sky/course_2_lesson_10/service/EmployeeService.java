package pro.sky.course_2_lesson_10.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.course_2_lesson_10.exception.EmployeeAlreadyAddedException;
import pro.sky.course_2_lesson_10.exception.EmployeeNotFoundException;
import pro.sky.course_2_lesson_10.model.Employee;

import java.util.*;

@Service
public class EmployeeService {

    private final Map<String, Employee> employees = new HashMap<>();

    private String getKey(String firstName, String lastName) {
        return firstName + lastName;
    }

    public Employee add(String firstName, String lastName, int department, double salary) {
        String checkFirstName = checkName(firstName);
        String checkLastName = checkName(lastName);
        Employee employee = new Employee(checkFirstName, checkLastName, department, salary);
        if (employees.containsKey(getKey(checkFirstName, checkLastName))) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(getKey(checkFirstName, lastName), employee);
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

    public String checkName(String name) {
        if (!StringUtils.isAlpha(name)) {
            throw new RuntimeException("400 Bad request");
        }
        String lowerCaseName = StringUtils.lowerCase(name);
        return StringUtils.capitalize(lowerCaseName);
    }


    public List<Employee> getEmployees() {
        return new ArrayList<>(employees.values());
    }
}
