package com.rabbitmq.test.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by wuhuachuan on 16/3/30.
 */

@Data
@NoArgsConstructor
@ToString
public class Teacher {
    private String teacherName;

    public Teacher(String teacherName){
        this.teacherName = teacherName;
    }
}
