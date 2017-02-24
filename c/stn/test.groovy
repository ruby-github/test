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

  if ("${agent}" == "windows") {
    catchError {
      stage("版本安装") {
        build job: 'stn_command', parameters: [[$class: 'StringParameterValue', name: 'home', value: "stn/daily/windows"],[$class: 'StringParameterValue', name: 'configure', value: "installation.xml"],[$class: 'StringParameterValue', name: 'version', value: "${version}"],[$class: 'BooleanParameterValue', name: 'reboot', value: rebootValue]]
      }

      stage("版本测试") {
        build job: 'stn_command', parameters: [[$class: 'StringParameterValue', name: 'home', value: "stn/daily/windows"],[$class: 'StringParameterValue', name: 'configure', value: "autotest.xml"],[$class: 'StringParameterValue', name: 'version', value: "${version}"],[$class: 'BooleanParameterValue', name: 'reboot', value: rebootValue]]
      }
    }
  } else {
    catchError {
      stage("版本安装") {
        build job: 'stn_command', parameters: [[$class: 'StringParameterValue', name: 'home', value: "stn/daily/linux"],[$class: 'StringParameterValue', name: 'configure', value: "installation.xml"],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
      }
    }
  }
}
