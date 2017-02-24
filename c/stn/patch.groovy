// name : 补丁名称

currentBuild.displayName = "#${currentBuild.number}(${name})"

timestamps {
  stage("补丁制作(${name})") {
    build job: 'stn_patch_module', parameters: [[$class: 'StringParameterValue', name: 'name', value: "${name}"]]
  }
}
