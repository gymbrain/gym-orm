package org.gymbrain.orm.common.repository;

import org.gymbrain.orm.common.entity.BaseEntity;

public interface BaseRepository {
    Integer save(BaseEntity entity);
}
