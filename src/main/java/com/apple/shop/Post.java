package com.apple.shop;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.ToString;

import java.util.Date;

@Entity
@ToString
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public  String title;
    public Date postDate;
}
