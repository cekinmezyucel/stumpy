package com.stumpy.exception.util;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ExceptionUtilTest {

  @Test
  public void testConstructorIsPrivate()
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Constructor<ExceptionUtil> constructor = ExceptionUtil.class.getDeclaredConstructor();
    assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    constructor.setAccessible(true);
    constructor.newInstance();
  }

}
