package com.db.schooolexaminator.telegramserver.exercise;

import lombok.Data;
import lombok.Setter;


@Data
@Setter
public class Exercise {
    private int a;
    private int b;
    private Operation operation;
    private int answer;



    public String toStringWithoutResult() {
        return a + " " + operation + " " + b;
    }

    @Override
    public String toString() {
        return a + " " + operation + " " + b + " = " + answer;
    }
}










