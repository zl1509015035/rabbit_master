package com.example.demo.controller;

import com.example.demo.model.Stu;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rabbit")
public class TestController {

    @Autowired
    RabbitTemplate rabbitTemplate;


    @PostMapping(value = "/send")
    public void test() {
        Map<String, String> map = new HashMap<>();
        map.put("111", "111");
        rabbitTemplate.convertAndSend("basic.one", "basic.face", "abc");
        map.put("222", "222");
        rabbitTemplate.convertAndSend("basic.two", "basic.faceImage", "111");

    }

    @PostMapping(value = "/sendMap")
    public void testMap() {
        Map<String, String> map = new HashMap<>();
        map.put("111", "111");
        rabbitTemplate.convertAndSend("basic.one", "basic.face", map);
        map.put("222", "222");
        rabbitTemplate.convertAndSend("basic.two", "basic.faceImage", map);
    }

    @PostMapping(value = "/sendStu")
    public void testStu() {
        Stu stu = new Stu("张三", 18);
        rabbitTemplate.convertAndSend("basic.one", "basic.face", stu);
        stu.setName("李四");
        stu.setAge(20);
        rabbitTemplate.convertAndSend("basic.two", "basic.faceImage", stu);
    }


    @PostMapping(value = "/receive")
    public void receive() {
        Object face = rabbitTemplate.receiveAndConvert("basic.face");
        System.out.println(face.getClass());
        System.out.println("face = " + face);
        Object faceImage = rabbitTemplate.receiveAndConvert("basic.faceImage");
        System.out.println(faceImage.getClass());
        System.out.println("faceImage = " + faceImage);
    }


}
