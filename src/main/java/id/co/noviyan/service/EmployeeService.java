package id.co.noviyan.service;

import id.co.noviyan.model.Employee;
import id.co.noviyan.model.User;
import id.co.noviyan.model.dto.request.EmployeeRequest;
import id.co.noviyan.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class EmployeeService {

    EmployeeRepository employeeRepository;
    ModelMapper modelMapper;

    @Inject
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public List<Employee> getAll() {
        return employeeRepository.listAll();
    }

    public Employee getById(Long id) {
        return employeeRepository.findByIdOptional(id).orElseThrow(
                () -> new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                        .entity("Employee not found")
                        .build())
        );
    }

    @Transactional
    public Employee create(EmployeeRequest employeeRequest) {
       Employee employee = modelMapper.map(employeeRequest, Employee.class);
       User user = modelMapper.map(employeeRequest, User.class);
       user.setEmployee(employee);
       employee.setUser(user);
        employeeRepository.persist(employee);
//        System.out.println(employee);
        return employee;
    }

    @Transactional
    public Employee update(Long id, Employee employee) {
        Employee entity = getById(id);
        if (entity != null) {
            entity.setId(id);
            entity = employee;
        }
        return entity;
    }

    @Transactional
    public Employee delete(Long id) {
        Employee entity = getById(id);
        if (entity != null) {
            employeeRepository.deleteById(id);
        }
        return entity;
    }

}
