// agent  : Agent名称
// home   : 工作目录
// version: 版本号
// reboot : 重启测试机

currentBuild.displayName = "#${currentBuild.number}(${agent})"

timestamps {
  def rebootValue = false

  if ("${reboot}" == 'true') {
    rebootValue = true
  }

  if ("${agent}" == "linux") {
    catchError {
      stage("版本安装") {
        build job: 'bn_command', parameters: [[$class: 'StringParameterValue', name: 'home', value: "bn/daily/linux"],[$class: 'StringParameterValue', name: 'configure', value: "installation.xml"],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
      }
    }
  }

  if ("${agent}" == "solaris") {
    catchError {
      stage("版本安装") {
        build job: 'bn_command', parameters: [[$class: 'StringParameterValue', name: 'home', value: "bn/daily/solaris"],[$class: 'StringParameterValue', name: 'configure', value: "installation.xml"],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
      }
    }
  }

  if ("${agent}" == "windows") {
    if ("${home}".contains("d:/build")) {
      catchError {
        stage("版本安装") {
          build job: 'bn_command', parameters: [[$class: 'StringParameterValue', name: 'home', value: "bn/daily/windows"],[$class: 'StringParameterValue', name: 'configure', value: "installation.xml"],[$class: 'StringParameterValue', name: 'version', value: "${version}"],[$class: 'BooleanParameterValue', name: 'reboot', value: rebootValue]]
        }

        stage("版本测试") {
          build job: 'bn_command', parameters: [[$class: 'StringParameterValue', name: 'home', value: "bn/daily/windows"],[$class: 'StringParameterValue', name: 'configure', value: "autotest.xml"],[$class: 'StringParameterValue', name: 'version', value: "${version}"],[$class: 'BooleanParameterValue', name: 'reboot', value: rebootValue]]
        }
      }
    } else {
      catchError {
        stage("版本安装") {
          current = build job: 'bn_command', parameters: [[$class: 'StringParameterValue', name: 'home', value: "bn/daily/windows32"],[$class: 'StringParameterValue', name: 'configure', value: "installation.xml"],[$class: 'StringParameterValue', name: 'version', value: "${version}"],[$class: 'BooleanParameterValue', name: 'reboot', value: rebootValue]]
        }
      }
    }
  }
}
