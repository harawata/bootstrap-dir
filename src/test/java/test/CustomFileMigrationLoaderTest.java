package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.junit.Test;

public class CustomFileMigrationLoaderTest {
  @Test
  public void shouldLoadScriptsInSubDirectories() throws Exception {
    File scriptDir = Resources.getResourceAsFile("case1");
    CustomFileMigrationLoader migrationLoader = new CustomFileMigrationLoader(scriptDir, "utf-8", null);
    String content = readAsString(migrationLoader.getBootstrapReader());
    assertEquals("A\nB\nC\n", content);
  }

  private String readAsString(Reader reader) throws IOException {
    try {
      StringBuilder buffer = new StringBuilder();
      int res;
      while ((res = reader.read()) != -1) {
        buffer.append((char) res);
      }
      return buffer.toString();
    } finally {
      reader.close();
    }
  }
}
