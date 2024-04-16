package com.jeeproj.company.employee.service.cache;

import com.jeeproj.company.base.cache.BaseCache;
import com.jeeproj.company.employee.entity.Employee;

import javax.ejb.Singleton;
import java.util.concurrent.TimeUnit;

@Singleton
public class EmployeeCache extends BaseCache<Employee> {
    public EmployeeCache() {
        super(EmployeeCacheConstant.CACHE_SIZE, EmployeeCacheConstant.EXPIRED_TIME,
            EmployeeCacheConstant.TIME_UNIT);
    }
}
