// agent          : Agent名称
// home           : 工作目录
// update         : 版本更新
// compile        : 版本编译
// force          : 全量编译

currentBuild.displayName = "#${currentBuild.number}(${agent})"

timestamps {
  def forceValue = true

  if ("${force}" == 'false') {
    forceValue = false
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
    stage('版本编译') {
      catchError {
        build job: 'bn_kloc_ignore', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"]]
      }

      parallel java: {
        catchError {
          build job: 'bn_compile', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: ""]]
        }
      }, cpp: {
        catchError {
          build job: 'bn_compile_cpp', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: ""]]
        }
      },
      failFast: false
    }
  }
}
