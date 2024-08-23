package com.apple.shop;

import lombok.Setter;

public class Age {
    private String name;
    private int age;

    public void setAge(int age) {
        if(age > 0 && age < 100) {
            this.age = age;
        }
    }

    public int addAge(int age) {
        return this.age += 1;
    };
}