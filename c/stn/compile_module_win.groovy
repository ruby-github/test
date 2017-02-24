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
  stage("版本编译(${name})") {
    node("${agent}") {
      timeout(30) {
        dir("${home}") {
          def shell = 'bat'
          def rake = 'call rake'

          if (isUnix()) {
            shell = 'sh'
            rake = 'rake'
          }

          withEnv(["VERSION=${version}"]) {
            "${shell}" "${rake} stn:compile:mvn[${name},\"${cmdline}\",${force},${retry},nil,${dir}] --trace"
          }
        }
      }
    }
  }
}
