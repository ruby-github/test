// agent: Agent名称
// home : 工作目录

currentBuild.displayName = "#${currentBuild.number}(${agent})"

timestamps {
  node("${agent}") {
    if (isUnix()) {
      stage('版本检查(interface)') {
        catchError {
          build job: 'stn_check_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'interface']]
        }
      }

      stage('版本检查(framework)') {
        catchError {
          build job: 'stn_check_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'framework']]
        }
      }

      stage('版本检查(application)') {
        catchError {
          build job: 'stn_check_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'application']]
        }
      }

      stage('版本检查(nesc)') {
        catchError {
          build job: 'stn_check_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'nesc']]
        }
      }

      stage('版本检查(tunnel)') {
        catchError {
          build job: 'stn_check_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'tunnel']]
        }
      }

      stage('版本检查(ict)') {
        catchError {
          build job: 'stn_check_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'ict']]
        }
      }

      stage('版本检查(e2e)') {
        catchError {
          build job: 'stn_check_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'e2e']]
        }
      }
    }
  }
}
