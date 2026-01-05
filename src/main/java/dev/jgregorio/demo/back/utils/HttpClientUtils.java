package dev.jgregorio.demo.back.utils;

import java.util.Locale;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;

public class HttpClientUtils {

  private HttpClientUtils() {
    throw new IllegalStateException("Class is not instantiable.");
  }

  public static String convertSortToString(Sort sort) {
    if (sort.isUnsorted()) {
      return null;
    }
    return sort.stream()
        .map(
            order ->
                String.join(
                    ",",
                    order.getProperty(),
                    order.getDirection().name().toLowerCase(Locale.ENGLISH)))
        .collect(Collectors.joining(","));
  }
}
