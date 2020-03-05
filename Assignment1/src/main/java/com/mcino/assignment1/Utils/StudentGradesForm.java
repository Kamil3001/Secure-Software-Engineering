package com.mcino.assignment1.Utils;

import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class StudentGradesForm {
    @Valid
    private List<StudentGrade> studentGrades;
}

