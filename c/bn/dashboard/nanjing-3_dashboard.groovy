// list   : 变更列表
// authors: 变更人员

currentBuild.result = 'SUCCESS'
currentBuild.displayName = "#${currentBuild.number}(${authors})"

catchError {
  timestamps {
    stage('编译') {
      build job: '1_nanjing-3_compile', parameters: [[$class: "StringParameterValue", name: "list", value: "${list}"],[$class: "StringParameterValue", name: "authors", value: "${authors}"]]
    }

    stage('测试') {
      build job: '2_nanjing-3_test', parameters: [[$class: "StringParameterValue", name: "list", value: "${list}"],[$class: "StringParameterValue", name: "authors", value: "${authors}"]]
    }

    stage('检查') {
      build job: '3_nanjing-3_check', parameters: [[$class: "StringParameterValue", name: "list", value: "${list}"],[$class: "StringParameterValue", name: "authors", value: "${authors}"]]
    }

    stage('发布') {
      build job: '4_nanjing-3_deploy', parameters: [[$class: "StringParameterValue", name: "list", value: "${list}"],[$class: "StringParameterValue", name: "authors", value: "${authors}"]]
    }
  }
}

if (currentBuild.result == 'SUCCESS') {
  currentBuild.displayName = "#${currentBuild.number}"
}
