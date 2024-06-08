package org.mikedegeofroy.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class OwnerDto {
    private Integer id;
    private String name;
    private String surname;
    private Date birthdate;
}
