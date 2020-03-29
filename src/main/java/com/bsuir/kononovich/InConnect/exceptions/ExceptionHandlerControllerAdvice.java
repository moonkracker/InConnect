package com.bsuir.kononovich.InConnect.exceptions;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Controller
public class ExceptionHandlerControllerAdvice implements ErrorController{
    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();

        if(response.getStatus() == HttpStatus.NOT_FOUND.value()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorCode", "404");
            modelAndView.addObject("errorMessage", "Not found");
        }
        else if(response.getStatus() == HttpStatus.FORBIDDEN.value()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorCode", "403");
            modelAndView.addObject("errorMessage", "Forbidden");

        }
        else if(response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorCode", "500");
            modelAndView.addObject("errorMessage", "Internal server error");
        }
        else {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorCode", "Error");
            modelAndView.addObject("errorMessage", "Undefined error");
        }

        return modelAndView;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}