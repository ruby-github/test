// list   : 变更列表
// authors: 变更人员

currentBuild.result = 'SUCCESS'
currentBuild.displayName = "#${currentBuild.number}(${authors})"

catchError {
  timestamps {
    stage('编译') {
      build job: '1_naf_compile_cpp', parameters: [[$class: "StringParameterValue", name: "list", value: "${list}"],[$class: "StringParameterValue", name: "authors", value: "${authors}"]]
    }

    stage('测试') {
      build job: '2_naf_test_cpp', parameters: [[$class: "StringParameterValue", name: "list", value: "${list}"],[$class: "StringParameterValue", name: "authors", value: "${authors}"]]
    }

    stage('检查') {
      build job: '3_naf_check_cpp', parameters: [[$class: "StringParameterValue", name: "list", value: "${list}"],[$class: "StringParameterValue", name: "authors", value: "${authors}"]]
    }

    stage('发布') {
      build job: '4_naf_deploy_cpp', parameters: [[$class: "StringParameterValue", name: "list", value: "${list}"],[$class: "StringParameterValue", name: "authors", value: "${authors}"]]
    }
  }
}

if (currentBuild.result == 'SUCCESS') {
  currentBuild.displayName = "#${currentBuild.number}"
}
