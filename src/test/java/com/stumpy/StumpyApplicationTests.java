package com.stumpy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:test.properties")
// @SpringBootTest
public class StumpyApplicationTests {

  @Test
  public void contextLoads() {}

}
