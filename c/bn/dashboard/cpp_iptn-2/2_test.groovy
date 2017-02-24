// list   : 变更列表
// authors: 变更人员

currentBuild.result = 'SUCCESS'
currentBuild.displayName = "#${currentBuild.number}(${authors})"

catchError {
  timestamps {
    stage('测试') {
      node('iptn-2') {
        timeout(180) {
          dir('/home/iptn-2/build/main') {
            sh "rake bn:dashboard_cpp:test[nil,iptn-2,\"${list}\"] --trace"
          }
        }
      }
    }
  }
}

if (currentBuild.result == 'SUCCESS') {
  currentBuild.displayName = "#${currentBuild.number}"
}
