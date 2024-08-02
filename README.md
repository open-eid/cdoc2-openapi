# CDOC2 OpenAPI

Contains OpenAPI specifications for [CDOC2 project](https://open-eid.github.io/CDOC2)

## Java

Openapi specification maven artifacts (`packaging=yaml`) can be installed (local `~/.m2` directory) or deployed 
(remote maven package repository) with standard `mvn install` or `mvn deploy` commands. 

`mvn install` will parse `info.version` from `*-openapi.yaml` and use that as maven package version. 

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

## Releasing/Publishing OpenApi using GitHub actions

Push a new version of *-openapi.yaml to GitHub.  

Create GitHub Release
https://docs.github.com/en/repositories/releasing-projects-on-github/managing-releases-in-a-repository#creating-a-release

That will trigger `.github/workflows/maven-publish.yml` that will call `mvn deploy` with correct parameters


## Releasing/Publishing OpenApi specification manually

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

## Delete OpenApi package from local Maven repository
```
mvn dependency:purge-local-repository -DmanualInclude=ee.cyber.cdoc2.openapi:cdoc2-key-capsules-openapi
```

## Usage from Java Maven projects for code generation

See:
* https://github.com/open-eid/cdoc2-java-ref-impl/blob/master/cdoc2-client/pom.xml
* https://github.com/open-eid/cdoc2-capsule-server/blob/master/cdoc2-server-openapi/pom.xml




