package ua.lviv.iot.hiberlab.view;

import java.util.LinkedList;
import java.util.List;

public class Formatter {
  public static void formatTable(List<String> header, List<List<String>> body) {
    List<List<String>> allRows = new LinkedList<>(body);
    allRows.add(header);
    int[] length = getLengths(allRows);
    String format = generateFormat(length);
    String headerString = String.format(format, header.toArray());
    String divider = headerString.replaceAll("[^|]", "-").replace("|", "+");
    System.out.println(divider);
    System.out.println(headerString);
    System.out.println(divider);
    for (List<String> row : body) {
      System.out.format(format + "%n", row.toArray());
    }
    System.out.println(divider);
  }

  private static int[] getLengths(List<List<String>> rows) {
    int[] lengths = new int[rows.get(0).size()];
    for (List<String> row : rows) {
      int index = 0;
      for (String value : row) {
        lengths[index] = Math.max(lengths[index], value.length());
        index++;
      }
    }
    return lengths;
  }

  private static String generateFormat(int[] lengths) {
    StringBuilder format = new StringBuilder("|");
    for (int length : lengths) {
      format.append(" %-").append(length).append("s").append(" |");
    }
    return format.toString();
  }
}
