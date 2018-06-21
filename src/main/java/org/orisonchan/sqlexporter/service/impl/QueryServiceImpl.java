package org.orisonchan.sqlexporter.service.impl;

import org.orisonchan.sqlexporter.dao.CsvDAO;
import org.orisonchan.sqlexporter.dao.FileDAO;
import org.orisonchan.sqlexporter.dao.MssqlDAO;
import org.orisonchan.sqlexporter.exception.NotInitializingException;
import org.orisonchan.sqlexporter.service.QueryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service("queryService")
public class QueryServiceImpl implements QueryService {

  @Resource
  private MssqlDAO mssqlDAO;

  @Resource
  private CsvDAO csvDAO;

  private String directory;

  private String filename;

  @Resource
  private FileDAO fileDAO;

  @Override
  public void querySQL() {
    fileDAO.setFileName(filename);
    StringBuilder stringBuilder = new StringBuilder();
    try {
      String str;
      int i = 1;
      do {
        str = fileDAO.readLine();
        if (str != null) {
          stringBuilder.append(str);
          if (str.indexOf(';') != -1) {
            List<Map<String, Object>> list = mssqlDAO.query(stringBuilder.toString());
            System.out.println("--------sql查出:" + list.size() + "条数据--------");
            csvDAO.writeToCSV(list, directory + "/sql" + i + ".csv");
            i++;
            stringBuilder = new StringBuilder();
          }
        }
      } while (str != null);
    } catch (NotInitializingException | IOException e) {
      e.printStackTrace();
    } finally {
      fileDAO.close();
    }
  }

  public void setDirectory(String directory) {
    this.directory = directory;
  }

  @Override
  public void setInputFile(String path) {
    filename = path;
  }
}
