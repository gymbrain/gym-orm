package org.gymbrain.orm.common.entity;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
@Documented
@Inherited
public @interface Column {
    String name();
}
