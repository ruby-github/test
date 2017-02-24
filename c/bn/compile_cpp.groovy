// agent  : Agent名称
// home   : 工作目录
// cmdline: 编译命令
// force  : 全量编译
// version: 版本号

currentBuild.displayName = "#${currentBuild.number}(${agent})"

timestamps {
  def forceValue = true

  if ("${force}" == 'false') {
    forceValue = false
  }

  stage('版本编译C++(interface)') {
    catchError {
      build job: 'bn_compile_cpp_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'interface'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
    }
  }

  stage('版本编译C++(platform)') {
    catchError {
      build job: 'bn_compile_cpp_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'platform'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
    }
  }

  stage('版本编译C++(necommon)') {
    catchError {
      build job: 'bn_compile_cpp_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'necommon'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
    }
  }

  stage('版本编译C++(uca)') {
    catchError {
      build job: 'bn_compile_cpp_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'uca'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
    }
  }

  stage('版本编译C++(naf)') {
    catchError {
      build job: 'bn_compile_cpp_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'naf'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
    }
  }

  stage('版本编译C++(e2e)') {
    catchError {
      build job: 'bn_compile_cpp_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'e2e'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
    }
  }

  stage('版本编译C++(sdh)') {
    catchError {
      build job: 'bn_compile_cpp_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'sdh'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
    }
  }

  stage('版本编译C++(wdm)') {
    catchError {
      build job: 'bn_compile_cpp_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'wdm'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
    }
  }
}
