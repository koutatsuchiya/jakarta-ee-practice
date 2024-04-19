package com.jeeproj.company.department.service;

import com.jeeproj.company.base.exception.BadRequestException;
import com.jeeproj.company.base.exception.NotFoundException;
import com.jeeproj.company.base.message.AppMessage;
import com.jeeproj.company.department.dto.DepartmentDTO;
import com.jeeproj.company.department.dto.DepartmentRequestDTO;
import com.jeeproj.company.department.dto.DepartmentRequestsDTO;
import com.jeeproj.company.department.entity.Department;
import com.jeeproj.company.department.dao.DepartmentDAO;
import com.jeeproj.company.department.service.cache.DepartmentCache;
import com.jeeproj.company.department.service.cache.DepartmentCacheConstant;
import com.jeeproj.company.department.service.mapper.DepartmentMapper;
import com.jeeproj.company.department_location.dao.DepartmentLocationDAO;
import com.jeeproj.company.employee.dao.EmployeeDAO;
import com.jeeproj.company.project.dao.ProjectDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class DepartmentService {
    @Inject
    DepartmentDAO departmentDAO;

    @Inject
    EmployeeDAO employeeDAO;

    @Inject
    ProjectDAO projectDAO;

    @Inject
    DepartmentLocationDAO departmentLocationDAO;

    @Inject
    DepartmentMapper departmentMapper;

    @Inject
    DepartmentCache departmentCache;

    public List<DepartmentDTO> getDepartments() {
        Map<Long, Department> departments = departmentCache.getCache()
                .getIfPresent(DepartmentCacheConstant.departmentsKey);
        if (departments == null) {
            departments = departmentDAO.findAll().stream().collect(Collectors.toMap(Department::getId, dept -> dept));
            departmentCache.getCache().put(DepartmentCacheConstant.departmentsKey, departments);
        }
        return departmentMapper.toDepartmentDTOs(departments.values().stream().toList());
    }

    public DepartmentDTO getDepartmentById(Long id) throws NotFoundException {
        Map<Long, Department> departments = departmentCache.getCache()
                .getIfPresent(DepartmentCacheConstant.departmentsKey);
        Department department;
        if (departments == null || departments.get(id) == null) {
            department = departmentDAO.findById(id)
                    .orElseThrow(() -> new NotFoundException(AppMessage.DEPARTMENT_NOT_FOUND));
        } else {
            department = departments.get(id);
        }
        return departmentMapper.toDepartmentDTO(department);
    }

    public DepartmentDTO add(DepartmentRequestDTO departmentRequestDTO) throws BadRequestException {
        checkDuplicatedDepartment(departmentRequestDTO.getName());
        Department department = departmentDAO.add(departmentMapper.toDepartment(departmentRequestDTO));
        modifyDepartmentsCache(department.getId(), department);

        return departmentMapper.toDepartmentDTO(department);
    }

    @Transactional(rollbackOn = {
        RuntimeException.class
    })
    public List<DepartmentDTO> addDepartments(DepartmentRequestsDTO departmentRequestsDTO) throws BadRequestException {
        List<String> deptNames = departmentRequestsDTO.getDepartments().stream()
                .map(DepartmentRequestDTO::getName).toList();
        checkDuplicatedDepartments(deptNames);
        List<Department> departments = departmentMapper.toDepartments(departmentRequestsDTO.getDepartments());
        departments.forEach(deptToAdd -> {
            departmentDAO.add(deptToAdd);
            modifyDepartmentsCache(deptToAdd.getId(), deptToAdd);
        });
        return departmentMapper.toDepartmentDTOs(departments);
    }

    public DepartmentDTO update(Long id, DepartmentRequestDTO departmentRequestDTO) throws NotFoundException {
        Department department = departmentDAO.findById(id)
                .orElseThrow(() -> new NotFoundException(AppMessage.DEPARTMENT_NOT_FOUND));
        updateDepartmentWithRequestDTO(department, departmentRequestDTO);
        modifyDepartmentsCache(id, department);

        return departmentMapper.toDepartmentDTO(department);
    }

    @Transactional(rollbackOn = {
            RuntimeException.class,
            NotFoundException.class
    })
    public void removeDepartment(Long id) throws NotFoundException {
        Department dept = departmentDAO.findById(id)
                .orElseThrow(() -> new NotFoundException(AppMessage.DEPARTMENT_NOT_FOUND));
        employeeDAO.deleteEmployeesByDepartment(id);
        projectDAO.deleteProjectsByDepartment(id);
        departmentLocationDAO.deleteLocationsByDepartment(id);
        departmentDAO.delete(dept);
        modifyDepartmentsCache(id, null);
    }

    public void updateDepartmentWithRequestDTO(Department department, DepartmentRequestDTO departmentRequestDTO) {
        if ( departmentRequestDTO == null ) {
            return;
        }
        department.setName( departmentRequestDTO.getName() );
        department.setStartDate( departmentRequestDTO.getStartDate() );
        department.setStatus( departmentRequestDTO.getStatus() );
    }

    private void checkDuplicatedDepartment(String deptName) throws BadRequestException {
        Optional<Department> department = departmentDAO.findDepartmentByName(deptName);
        if (department.isPresent()) {
            throw new BadRequestException(AppMessage.DEPARTMENT_EXIST);
        }
    }

    private void checkDuplicatedDepartments(List<String> deptNames) throws BadRequestException {
        List<Department> departments = departmentDAO.findAll();
        Map<String, Department> departmentsMap = new HashMap<>();
        departments.forEach(department -> departmentsMap.put(department.getName(), department));

        if (deptNames.stream().anyMatch(deptName -> departmentsMap.get(deptName) != null)) {
            throw new BadRequestException(AppMessage.DEPARTMENTS_EXIST);
        }
    }

    /**
     * Modify the departments cache.
     * <p>
     * Add new dept to cache if it didn't exist.
     * Update an existed dept in cache.
     * If the parameter dept is {@code null}, then delete a dept with that id from cache.
     * </p>
     * @param id
     * @param dept
     */
    private void modifyDepartmentsCache(Long id, Department dept) {
        Map<Long, Department> departments = departmentCache.getCache()
                .getIfPresent(DepartmentCacheConstant.departmentsKey);
        if (departments != null) {
            if (dept != null) {
                departments.put(id, dept);
            } else {
                departments.remove(id);
            }
        }
    }
}
