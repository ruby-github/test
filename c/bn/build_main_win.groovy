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
  stage("版本构建") {
    def updateValue = true

    if ("${update}" == 'false') {
      updateValue = false
    }

    def compileValue = true

    if ("${compile}" == 'false') {
      compileValue = false
    }

    def installValue = true

    if ("${install}" == 'false') {
      installValue = false
    }

    def checkValue = true

    if ("${check}" == 'false') {
      checkValue = false
    }

    def testValue = true

    if ("${test}" == 'false') {
      testValue = false
    }

    def forceValue = true

    if ("${force}" == 'false') {
      forceValue = false
    }

    build job: 'bn_build_win', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'BooleanParameterValue', name: 'update', value: updateValue],[$class: 'BooleanParameterValue', name: 'compile', value: compileValue],[$class: 'BooleanParameterValue', name: 'install', value: installValue],[$class: 'BooleanParameterValue', name: 'check', value: checkValue],[$class: 'BooleanParameterValue', name: 'test', value: testValue],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"],[$class: 'StringParameterValue', name: 'display_version', value: "${display_version}"],[$class: 'StringParameterValue', name: 'type', value: "${type}"]]
  }
}
