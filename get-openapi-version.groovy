import org.yaml.snakeyaml.Yaml
println 'buildbasedir: ' + properties['buildbasedir']
def yaml = new Yaml()
def keyCapsuleOpenapi = yaml.load(new File(properties['buildbasedir'] + File.separator + 'cdoc2-key-capsules-openapi.yaml').text)
println "cdoc2-key-capsules-openapi.version: ${keyCapsuleOpenapi.info.version}"
project.getProperties().setProperty('cdoc2-key-capsules-openapi.version', keyCapsuleOpenapi.info.version)

def keySharesOpenapi = yaml.load(new File(properties['buildbasedir'] + File.separator + 'cdoc2-key-shares-openapi.yaml').text)
println "cdoc2-key-shares-openapi.version: ${keySharesOpenapi.info.version}"
project.getProperties().setProperty('cdoc2-key-shares-openapi.version', keySharesOpenapi.info.version)