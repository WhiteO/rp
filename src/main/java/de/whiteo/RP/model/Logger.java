package de.whiteo.rp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

@Entity
@Table(name = "LOGGERS")
public class Logger implements Serializable {

  @JsonIgnore
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;
  @Column(name = "ERROR_DATE", columnDefinition = "DATETIME")
  private LocalDateTime errorDate;
  @Column(name = "ERROR_STRING", columnDefinition = "VARCHAR(255)")
  private String errorString;

  public Logger() {
  }

  public Long getId() {
    return id;
  }

  @JsonProperty("DATE")
  public LocalDateTime getErrorDate() {
    return errorDate;
  }

  public void setErrorDate(LocalDateTime errorDate) {
    this.errorDate = errorDate;
  }

  @JsonProperty("STRING")
  public String getErrorString() {
    return errorString;
  }

  public void setErrorString(String errorString) {
    this.errorString = errorString;
  }
}