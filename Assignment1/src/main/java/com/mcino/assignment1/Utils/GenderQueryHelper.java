package com.mcino.assignment1.Utils;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class GenderQueryHelper {

    @Id
    private String gender;

    private int total;
}