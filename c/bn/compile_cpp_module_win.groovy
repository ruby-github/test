// agent  : Agent名称
// home   : 工作目录
// name   : 模块名称
// cmdline: 编译命令
// force  : 全量编译
// retry  : 失败重试
// dir    : 编译目录
// version: 版本号

currentBuild.displayName = "#${currentBuild.number}(${name}-${agent})"

timestamps {
  node("${agent}") {
    dir("${home}") {
      def shell = 'bat'
      def rake = 'call rake'

      if (isUnix()) {
        shell = 'sh'
        rake = 'rake'
      }

      stage("版本编译(${name})") {
        timeout(180) {
          withEnv(["VERSION=${version}"]) {
            "${shell}" "${rake} bn:compile:mvn_cpp[${name},\"${cmdline}\",${force},${retry},nil,${dir}] --trace"
          }
        }
      }

      stage("收集debuginfo(${name})") {
        catchError {
          timeout(30) {
            "${shell}" "${rake} bn:compile:debuginfo[${name}] --trace"
          }
        }
      }

      currentBuild.result = 'SUCCESS'
    }
  }
}
