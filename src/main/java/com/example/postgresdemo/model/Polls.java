package com.example.postgresdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "polls")
public class Polls {

    @Id
    @GeneratedValue(generator = "polls_generator")
    @SequenceGenerator(
            name = "polls_generator",
            sequenceName = "polls_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(columnDefinition = "text")
    private String text;

    @Column(columnDefinition = "text")
    private String name;

    @Column(columnDefinition = "text")
    private Boolean active;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStart;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnd;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
