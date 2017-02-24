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
            "${shell}" "${rake} bn:check:check_xml_cpp[${name}] --trace"
          }
        }
      }
    }

    stage("版本检查(文件长度:${name})") {
      catchError {
        timeout(30) {
          dir("${home}") {
            "${shell}" "${rake} bn:check:check_cpp[${name}] --trace"
          }
        }
      }
    }

    if ("${agent}" == "linux") {
      stage("版本检查C++(cppcheck:${name})") {
        catchError {
          timeout(300) {
            withEnv(["WORKSPACE=${pwd()}/../bn_cppcheck/cppcheck"]) {
              dir("${home}") {
                "${shell}" "${rake} bn:compile:mvn_cpp[${name},\"mvn exec:exec -fn -U\",false,false,nil,nil] --trace"
              }
            }
          }
        }
      }

      stage("分析检查结果C++(cppcheck:${name})") {
        catchError {
          timeout(120) {
            build job: 'bn_cppcheck', parameters: [[$class: 'StringParameterValue', name: 'home', value: "cppcheck/${env.BUILD_NUMBER}"]]
          }
        }
      }
    }
  }
}
