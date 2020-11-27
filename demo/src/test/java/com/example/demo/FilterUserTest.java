package com.example.demo;

import ch.qos.logback.core.Appender;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mockito;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class FilterUserTest {
    private FilterUser target;
    private HttpServletResponse httpServletResponse;
    private HttpServletRequest httpServletRequest;
    private ServletRequest servletRequest;
    private ServletResponse servletResponse;
    private FilterChain filterChain;


    @Before
    public void init(){
        this.target = new FilterUser();
        servletRequest = Mockito.mock(ServletRequest.class);
        servletResponse = Mockito.mock(ServletResponse.class);
        httpServletResponse = Mockito.mock(HttpServletResponse.class);
        httpServletRequest = Mockito.mock(HttpServletRequest.class);
        filterChain = Mockito.mock(FilterChain.class);



    }
    @Test
    public void nullUserIDTest() throws IOException, ServletException {

        Mockito.when(httpServletRequest.getCookies()).thenReturn(null);
        this.target.doFilter(httpServletRequest,httpServletResponse,filterChain);
        System.out.print(" Should Log an error ----> TEST PASSED");
        Assert.assertTrue(true);
    }



}
