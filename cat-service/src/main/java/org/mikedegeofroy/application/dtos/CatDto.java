package org.mikedegeofroy.application.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.mikedegeofroy.dal.entities.Breed;
import org.mikedegeofroy.dal.entities.Color;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CatDto {
    private Integer id;
    private String name;
    private Date birthdate;
    private Breed breed;
    private Color color;
    private List<CatDto> friends;
}
