package org.gymbrain.orm.entity;

import org.gymbrain.orm.common.entity.BaseEntity;
import org.gymbrain.orm.common.entity.Table;

@Table(name = "person")
public class Person extends BaseEntity {
    private String firstName;
    private int age;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
