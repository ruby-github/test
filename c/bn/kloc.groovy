// agent: Agent名称
// home : 工作目录

currentBuild.displayName = "#${currentBuild.number}(${agent})"

timestamps {
  node("${agent}") {
    if (isUnix()) {
      stage('kloc检查(platform)') {
        catchError {
          build job: 'bn_kloc_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'platform']]
        }
      }

      stage('kloc检查(necommon)') {
        catchError {
          build job: 'bn_kloc_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'necommon']]
        }
      }

      stage('kloc检查(uca)') {
        catchError {
          build job: 'bn_kloc_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'uca']]
        }
      }

      stage('kloc检查(xmlfile)') {
        catchError {
          build job: 'bn_kloc_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'xmlfile']]
        }
      }

      stage('kloc检查(naf)') {
        catchError {
          build job: 'bn_kloc_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'naf']]
        }
      }

      stage('kloc检查(e2e)') {
        catchError {
          build job: 'bn_kloc_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'e2e']]
        }
      }

      stage('kloc检查(sdh)') {
        catchError {
          build job: 'bn_kloc_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'sdh']]
        }
      }

      stage('kloc检查(wdm)') {
        catchError {
          build job: 'bn_kloc_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'wdm']]
        }
      }

      stage('kloc检查(ptn)') {
        catchError {
          build job: 'bn_kloc_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'ptn']]
        }
      }

      stage('kloc检查(ptn2)') {
        catchError {
          build job: 'bn_kloc_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'ptn2']]
        }
      }

      stage('kloc检查(ip)') {
        catchError {
          build job: 'bn_kloc_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'ip']]
        }
      }
    }
  }
}
