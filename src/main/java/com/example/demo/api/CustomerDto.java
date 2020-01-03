package com.example.demo.api;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author zhaodong
 * @date 2019/8/30
 */

public class CustomerDto implements Serializable {
    private static final long serialVersionUID = 5904149347646592436L;
    @NotNull(groups = {GroupB.class})
    private Long CustomerId;
    @NotBlank(groups = {GroupA.class})
    private String customerName;
    @NotBlank(groups = {GroupA.class})
    private String customerPassword;

    public Long getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(Long customerId) {
        CustomerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }
}
