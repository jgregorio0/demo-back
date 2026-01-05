#!/bin/bash

# Script for initializing a domain files.
# Use: ./init.sh domain=<domain_name> table=<table_name> schema=<schema_name>
BASE_PATH="src/main/java/dev/jgregorio/demo/back"
PROJECT_ROOT="../../demo-back"

# --- Arguments ---
for ARGUMENT in "$@"
do
   KEY=$(echo $ARGUMENT | cut -f1 -d=)
   VALUE=$(echo $ARGUMENT | cut -f2 -d=)

   case "$KEY" in
           domain)     DOMAIN=${VALUE} ;;
           table)      TABLE=${VALUE} ;;
           schema)     SCHEMA=${VALUE} ;;
           *)
   esac
done

# --- Validations ---
if [ -z "$DOMAIN" ] || [ -z "$TABLE" ] || [ -z "$SCHEMA" ]; then
    echo "Error: Missing required parameters. Use: $0 domain=<nombre_dominio> table=<table> schema=<schema>"
    exit 1
fi

# --- Variables ---
DOMAIN_LOWER=$(echo "$DOMAIN" | tr '[:upper:]' '[:lower:]')
DOMAIN_CAPITAL="$(tr '[:lower:]' '[:upper:]' <<< ${DOMAIN_LOWER:0:1})${DOMAIN_LOWER:1}"
TEMPLATE_DIR=$(dirname "$0")

# --- Directories ---
echo "Creating directory structure for domain '$DOMAIN_LOWER'..."
mkdir -p "${PROJECT_ROOT}/${BASE_PATH}/domain/${DOMAIN_LOWER}"
mkdir -p "${PROJECT_ROOT}/${BASE_PATH}/application/${DOMAIN_LOWER}"
mkdir -p "${PROJECT_ROOT}/${BASE_PATH}/infrastructure/api/${DOMAIN_LOWER}/search"
mkdir -p "${PROJECT_ROOT}/${BASE_PATH}/infrastructure/persistence/${DOMAIN_LOWER}/search"
echo "Directory structure created."

# --- Render templates function ---
render_template() {
  TEMPLATE_FILE="$1"
  DESTINATION_FILE="$2"

  eval "cat <<EOF
$(<"$TEMPLATE_FILE")
EOF" > "$DESTINATION_FILE"
}

# --- Files generation ---

echo "Creating resources..."

# 1. Domain: ${DOMAIN_CAPITAL}.java
render_template "${TEMPLATE_DIR}/domain.java.tpl" "${PROJECT_ROOT}/${BASE_PATH}/domain/${DOMAIN_LOWER}/${DOMAIN_CAPITAL}.java"

# 2. Domain: ${DOMAIN_CAPITAL}Search.java
render_template "${TEMPLATE_DIR}/domain-search.java.tpl" "${PROJECT_ROOT}/${BASE_PATH}/domain/${DOMAIN_LOWER}/${DOMAIN_CAPITAL}Search.java"

# 3. Application: ${DOMAIN_CAPITAL}SearchPersistence.java
render_template "${TEMPLATE_DIR}/application-search-persistence.java.tpl" "${PROJECT_ROOT}/${BASE_PATH}/application/${DOMAIN_LOWER}/${DOMAIN_CAPITAL}SearchPersistence.java"

# 4. Application: ${DOMAIN_CAPITAL}Service.java
render_template "${TEMPLATE_DIR}/application-service.java.tpl" "${PROJECT_ROOT}/${BASE_PATH}/application/${DOMAIN_LOWER}/${DOMAIN_CAPITAL}Service.java"

# 5. Infrastructure API: ${DOMAIN_CAPITAL}Controller.java
render_template "${TEMPLATE_DIR}/infrastructure-api-controller.java.tpl" "${PROJECT_ROOT}/${BASE_PATH}/infrastructure/api/${DOMAIN_LOWER}/${DOMAIN_CAPITAL}Controller.java"

# 6. Infrastructure API Search: ${DOMAIN_CAPITAL}SearchRequest.java
render_template "${TEMPLATE_DIR}/infrastructure-api-search-request.java.tpl" "${PROJECT_ROOT}/${BASE_PATH}/infrastructure/api/${DOMAIN_LOWER}/search/${DOMAIN_CAPITAL}SearchRequest.java"

# 7. Infrastructure API Search: ${DOMAIN_CAPITAL}SearchResponse.java
render_template "${TEMPLATE_DIR}/infrastructure-api-search-response.java.tpl" "${PROJECT_ROOT}/${BASE_PATH}/infrastructure/api/${DOMAIN_LOWER}/search/${DOMAIN_CAPITAL}SearchResponse.java"

# 8. Infrastructure API Search: ${DOMAIN_CAPITAL}SearchApiMapper.java
render_template "${TEMPLATE_DIR}/infrastructure-api-search-mapper.java.tpl" "${PROJECT_ROOT}/${BASE_PATH}/infrastructure/api/${DOMAIN_LOWER}/search/${DOMAIN_CAPITAL}SearchApiMapper.java"

# 9. Infrastructure Persistence: ${DOMAIN_CAPITAL}Entity.java
render_template "${TEMPLATE_DIR}/infrastructure-persistence-entity.java.tpl" "${PROJECT_ROOT}/${BASE_PATH}/infrastructure/persistence/${DOMAIN_LOWER}/${DOMAIN_CAPITAL}Entity.java"

# 10. Infrastructure Persistence: ${DOMAIN_CAPITAL}Specification.java
render_template "${TEMPLATE_DIR}/infrastructure-persistence-specification.java.tpl" "${PROJECT_ROOT}/${BASE_PATH}/infrastructure/persistence/${DOMAIN_LOWER}/${DOMAIN_CAPITAL}Specification.java"

# 11. Infrastructure Persistence Search: ${DOMAIN_CAPITAL}SearchEntityJpaRepository.java
render_template "${TEMPLATE_DIR}/infrastructure-persistence-search-repository.java.tpl" "${PROJECT_ROOT}/${BASE_PATH}/infrastructure/persistence/${DOMAIN_LOWER}/search/${DOMAIN_CAPITAL}SearchEntityJpaRepository.java"

# 12. Infrastructure Persistence Search: ${DOMAIN_CAPITAL}SearchJpaPersistence.java
render_template "${TEMPLATE_DIR}/infrastructure-persistence-search-persistence.java.tpl" "${PROJECT_ROOT}/${BASE_PATH}/infrastructure/persistence/${DOMAIN_LOWER}/search/${DOMAIN_CAPITAL}SearchJpaPersistence.java"

# 13. Infrastructure Persistence Search: ${DOMAIN_CAPITAL}SearchPersistenceMapper.java
render_template "${TEMPLATE_DIR}/infrastructure-persistence-search-mapper.java.tpl" "${PROJECT_ROOT}/${BASE_PATH}/infrastructure/persistence/${DOMAIN_LOWER}/search/${DOMAIN_CAPITAL}SearchPersistenceMapper.java"

echo "¡Success! 13 resources have been created for domain '${DOMAIN_LOWER}'."
echo "TODO: Review resources marked with 'TODO'. Adjustments are required for entity, search parameters...)."
