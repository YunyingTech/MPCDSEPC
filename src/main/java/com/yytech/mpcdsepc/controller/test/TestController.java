package com.yytech.mpcdsepc.controller.test;

import com.yytech.mpcdsepc.result.Result;
import com.yytech.mpcdsepc.websocket.TestWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private TestWebSocket testWebSocket;

    @PostMapping("sendmsg")
    public void sendAll(@RequestBody String msg){
        testWebSocket.sendAllMessage(msg);
    }

}
