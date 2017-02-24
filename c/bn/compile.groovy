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

  stage('版本编译JAVA(interface)') {
    catchError {
      build job: 'bn_compile_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'interface'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
    }
  }

  stage('版本编译JAVA(platform)') {
    catchError {
      build job: 'bn_compile_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'platform'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
    }
  }

  stage('版本编译JAVA(e2e)') {
    catchError {
      build job: 'bn_compile_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'e2e'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
    }
  }

  stage('版本编译JAVA(necommon)') {
    catchError {
      build job: 'bn_compile_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'necommon'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
    }
  }

  stage('版本编译JAVA(uca)') {
    catchError {
      build job: 'bn_compile_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'uca'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
    }
  }

  stage('版本编译JAVA(xmlfile)') {
    catchError {
      build job: 'bn_compile_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'xmlfile'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
    }
  }

  stage('版本编译JAVA(naf)') {
    catchError {
      build job: 'bn_compile_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'naf'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
    }
  }

  stage('版本编译JAVA(sdh)') {
    catchError {
      build job: 'bn_compile_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'sdh'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
    }
  }

  stage('版本编译JAVA(wdm)') {
    catchError {
      build job: 'bn_compile_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'wdm'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
    }
  }

  stage('版本编译JAVA(ptn)') {
    catchError {
      build job: 'bn_compile_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'ptn'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
    }
  }

  stage('版本编译JAVA(ptn2)') {
    catchError {
      build job: 'bn_compile_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'ptn2'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
    }
  }

  stage('版本编译JAVA(ip)') {
    catchError {
      build job: 'bn_compile_module', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'ip'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
    }
  }
}
