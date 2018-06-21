package org.orisonchan.sqlexporter.service;

public interface QueryService {

  void setDirectory(String directory);

  void setInputFile(String path);

  void querySQL();


}
