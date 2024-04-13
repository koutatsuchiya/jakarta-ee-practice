package com.jeeproj.company.employee.service.cache;

import com.jeeproj.company.base.cache.BaseCache;
import com.jeeproj.company.employee.entity.Employee;

import javax.ejb.Singleton;
import java.util.concurrent.TimeUnit;

@Singleton
public class EmployeeCache extends BaseCache<Employee> {
    public static final long CACHE_SIZE = 100;
    public static final long EXPIRED_TIME = 30;
    public static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
//    public static final TimeUnit TIME_UNIT = TimeUnit.DAYS;

    public static final String employeesKey = "Employees";

    public EmployeeCache() { super(CACHE_SIZE, EXPIRED_TIME, TIME_UNIT); }
}
