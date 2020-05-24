package com.bsuir.kononovich.InConnect.dto;

import com.bsuir.kononovich.InConnect.domain.Views;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonView(Views.Id.class)
@Data@AllArgsConstructor
public class WebSockEventDto {
    private ObjectType objectType;
    private EventType eventType;
    @JsonRawValue
    private String body;
}
