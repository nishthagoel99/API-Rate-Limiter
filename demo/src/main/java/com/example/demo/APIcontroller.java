package com.example.demo;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.slf4j.LoggerFactory;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class APIcontroller {
private static final Logger LOGGER= LoggerFactory.getLogger(APIcontroller.class);
    @RequestMapping("/documents")
    @ResponseBody
    public void documents() throws IOException, InterruptedException {

        LOGGER.info("/documents API called successfully!******************200 OK");
    }

    @RequestMapping("/resources")
    @ResponseBody
    public void resources() throws IOException, InterruptedException {

        LOGGER.info("/resources API called successfully!******************200 OK");
    }

    @RequestMapping("/login")
    @ResponseBody
    public void login() throws IOException, InterruptedException {

        LOGGER.info("/login API called successfully!******************200 OK");
    }
}
