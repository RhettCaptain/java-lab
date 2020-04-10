package com.github.rhettcaptain.serialization;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

public class NoConClass {
    private ZonedDateTime zonedDateTime;
    @Setter
    private int age;
    @Getter
    private String hello;
    public NoConClass(ZonedDateTime zonedDateTime){
        this.zonedDateTime = zonedDateTime;
        this.age=1;
        this.hello = "world";
    }
    public NoConClass(ZonedDateTime zonedDateTime, int age){
        this.zonedDateTime = zonedDateTime;
        this.age=age;
        this.hello = "world";
    }

}
