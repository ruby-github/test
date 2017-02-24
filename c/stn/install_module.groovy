// agent          : Agent名称
// home           : 工作目录
// name           : 模块名称
// version        : 版本号
// display_version: 显示版本号

currentBuild.displayName = "#${currentBuild.number}(${name}-${agent})"

timestamps {
  stage("版本制作(${name})") {
    node("${agent}") {
      timeout(30) {
        dir("${home}") {
          def shell = 'bat'
          def rake = 'call rake'

          if (isUnix()) {
            shell = 'sh'
            rake = 'rake'
          }

          "${shell}" "${rake} stn:install:install[${name},nil,nil,\"${version}\",\"${display_version}\"] --trace"
        }
      }
    }
  }
}
