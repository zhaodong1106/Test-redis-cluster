package com.example.demo.vo;

/**
 * @author yef
 * @date 2019/8/7
 */
public class PersonVo {
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

    public PersonVo(String personId, String personName, String roleId) {
        this.personId = personId;
        this.personName = personName;
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "PersonVo{" +
                "personId='" + personId + '\'' +
                ", personName='" + personName + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }

    public PersonVo() {
    }
}
