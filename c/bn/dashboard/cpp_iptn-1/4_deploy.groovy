// list   : 变更列表
// authors: 变更人员

currentBuild.result = 'SUCCESS'
currentBuild.displayName = "#${currentBuild.number}(${authors})"

catchError {
  timestamps {
    stage('发布') {
      node('iptn-1') {
        timeout(180) {
          dir('/home/iptn-1/build/main') {
            sh "rake bn:dashboard_cpp:deploy[nil,iptn-1,\"${list}\"] --trace"
          }
        }
      }
    }
  }
}

if (currentBuild.result == 'SUCCESS') {
  currentBuild.displayName = "#${currentBuild.number}"
}
