package com.jeeproj.company.department.service.cache;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DepartmentCacheConstant {
    public static final long CACHE_SIZE = 100;
    public static final long EXPIRED_TIME = 20;
    public static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    // key
    public static final String departmentsKey = "Departments";
}
