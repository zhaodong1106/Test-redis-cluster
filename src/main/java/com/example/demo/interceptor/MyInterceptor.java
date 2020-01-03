package com.example.demo.interceptor;

import com.example.demo.base.ResponseMessage;
import com.example.demo.base.Status;
import com.example.demo.service.PersonService;
import com.example.demo.util.BeanUtils;
import com.example.demo.util.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.Manager;
import org.apache.catalina.Session;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.session.ManagerBase;
import org.apache.catalina.session.StandardManager;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author yef
 * @date 2019/8/7
 */
public class MyInterceptor implements HandlerInterceptor{
    private static final String PERSON_CREATE_URL="/person/create";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        PersonService personService = BeanUtils.getBean(PersonService.class);
        ObjectMapper objectMapper = BeanUtils.getBean(ObjectMapper.class);
        personService.selectAll().stream().forEach(System.out::println);
//        request.get
        if(Arrays.stream(urls).anyMatch(uri::equals)){
            return true;
        }else {
//            ManagerBase managerBase = BeanUtils.getBean(ManagerBase.class);
//            Session session = managerBase.findSession("token");
            String accessToken = WebUtils.findParameterValue(request,"accessToken");
            String userId = JWTUtil.decode(accessToken);
            System.out.println(userId);
            if (userId==null){
                response.setContentType("text/JavaScript; charset=utf-8");
                response.getWriter().write(objectMapper.writeValueAsString(ResponseMessage.ofStatus(Status.ACCESS_DENY)));
                return false;
            }
            return true;
        }
    }
    private static final String[] urls= new String[]{"/person/login","/person/testRedisCluster","/person/testCouponLua","/person/testCouponLuaInsert"};


}
