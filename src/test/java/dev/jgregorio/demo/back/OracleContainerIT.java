package dev.jgregorio.demo.back;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

@Disabled // disabled test using testcontainers
@Tag("excluded-pitest")
@Tag("oracle")
@ActiveProfiles("test-oracle")
@Testcontainers
public abstract class OracleContainerIT {

  private static final String ORACLE_IMAGE = "gvenzl/oracle-xe:21-slim-faststart";
  private static final String DB_NAME = "TESTDB";
  private static final String DB_USER = "TEST";
  private static final String DB_PASS = "TEST_PASS";
  private static final String DB_SCRIPT_SCHEMA = "schema-oracle.sql";
  private static final String DB_ENTRYPOINT_INIT = "/container-entrypoint-initdb.d/init.sql";
  private static OracleContainer oracleContainer;

  @BeforeAll
  static void initializeDatabase() {
    oracleContainer =
        new OracleContainer(ORACLE_IMAGE)
            .withDatabaseName(DB_NAME)
            .withUsername(DB_USER)
            .withPassword(DB_PASS)
            .withCopyFileToContainer(
                MountableFile.forClasspathResource(DB_SCRIPT_SCHEMA), DB_ENTRYPOINT_INIT);
    oracleContainer.start();
  }

  @DynamicPropertySource
  static void oracleProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", oracleContainer::getJdbcUrl);
    registry.add("spring.datasource.username", oracleContainer::getUsername);
    registry.add("spring.datasource.password", oracleContainer::getPassword);
  }
}
