package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.Scanner;

public class Student {
    public void Enroll(){
        Scanner sc = new Scanner(System.in);
        String uri = "mongodb://localhost:27017/"; // Replace with your MongoDB URI if needed
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("Student");
            MongoCollection<Document> Student = database.getCollection("Student");
            MongoCollection<Document> Course = database.getCollection("Course");
            MongoCollection<Document>Enrollment = database.getCollection("Enrollment");

            //INSERTING STUDENTS

            Document student1=new Document("name","abhi").append("Age","21");
            Document student2=new Document("name","mathew").append("Age","20");
            Document student3=new Document("name","joyal").append("Age","19");
            Student.insertOne(student1);
            ObjectId studentId1=student1.getObjectId("_id");
            Student.insertOne(student2);
            ObjectId studentId2=student2.getObjectId("_id");
            Student.insertOne(student3);
            ObjectId studentId3=student3.getObjectId("_id");

            //INSERTING COURSES

            Document Course1=new Document("name","Maths").append("Faculty","Yeshoda").append("Credits","3");
            Document Course2=new Document("name","Physics").append("Faculty","Pandian").append("Credits","4");
            Document Course3 =new Document("name","Chemistry").append("Faculty","Kurian").append("Credits","3");
            Course.insertOne(Course1);
            ObjectId courseId1=Course1.getObjectId("_id");
            Course.insertOne(Course2);
            ObjectId courseId2=Course2.getObjectId("_id");
            Course.insertOne(Course3);
            ObjectId courseId3=Course3.getObjectId("_id");
            // add embedded

            Document EmbeddedEnrollment1=new Document("type","embedded").append("Student",student1).append("Course",Course1);
            Document EmbeddedEnrollment2=new Document("type","embedded").append("Student",student2).append("Course",Course2);
            Document EmbeddedEnrollment3=new Document("type","embedded").append("Student",student3).append("Course",Course3);
            Enrollment.insertOne(EmbeddedEnrollment1);
            Enrollment.insertOne(EmbeddedEnrollment2);
            Enrollment.insertOne(EmbeddedEnrollment3);

            Document referencedEnrollment1 =new Document("type","referenced").append("Student",studentId1).append("Course",courseId1);
            Document referencedEnrollment2 =new Document("type","referenced").append("Student",studentId2).append("Course",courseId2);
            Document referencedEnrollment3 =new Document("type","referenced").append("Student",studentId3).append("Course",courseId3);

            Enrollment.insertOne(referencedEnrollment1);
            Enrollment.insertOne(referencedEnrollment2);
            Enrollment.insertOne(referencedEnrollment3);
        }
        }
    public void Show(){
        String uri = "mongodb://localhost:27017/";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("Student");
        MongoCollection<Document>Enrollment = database.getCollection("Enrollment");
        for(Document doc: Enrollment.find()){
            System.out.println(doc.toJson());
        }
    }
    public void Update(){
        String uri = "mongodb://localhost:27017/";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("Student");
        MongoCollection<Document>Enrollment = database.getCollection("Enrollment");
        MongoCollection<Document> Student = database.getCollection("Student");
      long count=Student.updateOne(Filters.eq("name","abhi"),Updates.set("name","Chidambaram")).getModifiedCount();
      if (count >0){
          System.out.println("Updated Succesfully");
      }
      else{
          System.out.println("no match found");
      }
    }
    }

