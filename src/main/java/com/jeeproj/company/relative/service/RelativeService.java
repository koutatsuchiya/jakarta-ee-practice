package com.jeeproj.company.relative.service;

import com.jeeproj.company.base.exception.NotFoundException;
import com.jeeproj.company.base.exception.message.ExceptionMessage;
import com.jeeproj.company.employee.dao.EmployeeDAO;
import com.jeeproj.company.employee.entity.Employee;
import com.jeeproj.company.relative.dao.RelativeDAO;
import com.jeeproj.company.relative.dto.RelativeDTO;
import com.jeeproj.company.relative.dto.RelativeRequestDTO;
import com.jeeproj.company.relative.entity.Relative;
import com.jeeproj.company.relative.service.mapper.RelativeMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class RelativeService {
    @Inject
    private RelativeDAO relativeDAO;

    @Inject
    private EmployeeDAO employeeDAO;

    @Inject
    private RelativeMapper relativeMapper;

    public List<RelativeDTO> getAll() {
        return relativeMapper.toRelativeDTOs(relativeDAO.findAll());
    }

    public RelativeDTO getRelativeById(Long id) throws NotFoundException {
        Relative relative = relativeDAO.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.RELATIVE_NOT_FOUND));

        return relativeMapper.toRelativeDTO(relative);
    }

    public List<RelativeDTO> findRelativesByEmployeeId(Long employeeId) {
        return relativeMapper.toRelativeDTOs(relativeDAO.findRelativesByEmployeeId(employeeId));
    }

    public RelativeDTO add(RelativeRequestDTO relativeRequestDTO) throws NotFoundException {
        Employee emp = employeeDAO.findById(relativeRequestDTO.getEmployeeId())
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.EMPLOYEE_NOT_FOUND));
        Relative newRelative = relativeMapper.toRelative(relativeRequestDTO);
        newRelative.setEmployee(emp);

        return relativeMapper.toRelativeDTO(relativeDAO.add(newRelative));
    }

    public RelativeDTO update(Long id, RelativeRequestDTO relativeRequestDTO) throws NotFoundException {
        Relative relative = relativeDAO.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.RELATIVE_NOT_FOUND));
        if (relativeRequestDTO.getEmployeeId() == null) {
            relative.setEmployee(null);
        } else {
            Employee emp = employeeDAO.findById(relativeRequestDTO.getEmployeeId())
                    .orElseThrow(() -> new NotFoundException(ExceptionMessage.EMPLOYEE_NOT_FOUND));
            relative.setEmployee(emp);
        }
        relativeMapper.updateRelative(relative, relativeRequestDTO);

        return relativeMapper.toRelativeDTO(relative);
    }

    @Transactional(rollbackOn = {
            RuntimeException.class,
            NotFoundException.class
    })
    public void removeRelative(Long id) throws NotFoundException {
        Relative relative = relativeDAO.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.RELATIVE_NOT_FOUND));
        relativeDAO.delete(relative);
    }

    public List<RelativeDTO> findRelativesByDepartment(Long deptId) {
        return relativeDAO.findRelativesByDepartment(deptId);
    }
}
