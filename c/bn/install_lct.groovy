// agent          : Agent名称
// home           : 工作目录
// version        : 版本号
// display_version: 显示版本号
// zh             : 中文版本

if ("${zh}" == 'true') {
  currentBuild.displayName = "#${currentBuild.number}(${agent}-中文版本)"
} else {
  currentBuild.displayName = "#${currentBuild.number}(${agent}-英文版本)"
}

timestamps {
  stage("LCT版本制作") {
    node("${agent}") {
      timeout(120) {
        dir("${home}") {
          def shell = 'bat'
          def rake = 'call rake'

          if (isUnix()) {
            shell = 'sh'
            rake = 'rake'
          }

          "${shell}" "${rake} bn:install:install_lct[nil,nil,nil,\"${version}\",\"${display_version}\",${zh}] --trace"
        }
      }
    }
  }
}
