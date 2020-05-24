package com.bsuir.kononovich.InConnect.controller;

import com.bsuir.kononovich.InConnect.domain.Message;
import com.bsuir.kononovich.InConnect.domain.User;
import com.bsuir.kononovich.InConnect.domain.Views;
import com.bsuir.kononovich.InConnect.dto.MessagePageDto;
import com.bsuir.kononovich.InConnect.service.MessageService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/api/messages")
@Api(value = "/api/messages")
public class MessageController {
    public static final int MESSAGES_PER_PAGE = 6;

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {

        this.messageService = messageService;
    }

    @ApiOperation(value = "Get list of messages", response = Iterable.class)
    @GetMapping
    @JsonView(Views.FullMessage.class)
    public MessagePageDto list(
            @PageableDefault(size = MESSAGES_PER_PAGE, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return messageService.findAll(pageable);
    }

    @ApiOperation(value = "Get specific message ", response = Message.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "Not found") })
    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message getOne(@PathVariable("id") Message message) {

        return message;
    }

    @ApiOperation(value = "Create specific message ", response = Message.class)
    @PostMapping
    @JsonView(Views.FullMessage.class)
    public Message create(@RequestBody Message message, @AuthenticationPrincipal User user) throws IOException {
        return messageService.create(message, user);
    }

    @ApiOperation(value = "Update specific message ", response = Message.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "Not found") })
    @PutMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message update(@PathVariable("id") Message messageFromDb, @RequestBody Message message) throws IOException {
        return messageService.update(messageFromDb, message);
    }

    @ApiOperation(value = "Delete specific message ", response = Message.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "Not found") })
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {

        messageService.delete(message);
    }
}
 /* private byte number = 0;
    @Scheduled(fixedRate = 60 * 1000) //Put messages in database every minute
    public void createSchedule() {
        Message message = new Message();
        number++;
        message.setText("Automated test message №"+ number + " Date 'n' Time: " + LocalDateTime.now());
        message.setCreationDate(LocalDateTime.now());
        System.out.println("posted test message");
        messageRepo.save(message);
    }*/