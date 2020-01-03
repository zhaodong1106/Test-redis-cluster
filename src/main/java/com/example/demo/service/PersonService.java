package com.example.demo.service;

import com.example.demo.vo.PersonVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yef
 * @date 2019/8/7
 */
@Service
public class PersonService {
    public List<PersonVo> selectAll(){
        return new ArrayList<PersonVo>(){{
            add(new PersonVo("1","111","1"));
            add(new PersonVo("2","222","2"));
            add(new PersonVo("3","333","3"));
        }};
    }
}
