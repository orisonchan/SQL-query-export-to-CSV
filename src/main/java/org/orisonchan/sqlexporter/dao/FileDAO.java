package org.orisonchan.sqlexporter.dao;

import org.orisonchan.sqlexporter.exception.NotInitializingException;
import org.springframework.stereotype.Repository;

import java.io.*;

@Repository("fileDAO")
public class FileDAO {

  private String fileName;

  private File file;

  private FileReader reader;

  private BufferedReader br;

  private boolean fileflag;

  public void setFileName(String fileName) {
    this.fileName = fileName;
    this.file = null;
    this.reader = null;
    this.br = null;
    this.fileflag = false;
    init(fileName);
  }

  private void init(String fileName) {
    file = new File(fileName);
    try {
      reader = new FileReader(fileName);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    br = new BufferedReader(reader);
  }

  private void checkFile() throws NotInitializingException, FileNotFoundException {
    if (fileName == null || file == null)
      throw new NotInitializingException("not initializing file!");
    if (!fileflag) {
      if (!file.exists())
        throw new FileNotFoundException("Input file does not exists!");
      else
        fileflag = true;
    }

  }

  public String readLine() throws NotInitializingException, IOException {
    checkFile();
    return br.readLine();
  }

  public void close() {
    try {
      if (reader != null)
        reader.close();
      if (br != null)
        br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
