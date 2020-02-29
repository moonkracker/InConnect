package com.bsuir.kononovich.InConnect.Controller;

import com.bsuir.kononovich.InConnect.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("im")
public class MessageController {
    private int count = 4;

    public List<Map<String,String>> messages = new ArrayList<Map<String,String>>(){{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("text", "test");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("text", "test2");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("text", "test3");
        }});
        add(new HashMap<String, String>() {{
            put("id", "4");
            put("text", "test4");
        }});
    }};
    @GetMapping
    public List<Map<String,String>> list() {
        return messages;
    }

    @GetMapping("{id}")
    public Map<String,String> getOne(@PathVariable String id){
        return getMessage(id);
    }

    private Map<String, String> getMessage(@PathVariable String id) {
        return messages.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String, String > create(@RequestBody Map<String,String> message) {
       message.put("id", String.valueOf(count++));
       messages.add(message);
       return message;
    }

    @PutMapping("{id}")
    public Map<String,String> update(@PathVariable String id, @RequestBody Map<String,String> message) {
        Map <String,String> messageFromDatabase = getMessage(id);
        messageFromDatabase.putAll(message);
        messageFromDatabase.put("id", id);
        return messageFromDatabase;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Map<String,String> message = getMessage(id);
        messages.remove(message);
    }
}
