// list   : 变更列表
// authors: 变更人员

currentBuild.result = 'SUCCESS'
currentBuild.displayName = "#${currentBuild.number}(${authors})"

catchError {
  timestamps {
    stage('测试') {
      node('wdm-2') {
        timeout(180) {
          dir('/home/wdm-2/build/main') {
            sh "rake bn:dashboard_cpp:test[nil,wdm-2,\"${list}\"] --trace"
          }
        }
      }
    }
  }
}

if (currentBuild.result == 'SUCCESS') {
  currentBuild.displayName = "#${currentBuild.number}"
}
