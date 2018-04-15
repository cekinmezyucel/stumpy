package com.stumpy.repository;

import java.util.Map;

public interface CrudRepository<T, I> {

  Map<Object, Object> findAllEntities();

  void add(T entity);

  void delete(I id);

  T findEntity(I id);

}
