package com.example.demo.entity;

/**
 * @author yef
 * @date 2019/8/7
 */
public class Person {
    private String personId;
    private String personName;
    private String roleId;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId='" + personId + '\'' +
                ", personName='" + personName + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
