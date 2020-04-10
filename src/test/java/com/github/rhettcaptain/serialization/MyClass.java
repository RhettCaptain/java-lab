package com.github.rhettcaptain.serialization;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class MyClass {

    private String name;
    private int age;
    private boolean isGood;
    private boolean hasMoney;
    private MySubClass subClass;
    private ZonedDateTime zonedDateTime;
}
@Data
class MySubClass {
    private String id;
}
