package com.db.schooolexaminator.server.exercise;

import lombok.Data;
import lombok.Setter;


@Data
@Setter
public class Exercise {
    private int a;
    private int b;
    private Operation operation;
    private int answer;


    @Override
    public String toString() {
        return a + " " + operation + " " + b + " = " + answer;
    }
}










