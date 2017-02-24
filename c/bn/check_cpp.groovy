// agent: Agent名称
// home : 工作目录

currentBuild.displayName = "#${currentBuild.number}(${agent})"

timestamps {
  node("${agent}") {
    if (isUnix()) {
      stage('版本检查C++(platform)') {
        catchError {
          build job: 'bn_check_cpp_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'platform']]
        }
      }

      stage('版本检查C++(necommon)') {
        catchError {
          build job: 'bn_check_cpp_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'necommon']]
        }
      }

      stage('版本检查C++(uca)') {
        catchError {
          build job: 'bn_check_cpp_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'uca']]
        }
      }

      stage('版本检查C++(naf)') {
        catchError {
          build job: 'bn_check_cpp_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'naf']]
        }
      }

      stage('版本检查C++(e2e)') {
        catchError {
          build job: 'bn_check_cpp_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'e2e']]
        }
      }

      stage('版本检查C++(sdh)') {
        catchError {
          build job: 'bn_check_cpp_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'sdh']]
        }
      }

      stage('版本检查C++(wdm)') {
        catchError {
          build job: 'bn_check_cpp_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'wdm']]
        }
      }
    }
  }
}
