package com.jeeproj.company.base.message;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppMessage {
    // Business
    public static final String EMPLOYEE_NOT_FOUND = "Employee not found";
    public static final String DEPARTMENT_NOT_FOUND = "Department not found";
    public static final String DEPARTMENT_EXIST = "Department is exist";
    public static final String DEPARTMENTS_EXIST = "Department(s) already exist";
    public static final String PROJECT_NOT_FOUND = "Project not found";
    public static final String ASSIGNMENT_NOT_FOUND = "Assignment not found";
    public static final String ASSIGNMENT_EXIST = "Assignment is exist";
    public static final String RELATIVE_NOT_FOUND = "Relative not found";
    public static final String LOCATION_NOT_FOUND = "Location not found";
    public static final String LOCATION_EXIST = "Location is existed";

    // Auth
    public static final String CREATE_JWT_FAILED = "JWT creation failed";
    public static final String INVALID_TOKEN = "Invalid token";
    public static final String USER_EXIST = "This email has already been used";
    public static final String INVALID_EMAIL_OR_PASSWORD = "Email or password is not correct";
    public static final String LOGIN_SUCCESSFULLY = "Login successfully";
    public static final String PERMISSION_DENIED = "Permission denied";
}
