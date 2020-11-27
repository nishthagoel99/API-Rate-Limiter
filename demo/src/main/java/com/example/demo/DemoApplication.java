package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

		//initialize the spring application
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("********************************************************************");
		//initialize the database connections and prepared statements
		Global obj = new Global();


	}
}
