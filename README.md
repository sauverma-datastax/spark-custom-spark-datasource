# spark-custom-spark-datasource
Extending Our Spark SQL Query Engine with a custom data source
as per https://developer.hpe.com/blog/spark-data-source-api-extending-our-spark-sql-query-engine/

## How to build
- ``./gradlew build``
- If you want to use locally built spark libs
  - Uncomment `flatDir` in `build.gradle` 
  - Replace `<spark base path>` with locally built spark path
