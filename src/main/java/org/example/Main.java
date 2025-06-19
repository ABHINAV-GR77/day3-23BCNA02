package org.example;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
Scanner sc=new Scanner(System.in);
  Student s=new Student();
        while(true)
                {
                System.out.println("Enter your choice");
            System.out.println("1.Enroll");
            System.out.println("2.Show");
            System.out.println("3.Update ");
            System.out.println("4.Find a Student details");
int ch=sc.nextInt();
            switch(ch) {
        case 1:
        s.Enroll();
                    break;
                            case 2:
                         s.Show();
                    break;
                            case 3:
                           s.Update();
                    break;
                            case 4:
                            s.find();
                    break;
                            }
                }

    }
       }
