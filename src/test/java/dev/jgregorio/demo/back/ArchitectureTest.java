package dev.jgregorio.demo.back;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

class ArchitectureTest {

  private static final String BASE_PACKAGE = "dev.jgregorio.demo.back";

  // Layer definitions
  private static final String DOMAIN_LAYER_PACKAGES = BASE_PACKAGE + ".domain..";
  private static final String APPLICATION_LAYER_PACKAGES = BASE_PACKAGE + ".application..";
  private static final String INFRASTRUCTURE_LAYER_PACKAGES = BASE_PACKAGE + ".infrastructure..";

  // Layer names
  private static final String DOMAIN_LAYER = "Domain";
  private static final String APPLICATION_LAYER = "Application";
  private static final String INFRASTRUCTURE_LAYER = "Infrastructure";

  private static final Logger log = LoggerFactory.getLogger(ArchitectureTest.class);

  private JavaClasses importedClasses;

  @BeforeEach
  void setUp() {
    importedClasses =
        new ClassFileImporter()
            .withImportOption(new ImportOption.DoNotIncludeTests())
            .importPackages(BASE_PACKAGE);

    log.info("Imported classes: {} ", importedClasses.size());
    importedClasses.forEach(c -> log.debug("-> {}", c.getName()));
  }

  @Nested
  @DisplayName("Layer Dependencies Tests")
  class LayerDependenciesTests {

    @Test
    @DisplayName("Verify layer dependencies are respected")
    void layerDependenciesAreRespected() {
      Architectures.LayeredArchitecture arch =
          layeredArchitecture()
              .consideringAllDependencies()
              .layer(DOMAIN_LAYER)
              .definedBy(DOMAIN_LAYER_PACKAGES)
              .layer(APPLICATION_LAYER)
              .definedBy(APPLICATION_LAYER_PACKAGES)
              .layer(INFRASTRUCTURE_LAYER)
              .definedBy(INFRASTRUCTURE_LAYER_PACKAGES)
              .whereLayer(DOMAIN_LAYER)
              .mayOnlyBeAccessedByLayers(APPLICATION_LAYER, INFRASTRUCTURE_LAYER)
              .whereLayer(APPLICATION_LAYER)
              .mayOnlyBeAccessedByLayers(INFRASTRUCTURE_LAYER);

      arch.check(importedClasses);
    }

    @Test
    @DisplayName("Domain layer should not depend on any framework")
    void domainShouldNotDependOnFrameworks() {
      ArchRule rule =
          classes()
              .that()
              .resideInAPackage(DOMAIN_LAYER_PACKAGES)
              .should()
              .dependOnClassesThat()
              .resideInAnyPackage(DOMAIN_LAYER_PACKAGES, "java..", "lombok..");

      rule.check(importedClasses);
    }

    @Test
    @DisplayName("Domain layer should not have external dependencies")
    void domainShouldNotHaveExternalDependencies() {
      ArchRule rule =
          noClasses()
              .that()
              .resideInAPackage(DOMAIN_LAYER_PACKAGES)
              .should()
              .dependOnClassesThat()
              .resideInAnyPackage(APPLICATION_LAYER_PACKAGES, INFRASTRUCTURE_LAYER_PACKAGES);

      rule.check(importedClasses);
    }

    @Test
    @DisplayName("Application layer should not depend on Infrastructure")
    void applicationShouldNotDependOnInfrastructure() {
      ArchRule rule =
          noClasses()
              .that()
              .resideInAPackage(APPLICATION_LAYER_PACKAGES)
              .should()
              .dependOnClassesThat()
              .resideInAPackage(INFRASTRUCTURE_LAYER_PACKAGES);

      rule.check(importedClasses);
    }
  }

  @Nested
  @DisplayName("Naming Convention Tests")
  class NamingConventionTests {

    // Naming convention suffixes
    private static final String CONTROLLER_SUFFIX = "Controller";
    private static final String SERVICE_SUFFIX = "Service";
    private static final String PERSISTENCE_PORT_SUFFIX = "PersistencePort";
    private static final String PERSISTENCE_ADAPTER_SUFFIX = "PersistenceAdapter";
    private static final String REPOSITORY_SUFFIX = "PersistenceRepository";
    private static final String ENTITY_SUFFIX = "Entity";
    private static final String MAPPER_SUFFIX = "Mapper";
    private static final String CONFIG_SUFFIX = "Config";
    private static final String PROPERTIES_SUFFIX = "Properties";
    private static final String VALIDATOR_SUFFIX = "Validator";
    private static final String UTILS_SUFFIX = "Utils";
    private static final String GENERIC_PREFIX = "Generic";

