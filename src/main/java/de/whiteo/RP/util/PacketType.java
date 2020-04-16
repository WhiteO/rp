package de.whiteo.rp.util;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public enum PacketType {
  PACKET_COMMIT("DevDepot_commitObjects"),
  PACKET_CHANGE_VERSION("DevDepot_changeVersion");

  private String type;

  PacketType(String packetType) {
    this.type = packetType;
  }

  String value() {
    return type;
  }
}