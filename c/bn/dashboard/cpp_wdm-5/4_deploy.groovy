// list   : 变更列表
// authors: 变更人员

currentBuild.result = 'SUCCESS'
currentBuild.displayName = "#${currentBuild.number}(${authors})"

catchError {
  timestamps {
    stage('发布') {
      node('wdm-5') {
        timeout(180) {
          dir('/home/wdm-5/build/main') {
            sh "rake bn:dashboard_cpp:deploy[nil,wdm-5,\"${list}\"] --trace"
          }
        }
      }
    }
  }
}

if (currentBuild.result == 'SUCCESS') {
  currentBuild.displayName = "#${currentBuild.number}"
}
