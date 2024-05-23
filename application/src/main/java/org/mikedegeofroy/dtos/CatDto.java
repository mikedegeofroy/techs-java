package org.mikedegeofroy.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.mikedegeofroy.models.Breed;
import org.mikedegeofroy.models.Color;

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
    private OwnerDto owner;
}
