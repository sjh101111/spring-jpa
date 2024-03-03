package org.example.jpa.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InstructorDto {
    private Long id;
    private String name;
    private String left;
    private String desc;
}
