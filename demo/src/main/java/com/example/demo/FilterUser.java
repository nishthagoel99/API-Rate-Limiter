package com.example.demo;

import org.slf4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.SysexMessage;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@Component
public class FilterUser implements javax.servlet.Filter {

    private static final Logger LOGGER= LoggerFactory.getLogger(FilterUser.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        //to get userID from cookie
        String userID = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        if(cookies!=null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("userID")) {
                    userID = cookies[i].getValue();
                    break;

                }
            }
        }

        if(userID==null) // if not authorized
        {
            //User not in Database
            LOGGER.error("UserID not sent in HTTP Request.");
            httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid/Unauthorized user "); //401
            return;
        }
        String requestURI =  httpServletRequest.getRequestURI().substring(1);

        if(!Global.user_plus_api_rate_map.containsKey(userID+requestURI))
            // if userID+api does not exist in the HashMap
        {
            //add user+api to the hash map
            try {
                LOGGER.info("user id in filteruser:" + userID);
                Global.preparedStatement.setString(1, userID);
                Global.preparedStatement.setString(2, requestURI);
                ResultSet rs = Global.preparedStatement.executeQuery();

                if (rs.next() == false) // if the api or user is wrong/doesnotexist
                {
                    //user or api is not in database
                    LOGGER.error("API/User not in Database for user.");
                    httpServletResponse.sendError(HttpStatus.BAD_REQUEST.value(), "BAD REQUEST"); //400
                    return;
                }
                int max_rate = 0;
                max_rate = rs.getInt("max_rate");

                //check if rate limit is defined or not
                if (max_rate == 0) {
                    max_rate = Global.default_rate;   //default rate is set as 5
                }
                LOGGER.info("Max rate retrieved from DB :" + max_rate);
                ArrayList<Integer> arrayList = new ArrayList<Integer>();
                arrayList.add(max_rate);
                arrayList.add(1);
                Global.user_plus_api_rate_map.put(userID + requestURI, arrayList);


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        //Call rate limiter here as authorization is successful
        try {
            boolean check_if_call_can_be_made = RateLimiterJar.process_incoming_requests(userID,requestURI);
            if(!check_if_call_can_be_made) //cannot make API call
            {
                LOGGER.error("Cannot make API call******************429 TOO MANY REQUESTS");
                httpServletResponse.sendError(HttpStatus.TOO_MANY_REQUESTS.value(), "TOO MANY REQUESTS"); //429
                return;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
