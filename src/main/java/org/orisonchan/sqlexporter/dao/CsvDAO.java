package org.orisonchan.sqlexporter.dao;

import com.csvreader.CsvWriter;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@Repository("csvDAO")
public class CsvDAO {

  public void writeToCSV(List<Map<String, Object>> list, String path) {
    try {
      // 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);
      File file = new File(path);
      if(file.exists())
        file.delete();
      CsvWriter csvWriter = new CsvWriter(path, ',', Charset.forName("UTF-8"));
      Map<String, Object> map0 = list.get(0);
      int columnSize = map0.keySet().size();
      String[] header = new String[columnSize];
      int i = 0;
      for (Map.Entry<String, Object> entry : map0.entrySet()) {
        header[i] = entry.getKey();
        i++;
      }
      csvWriter.writeRecord(header);
      for (Map<String, Object> map : list) {
        String[] row = new String[columnSize];
        i = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
          row[i] = entry.getValue().toString();
          i++;
        }
        csvWriter.writeRecord(row);
      }
      csvWriter.close();
      System.out.println("--------" + path + "文件已经写入--------");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
