package com.stumpy.service;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stumpy.exception.model.StumpyException;
import com.stumpy.model.UrlModel;
import com.stumpy.repository.IdCounterRepository;
import com.stumpy.repository.UrlModelRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class ShortenerServiceTest {

  @Mock
  private UrlModelRepository urlModelRepository;

  @Mock
  private IdCounterRepository idCounterRepository;

  @InjectMocks
  private ShortenerService shortenerService;

  @Test
  public void successWhenValidLongUrlAndExistInDatabase() {
    Map<Long, UrlModel> map = new HashMap<>();
    map.put(1L, new UrlModel(1L, "http://www.google.com"));
    Mockito.when(urlModelRepository.findAllEntities()).thenReturn(map);
    assertEquals("MQ", shortenerService.getShortUrl("http://www.google.com"));

  }

  @Test
  public void successWhenValidUrlAndNotExistInDatabase() {
    Mockito.when(urlModelRepository.findAllEntities()).thenReturn(new HashMap<>());
    Mockito.when(idCounterRepository.getAndIncrement()).thenReturn(1L);
    assertEquals("MQ", shortenerService.getShortUrl("http://www.google.com"));
  }

  @Test(expected = StumpyException.class)
  public void failWhenUrlNotValid() {
    shortenerService.getShortUrl("ht://www.google.com");
  }
}
