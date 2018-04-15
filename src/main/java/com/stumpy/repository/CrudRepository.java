package com.stumpy.repository;

import java.util.Map;

public interface CrudRepository<K, T, I> {

  Map<K, T> findAllEntities();

  void add(T entity);

  void delete(I id);

  T findEntity(I id);

}
