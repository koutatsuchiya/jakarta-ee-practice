package com.jeeproj.company.department.service.cache;

import com.jeeproj.company.base.cache.BaseCache;
import com.jeeproj.company.department.entity.Department;

import javax.ejb.Singleton;

@Singleton
public class DepartmentCache extends BaseCache<Department> {
    public DepartmentCache() {
        super(DepartmentCacheConstant.CACHE_SIZE, DepartmentCacheConstant.EXPIRED_TIME,
                DepartmentCacheConstant.TIME_UNIT);
    }
}
