package dev.jgregorio.demo.back.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.Test;

class DateTimeUtilsTest {

  @Test
  void testConstructorIsPrivateAndThrowsException() {
    assertThrows(
        IllegalStateException.class,
        () -> {
          try {
            Constructor<DateTimeUtils> constructor = DateTimeUtils.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
          } catch (InvocationTargetException e) {
            throw e.getTargetException();
          }
        });
  }

  @Test
  void toLocalDateTime_whenInstantIsNull_shouldReturnNull() {
    assertNull(DateTimeUtils.toLocalDateTime(null));
  }

  @Test
  void toLocalDateTime_whenInstantIsValid_shouldReturnCorrespondingLocalDateTime() {
    Instant now = Instant.now();
    assertEquals(
        LocalDateTime.ofInstant(now, ZoneId.systemDefault()), DateTimeUtils.toLocalDateTime(now));
  }
}
