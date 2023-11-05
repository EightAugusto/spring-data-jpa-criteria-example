package com.eightaugusto.spring.data.jpa.criteria.example.common.factory;

import com.eightaugusto.spring.data.jpa.criteria.example.common.config.ObjectMapperConfig;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

/** CommonFactory. */
@UtilityClass
public final class CommonFactory {

  private static final String RESOURCE_FACTORY_PATH_BY_CLASS = "./factory/%s.json";

  /**
   * Read and parse from specific resource file based on <code>Class</code> simple name.
   *
   * @param clazz Class.
   * @return Instance of class.
   * @param <T> Class generic.
   */
  public static <T> T readClassFromResource(Class<T> clazz) {
    return readFromResources(
        String.format(RESOURCE_FACTORY_PATH_BY_CLASS, clazz.getSimpleName()), clazz);
  }

  /**
   * Read the file from the provided path and parse into an instance of the provided <code>Class
   * </code>.
   *
   * @param path Path.
   * @param clazz Class.
   * @return Instance of class.
   * @param <T> Class generic.
   */
  @SneakyThrows
  private static <T> T readFromResources(String path, Class<T> clazz) {
    return ObjectMapperConfig.getObjectMapper()
        .readValue(CommonFactory.class.getClassLoader().getResourceAsStream(path), clazz);
  }
}
