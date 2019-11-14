package com.niup.demo.entity;

public class Test extends BaseEntity{
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Test [id=" + this.getId() + ", name=" + name + "]";
    }
    
}