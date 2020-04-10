package com.bsuir.kononovich.InConnect.exceptions;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@ControllerAdvice
@Controller
public class ExceptionHandler implements ErrorController{
    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        Date date=java.util.Calendar.getInstance().getTime();
        if(response.getStatus() == HttpStatus.NOT_FOUND.value()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorCode", "Error 404");
            modelAndView.addObject("errorMessage", "Page not found");
            modelAndView.addObject("time", date);
        }
        else if(response.getStatus() == HttpStatus.FORBIDDEN.value()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorCode", "Error 403");
            modelAndView.addObject("errorMessage", "Forbidden");
            modelAndView.addObject("time", date);
        }
        else if(response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorCode", "Error 500");
            modelAndView.addObject("errorMessage", "Internal server error");
            modelAndView.addObject("time", date);
        }
        else if(response.getStatus() == HttpStatus.UNAUTHORIZED.value()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorCode", "Error 401");
            modelAndView.addObject("errorMessage", "Unauthorized");
            modelAndView.addObject("time", date);
        }
        else {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorCode", "Error");
            modelAndView.addObject("errorMessage", "Undefined error");
            modelAndView.addObject("time", date);
        }

        return modelAndView;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}