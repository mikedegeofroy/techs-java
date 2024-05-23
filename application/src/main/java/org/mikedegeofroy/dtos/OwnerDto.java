package org.mikedegeofroy.dtos;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OwnerDto {
    private Integer id;
    private String name;
    private Date birthdate;
    private String surname;
    private String email;
    private List<CatDto> cats;
}
