# CDOC2 OpenAPI

Contains OpenAPI specifications.

## Java

Openapi specification maven artifacts can be installed (local `~/.m2` directory) or deployed 
(remote maven package repository) with standard `mvn install` or `mvn deploy` commands. 

### Get from GitHub package repo

Configure github package repo access 
https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry#authenticating-with-a-personal-access-token

```bash
mvn dependency::get -Dartifact=ee.cyber.cdoc2.openapi:cdoc2-key-capsules-openapi:2.1.0:yaml
```

Optionally specifying 
`-DremoteRepositories=github::::https://maven.pkg.github.com/open-eid/cdoc2-openapi`

Copy:
```bash
mvn dependency::copy -Dartifact=ee.cyber.cdoc2.openapi:cdoc2-key-capsules-openapi:2.0.0:yaml -DoutputDirectory=./target/openapi
```


## Usage from non-Java projects

This repo contains only latest version of openapi specifications. Versioned openapi specifications 
can be found in maven package repository:

### Browser

https://github.com/open-eid/cdoc2-openapi/packages

## Releasing/Publishing OpenApi specification

Deploy/publish OpenApi manually:
`mvn -Dproject.distributionManagement.repository.id=github -Dproject.distributionManagement.repository.url=https://maven.pkg.github.com/open-eid/cdoc2-openapi deploy`

where `project.distributionManagement.repository.id` is <id> under <server> section of settings.xml . 
In most cases, this parameter will be required for authentication.

Or use maven deploy:deploy-file directly to deploy single file:
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
Refer: https://maven.apache.org/plugins/maven-deploy-plugin/deploy-file-mojo.html





