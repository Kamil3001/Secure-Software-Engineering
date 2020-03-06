package com.mcino.assignment1.Utils;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class GradeQueryHelper {

    @Id
    public String grade;

    private int total;
}
