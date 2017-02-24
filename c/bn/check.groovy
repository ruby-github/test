// agent: Agent名称
// home : 工作目录

currentBuild.displayName = "#${currentBuild.number}(${agent})"

timestamps {
  node("${agent}") {
    if (isUnix()) {
      stage('版本检查(platform)') {
        catchError {
          build job: 'bn_check_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'platform']]
        }
      }

      stage('版本检查(necommon)') {
        catchError {
          build job: 'bn_check_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'necommon']]
        }
      }

      stage('版本检查(uca)') {
        catchError {
          build job: 'bn_check_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'uca']]
        }
      }

      stage('版本检查(xmlfile)') {
        catchError {
          build job: 'bn_check_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'xmlfile']]
        }
      }

      stage('版本检查(naf)') {
        catchError {
          build job: 'bn_check_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'naf']]
        }
      }

      stage('版本检查(e2e)') {
        catchError {
          build job: 'bn_check_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'e2e']]
        }
      }

      stage('版本检查(sdh)') {
        catchError {
          build job: 'bn_check_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'sdh']]
        }
      }

      stage('版本检查(wdm)') {
        catchError {
          build job: 'bn_check_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'wdm']]
        }
      }

      stage('版本检查(ptn)') {
        catchError {
          build job: 'bn_check_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'ptn']]
        }
      }

      stage('版本检查(ptn2)') {
        catchError {
          build job: 'bn_check_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'ptn2']]
        }
      }

      stage('版本检查(ip)') {
        catchError {
          build job: 'bn_check_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'ip']]
        }
      }
    }
  }
}
