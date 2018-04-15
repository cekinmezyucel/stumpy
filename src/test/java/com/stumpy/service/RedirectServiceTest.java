package com.stumpy.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stumpy.model.UrlModel;
import com.stumpy.repository.UrlModelRepository;
import com.stumpy.util.UrlGeneratorUtil;

@RunWith(SpringJUnit4ClassRunner.class)
public class RedirectServiceTest {

  @Mock
  private UrlModelRepository urlModelRepository;

  @Mock
  private StatisticService statisticService;

  @InjectMocks
  private RedirectService redirectService;

  @Test
  public void testWhenShortUrlNonNull() {
    Mockito.when(urlModelRepository.findEntity(UrlGeneratorUtil.decode("MA")))
        .thenReturn(new UrlModel(1L, "https://wwww.google.com.tr"));
    assertEquals("https://wwww.google.com.tr", redirectService.getLongUrl("MA"));
  }

}
