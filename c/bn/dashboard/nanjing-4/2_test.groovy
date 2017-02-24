// list   : 变更列表
// authors: 变更人员

currentBuild.result = 'SUCCESS'
currentBuild.displayName = "#${currentBuild.number}(${authors})"

catchError {
  timestamps {
    stage('测试') {
      node('nanjing-4') {
        timeout(180) {
          dir('/home/nanjing-4/build/main') {
            sh "rake bn:dashboard:test[nil,nanjing-4,\"${list}\"] --trace"
          }
        }
      }
    }
  }
}

if (currentBuild.result == 'SUCCESS') {
  currentBuild.displayName = "#${currentBuild.number}"
}
