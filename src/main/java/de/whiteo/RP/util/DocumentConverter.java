package de.whiteo.rp.util;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class DocumentConverter {

  public static Document stringXmlToDocumentConvert(String stringXml) {
    Document document = null;
    try {
      String preparedString = prepareXmlString(stringXml);
      DocumentBuilder df = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      document = df.parse(new InputSource(new StringReader(preparedString)));
    } catch (IOException | ParserConfigurationException | SAXException e) {
      e.printStackTrace();
    }
    return document;
  }

  private static String prepareXmlString(String stringXml) {
    return new String(stringXml.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
  }
}