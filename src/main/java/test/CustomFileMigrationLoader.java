package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.SequenceInputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.Vector;

import org.apache.commons.io.comparator.NameFileComparator;
import org.apache.ibatis.migration.FileMigrationLoader;
import org.apache.ibatis.migration.MigrationReader;

public class CustomFileMigrationLoader extends FileMigrationLoader {
  public CustomFileMigrationLoader(File scriptsDir, String charset, Properties variables) {
    super(scriptsDir, charset, variables);
  }

  @Override
  public Reader getBootstrapReader() {
    File dir = new File(scriptsDir, "bootstrap");
    if (dir.exists() && dir.isDirectory()) {
      try {
        Vector<InputStream> vector = new Vector<>();
        File[] files = dir.listFiles(f -> f.getName().endsWith(".sql"));
        Arrays.sort(files, NameFileComparator.NAME_COMPARATOR);
        for (File file : files) {
          vector.add(new FileInputStream(file));
        }
        return new MigrationReader(new SequenceInputStream(vector.elements()), charset, false, variables);
      } catch (IOException e) {
        // TODO:
      }
    }
    return super.getBootstrapReader();
  }
}
