package de.whiteo.rp.tcp;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public enum PacketType {
  PACKET_COMMIT("DevDepot_commitObjects"),
  PACKET_CHANGE_VERSION("DevDepot_changeVersion"),
  PACKET_WITH_VERSIONS("crs:versions");

  private String type;

  PacketType(String packetType) {
    this.type = packetType;
  }

  String value() {
    return type;
  }
}