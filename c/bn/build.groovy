// agent          : Agent名称
// home           : 工作目录
// update         : 版本更新
// compile        : 版本编译
// install        : 版本制作
// check          : 版本检查
// test           : 版本测试
// force          : 全量编译
// version        : 版本号
// display_version: 显示版本号
// type           : 版本类型

currentBuild.displayName = "#${currentBuild.number}(${agent})"

timestamps {
  def forceValue = true

  if ("${force}" == 'false') {
    forceValue = false
  }

  def nightbuild = false

  node("master") {
    stage('判断是否nightbuild构建') {
      def hour = 0

      catchError {
        sh "date +%H > current_hour"
        hour_string = readFile 'current_hour'

        try{
          hour = hour_string.toInteger()
        } catch (any) {
        }
      }

      if (hour >= 22 || hour <= 8) {
        nightbuild = true

        echo "是nightbuild构建"
      } else {
        echo "不是nightbuild构建"
      }
    }
  }

  currentBuild.result = 'SUCCESS'

  if ("${update}" == 'true') {
    stage('版本更新') {
      catchError {
        build job: 'bn_update', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"]]
      }
    }
  }

  if ("${compile}" == 'true') {
    stage('部署基础POM和第三方文件') {
      catchError {
        build job: 'bn_deploy', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
      }
    }

    stage('版本编译') {
      parallel java: {
        catchError {
          build job: 'bn_compile', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
        }
      }, cpp: {
        catchError {
          build job: 'bn_compile_cpp', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
        }
      },
      failFast: false
    }
  }

  if (currentBuild.result == 'SUCCESS') {
    if ("${install}" == 'true') {
      stage('版本制作') {
        catchError {
          build job: 'bn_install', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'version', value: "${version}"],[$class: 'StringParameterValue', name: 'display_version', value: "${display_version}"],[$class: 'StringParameterValue', name: 'type', value: "${type}"]]
        }
      }
    }
  }

  if (nightbuild) {
    if (currentBuild.result == 'SUCCESS') {
      stage('版本检查和版本测试') {
        parallel check: {
          if ("${check}" == 'true') {
            catchError {
              build job: 'bn_check', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"]]
            }

            catchError {
              build job: 'bn_check_cpp', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"]]
            }
          }
        }, test: {
          if ("${test}" == 'true') {
            catchError {
              build job: 'bn_test', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
            }
          }
        },
        failFast: false
      }
    }
  }
}
