package de.whiteo.rp.util;

import java.time.LocalDateTime;
import org.slf4j.LoggerFactory;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class DebugSingleton {

  private static volatile DebugSingleton instance;
  private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DebugSingleton.class);
  private boolean enable;

  private static DebugSingleton getInstance() {
    DebugSingleton localInstance = instance;
    if (localInstance == null) {
      synchronized (DebugSingleton.class) {
        localInstance = instance;
        if (localInstance == null) {
          instance = localInstance = new DebugSingleton();
        }
      }
    }
    return localInstance;
  }

  public static boolean isEnable() {
    DebugSingleton debugSingleton = DebugSingleton.getInstance();
    return debugSingleton.enable;
  }

  public static void setEnableTrue() {
    DebugSingleton debugSingleton = DebugSingleton.getInstance();
    debugSingleton.enable = true;
  }

  public static void runLogger(String txt) {
    LOGGER.info("-------------------------------------------------------------------------");
    LOGGER.info(txt);
    LOGGER.info(LocalDateTime.now().toString());
    LOGGER.info("-------------------------------------------------------------------------");
  }
}