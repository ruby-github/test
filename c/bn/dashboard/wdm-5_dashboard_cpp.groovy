// list   : 变更列表
// authors: 变更人员

currentBuild.result = 'SUCCESS'
currentBuild.displayName = "#${currentBuild.number}(${authors})"

catchError {
  timestamps {
    stage('编译') {
      build job: '1_wdm-5_compile_cpp', parameters: [[$class: "StringParameterValue", name: "list", value: "${list}"],[$class: "StringParameterValue", name: "authors", value: "${authors}"]]
    }

    stage('测试') {
      build job: '2_wdm-5_test_cpp', parameters: [[$class: "StringParameterValue", name: "list", value: "${list}"],[$class: "StringParameterValue", name: "authors", value: "${authors}"]]
    }

    stage('检查') {
      build job: '3_wdm-5_check_cpp', parameters: [[$class: "StringParameterValue", name: "list", value: "${list}"],[$class: "StringParameterValue", name: "authors", value: "${authors}"]]
    }

    stage('发布') {
      build job: '4_wdm-5_deploy_cpp', parameters: [[$class: "StringParameterValue", name: "list", value: "${list}"],[$class: "StringParameterValue", name: "authors", value: "${authors}"]]
    }
  }
}

if (currentBuild.result == 'SUCCESS') {
  currentBuild.displayName = "#${currentBuild.number}"
}
