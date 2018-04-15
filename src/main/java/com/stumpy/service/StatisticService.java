package com.stumpy.service;

import static com.stumpy.model.type.StatisticType.HIT_COUNT;
import static com.stumpy.util.UrlGeneratorUtil.decode;
import static java.util.Objects.isNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.stumpy.model.UrlStatisticCounterModel;
import com.stumpy.model.type.StatisticType;
import com.stumpy.repository.UrlStatisticCounterRepository;

@Service
public class StatisticService {

  private static final Logger LOG = LoggerFactory.getLogger(StatisticService.class);

  @Autowired
  private UrlStatisticCounterRepository urlStatisticCounterRepository;

  /**
   * Retrieve shortUrl's statisticType count.
   * 
   * @param shortUrl.
   * @return {@link Long}.
   */
  public Long getStatisticCount(String shortUrl, String statisticTypeName) {
    UrlStatisticCounterModel statisticModel = urlStatisticCounterRepository.findEntity(decode(shortUrl));
    if (Objects.nonNull(statisticModel)) {
      Optional<Entry<StatisticType, Long>> hitCountMap = statisticModel.getStatisticCounter().entrySet().stream()
          .filter(s -> s.getKey().equals(StatisticType.valueOf(statisticTypeName))).findAny();
      return hitCountMap.isPresent() ? hitCountMap.get().getValue() : 0L;
    } else {
      return 0L;
    }
  }


  /**
   * Create statisticCounter if not exist, increment if it exist.
   * 
   * <p>
   * This method runs {@link Async}.
   * </p>
   * 
   * @param id.
   */
  @Async
  public void executeStatisticOperations(Long id) {
    incrementStatisticCount(id, HIT_COUNT);
  }


  private void incrementStatisticCount(Long id, StatisticType statisticType) {
    UrlStatisticCounterModel statisticModel = urlStatisticCounterRepository.findEntity(id);
    if (isNull(statisticModel)) {
      urlStatisticCounterRepository
          .add(new UrlStatisticCounterModel(id, incrementCounter(statisticType, new HashMap<>())));
    } else {
      statisticModel.setStatisticCounter(incrementCounter(statisticType, statisticModel.getStatisticCounter()));
      urlStatisticCounterRepository.add(statisticModel);
    }
  }


  private Map<StatisticType, Long> incrementCounter(StatisticType statisticType,
      Map<StatisticType, Long> statisticCounter) {
    statisticCounter.computeIfPresent(statisticType, (key, value) -> value + 1L);
    statisticCounter.computeIfAbsent(statisticType, key -> 1L);
    return statisticCounter;
  }

}