    // Infrastructure internal layer definitions
    private static final String API_LAYER_PACKAGES = BASE_PACKAGE + ".infrastructure.api..";
    private static final String PERSISTENCE_LAYER_PACKAGES =
        BASE_PACKAGE + ".infrastructure.persistence..";

    // Other packages
    private static final String UTILS_PACKAGES = BASE_PACKAGE + ".utils..";

    @Test
    @DisplayName("Services should reside in application layer and have 'Service' suffix")
    void servicesShouldBeInApplicationAndHaveSuffix() {
      ArchRule rule =
          classes()
              .that()
              .areAnnotatedWith(Service.class)
              .should()
              .resideInAPackage(APPLICATION_LAYER_PACKAGES)
              .andShould()
              .haveSimpleNameEndingWith(SERVICE_SUFFIX);

      rule.check(importedClasses);
    }

    @Test
    @DisplayName("Transactional classes should be services")
    void transactionalClassesShouldBeServices() {
      ArchRule rule =
          classes()
              .that()
              .areAnnotatedWith(Transactional.class)
              .should()
              .beAnnotatedWith(Service.class)
              .andShould()
              .resideInAPackage(APPLICATION_LAYER_PACKAGES)
              .andShould()
              .haveSimpleNameEndingWith(SERVICE_SUFFIX);

      rule.allowEmptyShould(true).check(importedClasses);
    }

    @Test
    @DisplayName("Transactional methods should reside in services")
    void transactionalMethodsShouldBeInService() {
      ArchRule rule =
          methods()
              .that()
              .areAnnotatedWith(Transactional.class)
              .should()
              .beDeclaredInClassesThat()
              .areAnnotatedWith(Service.class);

      rule.allowEmptyShould(true).check(importedClasses);
    }

    @Test
    @DisplayName("Validators should reside in application or domain and have suffix 'Validator'")
    void validatorsShouldBeInApplicationOrDomain() {
      ArchRule rule =
          classes()
              .that()
              .haveSimpleNameEndingWith(VALIDATOR_SUFFIX)
              .should()
              .resideInAnyPackage(APPLICATION_LAYER_PACKAGES, DOMAIN_LAYER_PACKAGES);

      rule.allowEmptyShould(true).check(importedClasses);
    }

    @Test
    @DisplayName("Controllers should reside in infrastructure.api and have 'Controller' suffix")
    void controllersShouldBeInInfrastructureAndHaveSuffix() {
      ArchRule rule =
          classes()
              .that()
              .areAnnotatedWith(RestController.class)
              .should()
              .resideInAPackage(API_LAYER_PACKAGES)
              .andShould()
              .haveSimpleNameEndingWith(CONTROLLER_SUFFIX);

      rule.check(importedClasses);
    }

    @Test
    @DisplayName("DTOs should reside in infrastructure.api and have correct suffix")
    void dtosShouldBeInApplicationAndHaveCorrectSuffix() {
      ArchRule rule =
          classes()
              .that()
              .areRecords()
              .and()
              .resideInAPackage(API_LAYER_PACKAGES)
              .should()
              .haveSimpleNameEndingWith("Request")
              .orShould()
              .haveSimpleNameEndingWith("Response")
              .orShould()
              .haveSimpleNameEndingWith("Creation")
              .orShould()
              .haveSimpleNameEndingWith("Update")
              .orShould()
              .haveSimpleNameEndingWith("Patch")
              .orShould()
              .haveSimpleNameEndingWith("Deletion")
              .orShould()
              .haveSimpleNameEndingWith("Search");

      rule.allowEmptyShould(true).check(importedClasses);
    }

