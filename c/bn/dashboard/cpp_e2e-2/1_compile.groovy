// list   : 变更列表
// authors: 变更人员

currentBuild.result = 'SUCCESS'
currentBuild.displayName = "#${currentBuild.number}(${authors})"

catchError {
  timestamps {
    stage('编译') {
      node('e2e-2') {
        timeout(180) {
          dir('/home/e2e-2/build/main') {
            sh "rake bn:dashboard_cpp:compile[nil,e2e-2,\"${list}\"] --trace"
          }
        }
      }
    }
  }
}

if (currentBuild.result == 'SUCCESS') {
  currentBuild.displayName = "#${currentBuild.number}"
}
