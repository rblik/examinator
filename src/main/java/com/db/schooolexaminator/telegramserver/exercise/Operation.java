package com.db.schooolexaminator.telegramserver.exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * Created by Jeka on 23/08/2016.
 */
@AllArgsConstructor
@Getter
public enum Operation implements Serializable {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    private final String sign;


    @Override
    public String toString() {
        return sign;
    }
}
