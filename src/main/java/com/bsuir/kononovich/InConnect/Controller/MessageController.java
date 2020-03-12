package com.bsuir.kononovich.InConnect.Controller;

import com.bsuir.kononovich.InConnect.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/controllers/inconnect/messages")
public class MessageController {
    private int count = 5;

    public List<Map<String,String>> msgDatabase = new ArrayList<Map<String,String>>(){{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("text", "first string");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("text", "second string");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("text", "third string");
        }});
        add(new HashMap<String, String>() {{
            put("id", "4");
            put("text", "fourth string");
        }});
    }};
    @GetMapping
    public List<Map<String,String>> list() {
        return msgDatabase;
    }

    @GetMapping("{id}")
    public Map<String,String> GetOneMessage(@PathVariable String id){
        return getMessage(id);
    }

    private Map<String, String> getMessage(@PathVariable String id) {
        return msgDatabase.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String, String > PostMessage(@RequestBody Map<String,String> message) {
       message.put("id", String.valueOf(count++));
       msgDatabase.add(message);
       return message;
    }

    @PutMapping("{id}")
    public Map<String,String> PutMessage(@PathVariable String id, @RequestBody Map<String,String> message) {
        Map <String,String> messageFromDatabase = getMessage(id);
        messageFromDatabase.putAll(message);
        messageFromDatabase.put("id", id);
        return messageFromDatabase;
    }

    @DeleteMapping("{id}")
    public void DeleteMessage(@PathVariable String id){
        Map<String,String> message = getMessage(id);
        msgDatabase.remove(message);
    }
}
