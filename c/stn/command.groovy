// home     : 工作目录
// configure: 配置文件
// version  : 版本号
// reboot   : 重启测试机

currentBuild.displayName = "#${currentBuild.number}(${home})"

timestamps {
  stage("执行command(${home}:${configure})") {
    node('command') {
      timeout(300) {
        dir('d:/command') {
          bat "call rake command:exec[\"${home}\",\"${configure}\",\"${version}\",${reboot}] --trace"
        }
      }
    }
  }
}
