package com.bsuir.kononovich.InConnect.controller;

import com.bsuir.kononovich.InConnect.domain.Message;
import com.bsuir.kononovich.InConnect.repo.MessageRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
@Api(value = "/api/messages")
public class MessageController {
    private final MessageRepo messageRepo;

    @Autowired
    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }
    @ApiOperation(value = "Get list of messages", response = Iterable.class)
    @GetMapping
    public List<Message> list() throws SQLException {
        if (messageRepo == null)
            throw new SQLException("Messages not found");
        else
            return messageRepo.findAll();
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
    public Message getOne(@PathVariable("id") Message message) throws SQLException {
        if (message == null)
            throw new SQLException("Message not found");
        else
            return message;
    }

    @ApiOperation(value = "Create specific message ", response = Message.class)
    @PostMapping
    public Message create(@RequestBody Message message){
        message.setCreationDate(LocalDateTime.now());
        return messageRepo.save(message);
    }

    private byte number = 0;
    @Scheduled(fixedRate = 60 * 1000)
    public void createSchedule() {
        Message message = new Message();
        number++;
        message.setText("Automated test message №"+ number + " Date 'n' Time: " + LocalDateTime.now());
        message.setCreationDate(LocalDateTime.now());
        System.out.println("posted test message");
        messageRepo.save(message);
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
    public Message update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message message
    ) {
        BeanUtils.copyProperties(message, messageFromDb, "id");
        return messageRepo.save(messageFromDb);
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
        messageRepo.delete(message);
    }
}