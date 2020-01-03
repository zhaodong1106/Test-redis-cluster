package com.example.demo.web;

import com.example.demo.annotation.LoggerTest;
import com.example.demo.api.CreatePersonDto;
import com.example.demo.base.ResponseMessage;
import com.example.demo.entity.Person;
import com.example.demo.util.JWTUtil;
import com.example.demo.util.UploadUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yef
 * @date 2019/8/7
 */
@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UploadUtils uploadUtils;
    @GetMapping("/create")
    public ResponseMessage create(HttpServletRequest request,@Valid CreatePersonDto createPersonDto){
        String uuid = WebUtils.findParameterValue(request, "JESESIONID");
        System.out.println(uuid);
        Person person = modelMapper.map(createPersonDto, Person.class);
        return ResponseMessage.ofSuccess(person);
    }
    @PostMapping(value = "/upload",produces = {"application/json;charset=UTF-8"} )
    public Object upload(MultipartFile multipartFile){
        String targetFilePath = uploadUtils.upload(multipartFile);
        return targetFilePath;
    }

    @GetMapping(value = "/download",produces = {"application/json;charset=UTF-8"} )
    @LoggerTest
    public ResponseEntity<byte[]> download( HttpServletResponse response) throws IOException {
        ResponseEntity<byte[]> responseEntity = uploadUtils.download(response);
        return responseEntity;
    }
    @GetMapping("/login")
    public Object login(HttpServletRequest request){
//        HttpSession session = request.getSession(true);
//        session.setAttribute("username","zhaodong");
//        String sessionId = session.getId();
        String accesstoken = JWTUtil.encode("11064957", 5);
        return accesstoken;
    }
    @GetMapping("/testRedisCluster")
    public Object testRedisCluster(){
        Map<String,Object> param=new HashMap();
        String people = redisTemplate.opsForValue().get("goodmorning");
        if( people==null) {
            redisTemplate.opsForValue().set("goodmorning", "羽田机场");
            return "success";
        }else {
            param.put("obj",people);
            return param;
        }
    }
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @PostConstruct
    public void init() throws IOException {
//        String script = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("lua/1122.lua"), "utf-8");
//        rs=new DefaultRedisScript(script,Long.class);
        String stockLua=IOUtils.toString(getClass().getClassLoader().getResourceAsStream("luaScripts/couponLua.lua"), "utf-8");
        couponScript=new DefaultRedisScript<>(stockLua,String.class);
    }
    private RedisScript<String> couponScript;

    @GetMapping(value = "/testCouponLua",produces="text/html;charset=UTF-8")
    @ResponseBody
    public Object testCouponLua(Integer userId,Integer count){
//        String s = redisTemplate.opsForList().rightPop("coupon_to_count:456");
        String execute = (String)redisTemplate.execute(couponScript, Arrays.asList("{"+userId + "}"), count+"");
        switch (execute){
            case "-4":
                return "活动还没开始";
            case "-5":
                return "活动已经结束";
            case "-3":
                return "库存不够";
            default:
                return execute;

        }
    }

    @GetMapping("/testCouponLuaInsert")
    @ResponseBody
    public Object testCouponLuaInsert(String userId,String startTime,String endTime) throws JsonProcessingException {
        for(int i=0;i<10;i++) {
            redisTemplate.opsForList().leftPush("coupon_to_count:{"+userId+"}","20090709005"+i+1);
        }
        Map<String,Long> map=new HashMap();
        ZoneId zoneId = ZoneId.systemDefault(); // or: ZoneId.of("Europe/Oslo");
        long startSecond = LocalDateTime.parse(startTime, dtf).atZone(zoneId).toEpochSecond();
        long endSecond = LocalDateTime.parse(endTime, dtf).atZone(zoneId).toEpochSecond();
        map.put("startTime",Long.valueOf(startSecond));
        map.put("endTime",Long.valueOf(endSecond));
        redisTemplate.opsForValue().set("coupon_to_duration:{"+userId+"}",objectMapper.writeValueAsString(map));
        return "sucess";


    }
    private DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private ObjectMapper objectMapper;
}
