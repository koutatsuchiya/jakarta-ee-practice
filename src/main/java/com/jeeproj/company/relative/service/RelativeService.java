package com.jeeproj.company.relative.service;

import com.jeeproj.company.base.exception.NotFoundException;
import com.jeeproj.company.employee.dao.EmployeeDAO;
import com.jeeproj.company.employee.entity.Employee;
import com.jeeproj.company.relative.dao.RelativeDAO;
import com.jeeproj.company.relative.dto.RelativeDTO;
import com.jeeproj.company.relative.entity.Relative;
import com.jeeproj.company.relative.service.mapper.RelativeMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class RelativeService {
    @Inject
    private RelativeDAO relativeDAO;

    @Inject
    private EmployeeDAO employeeDAO;

    @Inject
    private RelativeMapper relativeMapper;

    public List<RelativeDTO> findRelativesByDepartment(Long deptId) {
        return relativeDAO.findRelativesByDepartment(deptId);
    }

    public List<RelativeDTO> findRelativesByEmployeeId(Long employeeId) {
        return relativeMapper.toRelativeDTOs(relativeDAO.findRelativesByEmployeeId(employeeId));
    }

    public List<RelativeDTO> findRelativesByDepartmentStream(Long deptId) {
        return relativeDAO.findRelativesByDepartment(deptId).stream()
                .peek(relativeDTO -> relativeDTO.setEmployee((null))).toList();
    }
}
