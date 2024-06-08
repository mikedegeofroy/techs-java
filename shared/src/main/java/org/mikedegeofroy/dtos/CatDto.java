package org.mikedegeofroy.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CatDto {
    private Integer id;
    private String name;
    private Date birthdate;
    private Breed breed;
    private Color color;
    private List<CatDto> friends;
}
