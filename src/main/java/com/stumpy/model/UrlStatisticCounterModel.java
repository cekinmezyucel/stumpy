package com.stumpy.model;

import java.io.Serializable;
import java.util.Map;


public class UrlStatisticCounterModel implements Serializable {

  private static final long serialVersionUID = -9080598277655966099L;

  private Long id;
  private Map<String, Long> statisticCounter;

  public UrlStatisticCounterModel() {
    super();
  }

  /**
   * Constructor using all fields.
   * 
   * @param id.
   * @param statisticCounter.
   */
  public UrlStatisticCounterModel(Long id, Map<String, Long> statisticCounter) {
    super();
    this.id = id;
    this.statisticCounter = statisticCounter;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Map<String, Long> getStatisticCounter() {
    return statisticCounter;
  }

  public void setStatisticCounter(Map<String, Long> statisticCounter) {
    this.statisticCounter = statisticCounter;
  }

}
