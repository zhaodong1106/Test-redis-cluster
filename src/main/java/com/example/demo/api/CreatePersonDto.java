package com.example.demo.api;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author yef
 * @date 2019/8/7
 */
public class CreatePersonDto implements Serializable {
    private static final long serialVersionUID = -1024025432406161854L;
    @NotNull(groups = {GroupB.class})
    private String personId;
    @NotBlank(groups = {GroupA.class})
    @Length(min = 4,max = 8)
    private String personName;
    @NotNull
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
}
