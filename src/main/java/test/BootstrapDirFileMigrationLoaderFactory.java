package test;

import java.io.File;
import java.util.Properties;

import org.apache.ibatis.migration.FileMigrationLoaderFactory;
import org.apache.ibatis.migration.MigrationLoader;

public class BootstrapDirFileMigrationLoaderFactory implements FileMigrationLoaderFactory {
  @Override
  public MigrationLoader create(File scriptsDir, String charset, Properties variables) {
    return new CustomFileMigrationLoader(scriptsDir, charset, variables);
  }
}
