// list   : 变更列表
// authors: 变更人员

currentBuild.result = 'SUCCESS'
currentBuild.displayName = "#${currentBuild.number}(${authors})"

catchError {
  timestamps {
    stage('检查') {
      node('nanjing-1') {
        timeout(180) {
          dir('/home/nanjing-1/build/main') {
            sh "rake bn:dashboard:check[nil,nanjing-1,\"${list}\"] --trace"
          }
        }
      }
    }
  }
}

if (currentBuild.result == 'SUCCESS') {
  currentBuild.displayName = "#${currentBuild.number}"
}
