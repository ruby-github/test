// agent          : Agent名称
// home           : 工作目录
// version        : 版本号
// display_version: 显示版本号
// type           : 版本类型

currentBuild.displayName = "#${currentBuild.number}(${agent})"

timestamps {
  currentBuild.result = 'SUCCESS'

  stage('版本制作(uep)') {
    catchError {
      build job: 'bn_install_uep', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'version', value: "${version}"],[$class: 'StringParameterValue', name: 'type', value: "${type}"]]
    }
  }

  if (currentBuild.result == 'SUCCESS') {
    stage('版本制作(bn)') {
      parallel interface: {
        build job: 'bn_install_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'interface'],[$class: 'StringParameterValue', name: 'version', value: "${version}"],[$class: 'StringParameterValue', name: 'display_version', value: "${display_version}"],[$class: 'StringParameterValue', name: 'type', value: "${type}"]]
      }, platform: {
        build job: 'bn_install_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'platform'],[$class: 'StringParameterValue', name: 'version', value: "${version}"],[$class: 'StringParameterValue', name: 'display_version', value: "${display_version}"],[$class: 'StringParameterValue', name: 'type', value: "${type}"]]
      }, necommon: {
        build job: 'bn_install_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'necommon'],[$class: 'StringParameterValue', name: 'version', value: "${version}"],[$class: 'StringParameterValue', name: 'display_version', value: "${display_version}"],[$class: 'StringParameterValue', name: 'type', value: "${type}"]]
      }, uca: {
        build job: 'bn_install_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'uca'],[$class: 'StringParameterValue', name: 'version', value: "${version}"],[$class: 'StringParameterValue', name: 'display_version', value: "${display_version}"],[$class: 'StringParameterValue', name: 'type', value: "${type}"]]
      }, xmlfile: {
        build job: 'bn_install_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'xmlfile'],[$class: 'StringParameterValue', name: 'version', value: "${version}"],[$class: 'StringParameterValue', name: 'display_version', value: "${display_version}"],[$class: 'StringParameterValue', name: 'type', value: "${type}"]]
      }, naf: {
        build job: 'bn_install_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'naf'],[$class: 'StringParameterValue', name: 'version', value: "${version}"],[$class: 'StringParameterValue', name: 'display_version', value: "${display_version}"],[$class: 'StringParameterValue', name: 'type', value: "${type}"]]
      }, e2e: {
        build job: 'bn_install_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'e2e'],[$class: 'StringParameterValue', name: 'version', value: "${version}"],[$class: 'StringParameterValue', name: 'display_version', value: "${display_version}"],[$class: 'StringParameterValue', name: 'type', value: "${type}"]]
      }, sdh: {
        build job: 'bn_install_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'sdh'],[$class: 'StringParameterValue', name: 'version', value: "${version}"],[$class: 'StringParameterValue', name: 'display_version', value: "${display_version}"],[$class: 'StringParameterValue', name: 'type', value: "${type}"]]
      }, wdm: {
        build job: 'bn_install_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'wdm'],[$class: 'StringParameterValue', name: 'version', value: "${version}"],[$class: 'StringParameterValue', name: 'display_version', value: "${display_version}"],[$class: 'StringParameterValue', name: 'type', value: "${type}"]]
      }, ptn: {
        build job: 'bn_install_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'ptn'],[$class: 'StringParameterValue', name: 'version', value: "${version}"],[$class: 'StringParameterValue', name: 'display_version', value: "${display_version}"],[$class: 'StringParameterValue', name: 'type', value: "${type}"]]
      }, ptn2: {
        build job: 'bn_install_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'ptn2'],[$class: 'StringParameterValue', name: 'version', value: "${version}"],[$class: 'StringParameterValue', name: 'display_version', value: "${display_version}"],[$class: 'StringParameterValue', name: 'type', value: "${type}"]]
      }, ip: {
        build job: 'bn_install_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'ip'],[$class: 'StringParameterValue', name: 'version', value: "${version}"],[$class: 'StringParameterValue', name: 'display_version', value: "${display_version}"],[$class: 'StringParameterValue', name: 'type', value: "${type}"]]
      },
      failFast: false
    }
  }
}
