# NOTE: Spring Cloud 2022.0.0-M5 is not compatible with boot snapshots. Use 3.0.0-M5
## 1. Change Spring Boot version as 3.0.0-M5
```xml
<parent>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-parent</artifactId>
	<version>3.0.0-M5</version>
	<relativePath/> <!-- lookup parent from repository -->
</parent>
```
## 2. Change Java version as 17 and Spring Cloud Version as 2022.0.0-M5
```xml
<properties>
	<java.version>17</java.version>
	<spring-cloud.version>2022.0.0-M5</spring-cloud.version>
</properties>
```
## 3. Change repository and plugin repository tags as below
```xml
<repositories>
	<repository>
		<id>spring-milestones</id>
		<name>Spring Milestones</name>
		<url>https://repo.spring.io/milestone</url>
		<snapshots>
			<enabled>false</enabled>
		</snapshots>
	</repository>
	<repository>
		<id>netflix-candidates</id>
		<name>Netflix Candidates</name>
		<url>https://artifactory-oss.prod.netflix.net/artifactory/maven-oss-candidates</url>
		<snapshots>
			<enabled>false</enabled>
		</snapshots>
	</repository>
</repositories>

<pluginRepositories>
	<pluginRepository>
		<id>spring-milestones</id>
		<name>Spring Milestones</name>
		<url>https://repo.spring.io/milestone</url>
		<snapshots>
			<enabled>false</enabled>
		</snapshots>
	</pluginRepository>
</pluginRepositories>

```
## 4. For more details please refer to spring cloud release notes:
https://github.com/spring-cloud/spring-cloud-netflix/issues/4128

https://github.com/spring-cloud/spring-cloud-release/wiki/Spring-Cloud-2022.0-Release-Notes#202200-m5