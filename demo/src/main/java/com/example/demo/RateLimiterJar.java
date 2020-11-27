package com.example.demo;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RateLimiterJar {

    private static final Logger LOGGER= LoggerFactory.getLogger(RateLimiterJar.class);
    public static boolean printDetailsAndCallAPI(int no_of_calls, String requestnameandid,String requestName) throws IOException, InterruptedException {

        //get info from time map
        no_of_calls = Global.user_plus_api_rate_map.get(requestnameandid).get(1);
        int max_rate_limit = Global.user_plus_api_rate_map.get(requestnameandid).get(0);
        no_of_calls++;
        ArrayList<Integer> temparr = new ArrayList<Integer>();
        temparr.add(max_rate_limit);
        temparr.add(no_of_calls);
        Global.user_plus_api_rate_map.put(requestnameandid,temparr);
        LOGGER.info("Max rate Limit for this user from hash map: "+max_rate_limit);
        LOGGER.info("*> Number of calls  --> " + no_of_calls);
        LOGGER.info("@> user + api called = " +requestnameandid);

        // check if number of calls exceeds the rate limit
        if(no_of_calls <= max_rate_limit) {
            return true; //implies that API call can be made
        }
        else{

            return false; //implies that API call cannot be made
        }

    }

    public static boolean process_incoming_requests(String userid , String requestName) throws IOException, InterruptedException {

        String requestnameandid = userid+requestName;
        int no_of_calls = 1;

        // if the userandapi call is not there in the timemap
        if(!Global.timemap.containsKey(requestnameandid)) {

            //initializing  and resetting a new timemap for this user+api
            //set current time as start time
            Global.timemap.put(requestnameandid,System.nanoTime()/1000000000);

        }
        else
        {
                Long starttime;            // printing the time elapsed for this user+api
                Long currentime;
                Long remaining_time =0l;
                starttime  = Global.timemap.get(requestnameandid);
                currentime = System.nanoTime()/1000000000;
                remaining_time = (currentime-starttime);

                if(remaining_time < Global.fixed_window )
                    //checking if 60 seconds has elapsed for this user+api
                {
                    LOGGER.info("seconds elapsed since the first call = "+ remaining_time);

                    //print details and call API
                     return printDetailsAndCallAPI(no_of_calls,requestnameandid,requestName);

                }
                else //initializing  and resetting a new timemap for this user+api
                {
                    int max_no_of_calls = Global.user_plus_api_rate_map.get(requestnameandid).get(0);

                    ArrayList<Integer> temp = new ArrayList<Integer>();
                    temp.add(max_no_of_calls);
                    temp.add(0);
                    no_of_calls=1;
                    Global.user_plus_api_rate_map.put(requestnameandid,temp);
                    starttime=System.nanoTime()/1000000000;
                    Global.timemap.put(requestnameandid,starttime);
                    LOGGER.info("seconds elapsed since the first call = "+ (int)(System.nanoTime()/1000000000-starttime));
                    return printDetailsAndCallAPI(no_of_calls,requestnameandid,requestName);
                }
        }
        return true;
    }
}
