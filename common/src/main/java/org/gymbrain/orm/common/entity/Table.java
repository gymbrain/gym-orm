package org.gymbrain.orm.common.entity;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
@Documented
@Inherited
public @interface Table {
    String name();
}
