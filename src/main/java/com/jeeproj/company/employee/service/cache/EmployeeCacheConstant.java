package com.jeeproj.company.employee.service.cache;

import java.util.concurrent.TimeUnit;

public final class EmployeeCacheConstant {
    public static final long CACHE_SIZE = 100;
    public static final long EXPIRED_TIME = 20;
    public static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
//    public static final TimeUnit TIME_UNIT = TimeUnit.DAYS;

    public static final String employeesKey = "Employees";
}
