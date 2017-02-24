// agent: Agent名称
// home : 工作目录

currentBuild.displayName = "#${currentBuild.number}(${agent})"

timestamps {
  node("${agent}") {
    if (isUnix()) {
      stage('kloc检查C++(platform)') {
        catchError {
          build job: 'bn_kloc_cpp_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'platform']]
        }
      }

      stage('kloc检查C++(necommon)') {
        catchError {
          build job: 'bn_kloc_cpp_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'necommon']]
        }
      }

      stage('kloc检查C++(uca)') {
        catchError {
          build job: 'bn_kloc_cpp_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'uca']]
        }
      }

      stage('kloc检查C++(naf)') {
        catchError {
          build job: 'bn_kloc_cpp_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'naf']]
        }
      }

      stage('kloc检查C++(e2e)') {
        catchError {
          build job: 'bn_kloc_cpp_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'e2e']]
        }
      }

      stage('kloc检查C++(sdh)') {
        catchError {
          build job: 'bn_kloc_cpp_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'sdh']]
        }
      }

      stage('kloc检查C++(wdm1)') {
        catchError {
          build job: 'bn_kloc_cpp_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'wdm_1']]
        }
      }

      stage('kloc检查C++(wdm2)') {
        catchError {
          build job: 'bn_kloc_cpp_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'wdm_2']]
        }
      }

      stage('kloc检查C++(wdm3)') {
        catchError {
          build job: 'bn_kloc_cpp_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'wdm_3']]
        }
      }

      stage('kloc检查C++(wdm4)') {
        catchError {
          build job: 'bn_kloc_cpp_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'wdm_4']]
        }
      }
    }
  }
}
