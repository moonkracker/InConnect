package com.bsuir.kononovich.InConnect.Controller;

import com.bsuir.kononovich.InConnect.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("im")
public class MessageController {
    public List<Map<String,String>> messages = new ArrayList<Map<String,String>>(){{
        add(new HashMap<String,String>() {{ put ("id", "1"); put("text", "test"); }});
        add(new HashMap<String,String>() {{ put ("id", "2"); put("text", "test2"); }});
        add(new HashMap<String,String>() {{ put ("id", "3"); put("text", "test3"); }});
    }};
    @GetMapping
    public List<Map<String,String>> list(){
        return messages;
    }

    @GetMapping("{id}")
public Map<String,String> getOne(@PathVariable String id){
        return messages.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}
