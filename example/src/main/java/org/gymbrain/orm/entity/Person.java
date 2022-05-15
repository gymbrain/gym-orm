package org.gymbrain.orm.entity;

import org.gymbrain.orm.common.entity.BaseEntity;
import org.gymbrain.orm.common.entity.Column;
import org.gymbrain.orm.common.entity.Table;

@Table(name = "person")
public class Person extends BaseEntity {
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "age")
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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
