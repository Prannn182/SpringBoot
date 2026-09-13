package com.example.Journal.App.Entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document()//this will tell the compiler that this file is a mongodb instance mapped file
@Getter
@Setter //u can use @Data that includes everything 
public class JournalEntity {

    @Id // this will treat id as a primary key
    private ObjectId id;


    private LocalDateTime date;

    private String title;
    private String content;
}


