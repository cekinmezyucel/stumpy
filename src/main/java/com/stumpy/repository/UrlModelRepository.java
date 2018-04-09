package com.stumpy.repository;

import java.util.Map;

import com.stumpy.model.UrlModel;

public interface UrlModelRepository {

  Map<Object, Object> findAllUrlModels();

  void add(UrlModel movie);

  void delete(Long id);

  UrlModel findUrlModel(Long id);

}
