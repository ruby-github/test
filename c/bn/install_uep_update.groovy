// agent  : Agent名称
// home   : 工作目录
// version: 版本号
// type   : 版本类型

currentBuild.displayName = "#${currentBuild.number}(${agent})"

timestamps {
  stage("升级包制作(uep)") {
    node("${agent}") {
      timeout(30) {
        dir("${home}") {
          def shell = 'bat'
          def rake = 'call rake'

          if (isUnix()) {
            shell = 'sh'
            rake = 'rake'
          }

          "${shell}" "${rake} bn:install:uep_update[nil,nil,nil,\"${version}\",${type}] --trace"
        }
      }
    }
  }
}
