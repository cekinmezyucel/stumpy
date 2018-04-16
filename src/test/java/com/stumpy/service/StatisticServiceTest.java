package com.stumpy.service;

import static com.stumpy.model.type.StatisticType.HIT_COUNT;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stumpy.repository.UrlStatisticCounterRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class StatisticServiceTest {

  @Mock
  private UrlStatisticCounterRepository urlStatisticCounterRepository;

  @InjectMocks
  private StatisticService statisticService;

  @Test
  public void successWhenStatisticsNotExist() {
    Mockito.when(urlStatisticCounterRepository.findEntity(1L)).thenReturn(null);
    assertEquals(Long.valueOf(0L), statisticService.getStatisticCount("MQ", HIT_COUNT.name()));
  }
}
