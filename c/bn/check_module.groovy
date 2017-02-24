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
            "${shell}" "${rake} bn:check:check_xml[${name}] --trace"
          }
        }
      }
    }

    stage("版本检查(文件长度:${name})") {
      catchError {
        timeout(30) {
          dir("${home}") {
            "${shell}" "${rake} bn:check:check[${name}] --trace"
          }
        }
      }
    }

    if ("${agent}" == "linux") {
      stage("版本检查(findbugs:${name})") {
        catchError {
          timeout(300) {
            withEnv(["WORKSPACE=${pwd()}"]) {
              dir("${home}") {
                "${shell}" "${rake} bn:compile:mvn[${name},\"mvn findbugs:findbugs -fn -U\",false,false,nil,nil] --trace"
              }
            }
          }
        }
      }

      stage("分析检查结果(findbugs:${name})") {
        catchError {
          timeout(120) {
            step([$class: 'FindBugsPublisher', pattern: '${BUILD_NUMBER}/**/findbugsXml.xml', canComputeNew: true, canRunOnFailed: true])
          }
        }
      }
    }
  }
}
