package com.bsuir.kononovich.InConnect.util;

import com.bsuir.kononovich.InConnect.dto.EventType;
import com.bsuir.kononovich.InConnect.dto.ObjectType;
import com.bsuir.kononovich.InConnect.dto.WebSockEventDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Component
public class WebSockSender {
    private final SimpMessagingTemplate template;
    private final ObjectMapper mapper;

    public WebSockSender(SimpMessagingTemplate template, ObjectMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }
    public <T> BiConsumer<EventType, T> getSender(ObjectType objectType, Class view){
        ObjectWriter writer = mapper
                .setConfig(mapper.getSerializationConfig())
                .writerWithView(view);

        return (EventType eventType, T payload) -> {
            String value = null;

            try {
                value = writer.writeValueAsString(payload);
            } catch (JsonProcessingException e) {
               throw new RuntimeException(e);
            }

            template.convertAndSend(
                    "/topic/activity",
                    new WebSockEventDto(objectType, eventType, value)
            );
        };
    }
}
