package org.mikedegeofroy.dtos;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OwnerDto {
    private Integer id;
    private String name;
    private String surname;
    private Date birthdate;
    private List<CatDto> cats;
}
