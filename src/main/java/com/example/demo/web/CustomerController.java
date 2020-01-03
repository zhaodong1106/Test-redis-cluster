package com.example.demo.web;

import com.example.demo.api.CustomerDto;
import com.example.demo.api.GroupA;
import com.example.demo.api.GroupB;
import com.example.demo.base.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Set;

/**
 * @author zhaodong
 * @date 2019/8/30
 */
@RestController
public class CustomerController {
    @Autowired
    private Validator validator;
    @PostMapping("/insert")
    public ResponseMessage insert(@Validated({GroupA.class})CustomerDto customerDto){
        return ResponseMessage.ofSuccess(null);
    }
    @PostMapping("/update")
    public ResponseMessage update(@Validated({GroupB.class})CustomerDto customerDto){
        return ResponseMessage.ofSuccess(null);
    }
}
