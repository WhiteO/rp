package de.whiteo.rp.repository;

import de.whiteo.rp.model.Logger;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public interface LoggerRepository extends JpaRepository<Logger, Long> {

  @Query(value =
      "select distinct L.ID, L.ERROR_DATE ERROR_DATE, L.ERROR_STRING ERROR_STRING"
      +
      " from LOGGERS L", nativeQuery = true)
  Set<Logger> getAllLoggers();

  @Query(value = "select COUNT(*) from LOGGERS", nativeQuery = true)
  Integer getLoggersCount();
}