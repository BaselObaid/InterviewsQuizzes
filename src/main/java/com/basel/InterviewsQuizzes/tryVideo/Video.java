package com.basel.InterviewsQuizzes.tryVideo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "video_upload")
public class Video{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String tags;
    private String videoName;
    private Date addDate;

}