    @Test
    @DisplayName(
        "Persistence ports should reside in application layer and have 'Persistence' suffix")
    void persistencePort_shouldResideInApplicationLayer() {
      ArchRule rule =
          classes()
              .that()
              .areInterfaces()
              .and()
              .haveSimpleNameEndingWith(PERSISTENCE_PORT_SUFFIX)
              .and()
              .areNotAssignableTo(JpaRepository.class)
              .should()
              .notBeAnnotatedWith(Repository.class)
              .andShould()
              .resideInAPackage(APPLICATION_LAYER_PACKAGES);

      rule.check(importedClasses);
    }

    @Test
    @DisplayName(
        "Persistence adapters should reside in infrastructure.persistence and have 'PersistenceAdapter' suffix")
    void persistenceAdapters_shouldResideInInfrastructureLayer() {
      ArchRule rule =
          classes()
              .that()
              .areNotInterfaces()
              .and()
              .haveSimpleNameNotStartingWith(GENERIC_PREFIX)
              .and()
              .haveSimpleNameEndingWith(PERSISTENCE_ADAPTER_SUFFIX)
              .should()
              .beAnnotatedWith(Component.class)
              .andShould()
              .resideInAPackage(PERSISTENCE_LAYER_PACKAGES);

      rule.check(importedClasses);
    }

    @Test
    @DisplayName("JPA Repository interfaces should reside in infrastructure.persistence")
    void jpaRepositoryInterfacesShouldBeInPersistenceAndHaveSuffix() {
      ArchRule rule =
          classes()
              .that()
              .areInterfaces()
              .and()
              .areAssignableTo(JpaRepository.class)
              .should()
              .notBeAnnotatedWith(Repository.class)
              .andShould()
              .haveSimpleNameEndingWith(REPOSITORY_SUFFIX)
              .andShould()
              .resideInAPackage(PERSISTENCE_LAYER_PACKAGES);

      rule.check(importedClasses);
    }

    @Test
    @DisplayName(
        "JPA Entities should reside in infrastructure.persistence and should have 'Entity' suffix")
    void entitiesShouldBeInPersistenceAndHaveSuffix() {
      ArchRule rule =
          classes()
              .that()
              .areAnnotatedWith(jakarta.persistence.Entity.class)
              .should()
              .resideInAPackage(PERSISTENCE_LAYER_PACKAGES)
              .andShould()
              .haveSimpleNameEndingWith(ENTITY_SUFFIX);

      rule.check(importedClasses);
    }

    @Test
    @DisplayName("Mappers should reside in infrastructure packages and should have 'Mapper' suffix")
    void mappersShouldHaveSuffix() {
      ArchRule rule =
          classes()
              .that()
              .areInterfaces()
              .and()
              .areAnnotatedWith(org.mapstruct.Mapper.class)
              .and()
              .resideInAnyPackage(INFRASTRUCTURE_LAYER_PACKAGES)
              .should()
              .haveSimpleNameEndingWith(MAPPER_SUFFIX);

      rule.check(importedClasses);
    }

    @Test
    @DisplayName(
        "Configuration classes should reside in infrastructure and should have suffix 'Config'")
    void configurationsShouldBeInInfrastructureAndHaveSuffix() {
      ArchRule rule =
          classes()
              .that()
              .areAnnotatedWith(Configuration.class)
              .should()
              .resideInAPackage(INFRASTRUCTURE_LAYER_PACKAGES)
              .andShould()
              .haveSimpleNameEndingWith(CONFIG_SUFFIX);

      rule.check(importedClasses);
    }

    @Test
    @DisplayName(
        "Properties classes should reside in infrastructure and should have suffix 'Properties'")
    void propertiesShouldBeInInfrastructureAndHaveSuffix() {
      ArchRule rule =
          classes()
              .that()
              .areAnnotatedWith(ConfigurationProperties.class)
              .should()
              .resideInAPackage(INFRASTRUCTURE_LAYER_PACKAGES)
              .andShould()
              .haveSimpleNameEndingWith(PROPERTIES_SUFFIX);

      rule.allowEmptyShould(true).check(importedClasses);
    }

    @Test
    @DisplayName("Utility classes should reside in utils and should have suffix 'Utils'")
    void utilitiesShouldBeInUtils() {
      ArchRule rule =
          classes()
              .that()
              .haveSimpleNameEndingWith(UTILS_SUFFIX)
              .should()
              .resideInAPackage(UTILS_PACKAGES);

      rule.allowEmptyShould(true).check(importedClasses);
    }
  }
}
