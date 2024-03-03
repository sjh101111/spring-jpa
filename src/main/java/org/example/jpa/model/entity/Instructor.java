package org.example.jpa.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "instructor")
public class Instructor {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "left", nullable = false)
    private Boolean left;

    @Column(name = "desc")
    private String desc;

    @CreatedDate
    //@CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    //@LastModifiedDate
    //@UpdateTimestamp

}
