package org.gymbrain.orm.common.repository;

import org.gymbrain.orm.common.entity.BaseEntity;

public interface BaseRepository<E extends BaseEntity> {
    Integer save(E entity);

    Integer update(E entity);

     void test();
}
