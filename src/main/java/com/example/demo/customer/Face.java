package com.example.demo.customer;

import com.example.demo.model.Stu;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "basic.face")
public class Face {

    @RabbitHandler
    public void receive(String message) {
        System.out.println("face = " + message);
    }
    @RabbitHandler
    public void receive(Stu message) {
        System.out.println("face = " + message);
    }

    @RabbitHandler
    public void receive(Map<String, Object> message) {
        for (Map.Entry<String,Object> entry : message.entrySet()){
            System.out.println("faceKey = "+entry.getKey()+", faceValue = "+entry.getValue());
        }
    }
}
