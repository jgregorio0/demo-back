package dev.jgregorio.demo.back.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

class HttpClientUtilsTest {

  @Test
  void convertSortToString_whenSortIsUnsorted_shouldReturnNull() {
    String result = HttpClientUtils.convertSortToString(Sort.unsorted());
    assertNull(result);
  }

  @Test
  void convertSortToString_whenSortHasSingleAscendingOrder_shouldReturnCorrectString() {
    Sort sort = Sort.by(Sort.Direction.ASC, "name");
    String result = HttpClientUtils.convertSortToString(sort);
    assertEquals("name,asc", result);
  }

  @Test
  void convertSortToString_whenSortHasSingleDescendingOrder_shouldReturnCorrectString() {
    Sort sort = Sort.by(Sort.Direction.DESC, "age");
    String result = HttpClientUtils.convertSortToString(sort);
    assertEquals("age,desc", result);
  }

  @Test
  void convertSortToString_whenSortHasMultipleOrders_shouldReturnCorrectString() {
    Sort sort = Sort.by(Sort.Order.asc("name"), Sort.Order.desc("age"));
    String result = HttpClientUtils.convertSortToString(sort);
    assertEquals("name,asc,age,desc", result);
  }

  @Test
  void constructor_shouldThrowException() throws NoSuchMethodException {
    Constructor<HttpClientUtils> constructor = HttpClientUtils.class.getDeclaredConstructor();
    assertTrue(java.lang.reflect.Modifier.isPrivate(constructor.getModifiers()));
    constructor.setAccessible(true);

    InvocationTargetException thrown =
        assertThrows(
            InvocationTargetException.class,
            constructor::newInstance,
            "Expected constructor to throw InvocationTargetException");
    assertEquals(IllegalStateException.class, thrown.getCause().getClass());
  }
}
