package org.gymbrain.orm.common.repository;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
@Documented
@Inherited
public @interface GymRepository {
}
