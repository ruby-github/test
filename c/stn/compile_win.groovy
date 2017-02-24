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

  stage('版本编译(u3_interface)') {
    catchError {
      build job: 'stn_compile_module_win', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'u3_interface'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
    }
  }

  stage('版本编译(interface)') {
    catchError {
      build job: 'stn_compile_module_win', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'interface'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
    }
  }

  stage('版本编译(framework)') {
    catchError {
      build job: 'stn_compile_module_win', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'framework'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
    }
  }

  stage('版本编译(application,nesc,tunnel,e2e,ict,installation)') {
    parallel application: {
      build job: 'stn_compile_module_win', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'application'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
    }, nesc: {
      build job: 'stn_compile_module_win', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'nesc'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
    }, tunnel: {
      build job: 'stn_compile_module_win', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'tunnel'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
    }, ict: {
      catchError {
        build job: 'stn_compile_module_win', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'e2e'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
      }

      catchError {
        build job: 'stn_compile_module_win', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'ict'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
      }
    }, installation: {
      build job: 'stn_compile_module_win', parameters: [[$class: 'StringParameterValue', name: 'agent', value: "${agent}"],[$class: 'StringParameterValue', name: 'home', value: "${home}"],[$class: 'StringParameterValue', name: 'name', value: 'installation'],[$class: 'StringParameterValue', name: 'cmdline', value: "${cmdline}"],[$class: 'BooleanParameterValue', name: 'force', value: forceValue],[$class: 'StringParameterValue', name: 'version', value: "${version}"]]
    },
    failFast: false
  }
}
