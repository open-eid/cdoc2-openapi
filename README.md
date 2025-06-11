# CDOC2 OpenAPI

Contains OpenAPI specifications for [CDOC2 project](https://open-eid.github.io/CDOC2)

Used for code generation by:
* https://github.com/open-eid/cdoc2-java-ref-impl
* https://github.com/open-eid/cdoc2-capsule-server
* https://github.com/open-eid/cdoc2-shares-server 

## Java

Openapi specification maven artifacts (`packaging=yaml`) can be installed (local `~/.m2` directory) or deployed 
(remote maven package repository) with standard `mvn install` or `mvn deploy` commands. 

`mvn install` will parse `info.version` from `*-openapi.yaml` and use that as maven package version. 

### Get from GitHub package repo

Configure github package repo access 
https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry#authenticating-with-a-personal-access-token

Example `<profile>` section of `settings.xml` for using `cdoc2-openapi`:
```xml
  <profile>
      <id>github</id>
      <repositories>
        <repository>
          <id>central</id>
          <url>https://repo1.maven.org/maven2</url>
        </repository>
        <repository>
          <id>github</id>
          <url>https://maven.pkg.github.com/open-eid/cdoc2-openapi</url>
        </repository>
      </repositories>
  </profile>
```

Note: When pulling, the package index is based on the organization level, not the repository level.
https://stackoverflow.com/questions/63041402/github-packages-single-maven-repository-for-github-organization

So defining single Maven package repo from `open-eid` is enough for pulling cdoc2-* dependencies.


Test that `settings.xml` is properly configured:
```bash
mvn dependency::get -Dartifact=ee.cyber.cdoc2.openapi:cdoc2-key-capsules-openapi:2.2.0:yaml
```

Optionally specifying 
`-DremoteRepositories=github::::https://maven.pkg.github.com/open-eid/cdoc2-openapi`

Or from Maven pom.xml:

```xml
        <dependency>
            <groupId>ee.cyber.cdoc2.openapi</groupId>
            <artifactId>cdoc2-key-capsules-openapi</artifactId>
            <version>2.2.0</version>
            <type>yaml</type>
        </dependency>
        <dependency>
            <groupId>ee.cyber.cdoc2.openapi</groupId>
            <artifactId>cdoc2-key-shares-openapi</artifactId>
            <version>1.0.0</version>
        <type>yaml</type>
        </dependency>
```

Copy into project directory:
```bash
mvn dependency::copy -Dartifact=ee.cyber.cdoc2.openapi:cdoc2-key-capsules-openapi:2.2.0:yaml -DoutputDirectory=./target/openapi
mvn dependency::copy -Dartifact=ee.cyber.cdoc2.openapi:cdoc2-key-shares-openapi:1.0.0:yaml -DoutputDirectory=./target/openapi
```

### Usage from Java Maven projects for code generation

See:
* https://github.com/open-eid/cdoc2-java-ref-impl/blob/master/cdoc2-client/pom.xml
* https://github.com/open-eid/cdoc2-capsule-server/blob/master/cdoc2-server-openapi/pom.xml



## Usage from non-Java projects

This repo contains only latest version of openapi specifications. Versioned openapi specifications 
can be found in maven package repository:

### Browser

https://github.com/orgs/open-eid/packages

## Releasing/Publishing OpenApi using GitHub actions

Push a new version of *-openapi.yaml to GitHub.  

Create GitHub Release
https://docs.github.com/en/repositories/releasing-projects-on-github/managing-releases-in-a-repository#creating-a-release

That will trigger `.github/workflows/maven-publish.yml` that will call `mvn deploy` with correct parameters


## Releasing/Publishing OpenApi specification manually

Deploy/publish OpenApi manually:
`mvn -Dproject.distributionManagement.repository.id=github -Dproject.distributionManagement.repository.url=https://maven.pkg.github.com/open-eid/cdoc2-openapi deploy`

where `project.distributionManagement.repository.id` is `<id>` under `<server>` section fo settings.xml . 
In most cases, this parameter will be required for authentication.

Or use maven deploy:deploy-file directly to deploy single file:

cdoc2-key-capsules:
```
mvn deploy:deploy-file \
-DrepositoryId=github \
-Durl=https://maven.pkg.github.com/open-eid/cdoc2-openapi \
-Dfile=cdoc2-openapi/cdoc2-key-capsules-openapi.yaml \
-Dversion=2.1.0 \
-Dpackaging=yaml \
-DgroupId=ee.cyber.cdoc2.openapi \
-DartifactId=cdoc2-key-capsules-openapi \
-Dmaven.deploy.file.skip=false
```

cdoc2-key-shares:
```
mvn deploy:deploy-file \
-DrepositoryId=github \
-Durl=https://maven.pkg.github.com/open-eid/cdoc2-openapi \
-Dfile=cdoc2-openapi/cdoc2-key-shares-openapi.yaml \
-Dversion=1.0.0 \
-Dpackaging=yaml \
-DgroupId=ee.cyber.cdoc2.openapi \
-DartifactId=cdoc2-key-shares-openapi \
-Dmaven.deploy.file.skip=false
```
Refer: https://maven.apache.org/plugins/maven-deploy-plugin/deploy-file-mojo.html

## Delete OpenApi package from local Maven repository
cdoc2-key-capsules:
```
mvn dependency:purge-local-repository -DmanualInclude=ee.cyber.cdoc2.openapi:cdoc2-key-capsules-openapi
```

cdoc2-key-shares:
```
mvn dependency:purge-local-repository -DmanualInclude=ee.cyber.cdoc2.openapi:cdoc2-key-shares-openapi
```





