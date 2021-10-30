package com.example.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity{

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    private String author;

    private String status;
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.status = "wrote";
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void delete(){
        this.status = "delete";
    }
}
