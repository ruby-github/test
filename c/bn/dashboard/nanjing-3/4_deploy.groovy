// list   : 变更列表
// authors: 变更人员

currentBuild.result = 'SUCCESS'
currentBuild.displayName = "#${currentBuild.number}(${authors})"

catchError {
  timestamps {
    stage('发布') {
      node('nanjing-3') {
        timeout(180) {
          dir('/home/nanjing-3/build/main') {
            sh "rake bn:dashboard:deploy[nil,nanjing-3,\"${list}\"] --trace"
          }
        }
      }
    }
  }
}

if (currentBuild.result == 'SUCCESS') {
  currentBuild.displayName = "#${currentBuild.number}"
}
