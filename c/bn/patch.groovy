// os   : OS名称
// name : 补丁名称

currentBuild.displayName = "#${currentBuild.number}(${name}_${os})"

timestamps {
  stage("补丁制作(${name})") {
    parallel ptn: {
      build job: 'bn_patch_module', parameters: [[$class: 'StringParameterValue', name: 'os', value: "${os}"],[$class: 'StringParameterValue', name: 'name', value: "${name}"],[$class: 'StringParameterValue', name: 'module_name', value: "ptn"]]
    }, e2e: {
      build job: 'bn_patch_module', parameters: [[$class: 'StringParameterValue', name: 'os', value: "${os}"],[$class: 'StringParameterValue', name: 'name', value: "${name}"],[$class: 'StringParameterValue', name: 'module_name', value: "e2e"]]
    }, naf: {
      build job: 'bn_patch_module', parameters: [[$class: 'StringParameterValue', name: 'os', value: "${os}"],[$class: 'StringParameterValue', name: 'name', value: "${name}"],[$class: 'StringParameterValue', name: 'module_name', value: "naf"]]
    }, wdm: {
      build job: 'bn_patch_module', parameters: [[$class: 'StringParameterValue', name: 'os', value: "${os}"],[$class: 'StringParameterValue', name: 'name', value: "${name}"],[$class: 'StringParameterValue', name: 'module_name', value: "wdm"]]
    },
    failFast: false
  }
}
