package com.stumpy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.junit.Test;

import com.stumpy.util.UrlGeneratorUtil;

public class UrlGeneratorUtilTest {

  @Test
  public void encodeShouldHandleOne() {
    assertEquals("MQ", UrlGeneratorUtil.encode(1L));
  }

  @Test
  public void decodeShouldHandleMQ() {
    assertEquals(Long.valueOf(1L), UrlGeneratorUtil.decode("MQ"));
  }

  @Test
  public void testConstructorIsPrivate()
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Constructor<UrlGeneratorUtil> constructor = UrlGeneratorUtil.class.getDeclaredConstructor();
    assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    constructor.setAccessible(true);
    constructor.newInstance();
  }

}
