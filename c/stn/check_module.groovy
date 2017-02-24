// agent: Agent名称
// home : 工作目录
// name : 模块名称

currentBuild.displayName = "#${currentBuild.number}(${name}-${agent})"

timestamps {
  node("${agent}") {
    def shell = 'bat'
    def rake = 'call rake'

    if (isUnix()) {
      shell = 'sh'
      rake = 'rake'
    }

    stage("版本检查(XML:${name})") {
      catchError {
        timeout(30) {
          dir("${home}") {
            "${shell}" "${rake} stn:check:check_xml[${name}] --trace"
          }
        }
      }
    }

    if ("${agent}" == "linux") {
      stage("版本检查(${name})") {
        catchError {
          timeout(30) {
            withEnv(["WORKSPACE=${pwd()}"]) {
              dir("${home}") {
                "${shell}" "${rake} stn:compile:mvn[${name},\"mvn findbugs:findbugs -fn -U\",false,false,nil,nil] --trace"
              }
            }
          }
        }
      }

      stage("分析检查结果(${name})") {
        catchError {
          timeout(30) {
            step([$class: 'FindBugsPublisher', pattern: '${BUILD_NUMBER}/**/findbugsXml.xml', canComputeNew: true, canRunOnFailed: true])
          }
        }
      }
    }
  }
}
