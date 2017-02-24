// agent  : Agent名称
// home   : 工作目录
// version: 版本号

currentBuild.displayName = "#${currentBuild.number}(${agent})"

timestamps {
  node("${agent}") {
    dir("${home}") {
      def shell = 'bat'
      def rake = 'call rake'

      if (isUnix()) {
        shell = 'sh'
        rake = 'rake'
      }

      stage("部署基础POM") {
        catchError {
          timeout(10) {
            withEnv(["VERSION=${version}"]) {
              "${shell}" "${rake} stn:deploy:base --trace"
            }
          }
        }
      }

      stage("部署第三方文件") {
        catchError {
          timeout(10) {
            withEnv(["VERSION=${version}"]) {
              "${shell}" "${rake} stn:deploy:thirdparty --trace"
            }
          }
        }
      }
    }
  }
}
