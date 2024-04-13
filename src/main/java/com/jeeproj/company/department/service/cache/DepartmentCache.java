package com.jeeproj.company.department.service.cache;

import com.jeeproj.company.base.cache.BaseCache;
import com.jeeproj.company.department.entity.Department;

import javax.ejb.Singleton;
import java.util.concurrent.TimeUnit;

@Singleton
public class DepartmentCache extends BaseCache<Department> {
    public static final long CACHE_SIZE = 100;
    public static final long EXPIRED_TIME = 20;
    public static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    public static final String departmentsKey = "Departments";

    public DepartmentCache() { super(CACHE_SIZE, EXPIRED_TIME, TIME_UNIT); }
}
