package com.stumpy.model;

import java.io.Serializable;
import java.util.Map;

import com.stumpy.model.type.StatisticType;


public class UrlStatisticCounterModel implements Serializable {

  private static final long serialVersionUID = -9080598277655966099L;

  private Long id;
  private Map<StatisticType, Long> statisticCounter;

  public UrlStatisticCounterModel() {
    super();
  }

  /**
   * Constructor using all fields.
   * 
   * @param id.
   * @param statisticCounter.
   */
  public UrlStatisticCounterModel(Long id, Map<StatisticType, Long> statisticCounter) {
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

  public Map<StatisticType, Long> getStatisticCounter() {
    return statisticCounter;
  }

  public void setStatisticCounter(Map<StatisticType, Long> statisticCounter) {
    this.statisticCounter = statisticCounter;
  }



}
