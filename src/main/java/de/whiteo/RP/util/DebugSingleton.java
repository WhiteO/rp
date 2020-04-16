package de.whiteo.rp.util;

import de.whiteo.rp.model.Logger;
import java.time.LocalDateTime;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class DebugSingleton {

  private static volatile DebugSingleton instance;
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

  public static Logger saveLogger(String txt) {
    Logger logger = new Logger();
    logger.setErrorDate(LocalDateTime.now());
    logger.setErrorString(txt);
    return logger;
  }
}