// agent  : Agent名称
// home   : 工作目录
// version: 版本号

currentBuild.displayName = "#${currentBuild.number}(${agent})"

timestamps {
  stage("版本制作(uep)") {
    node("${agent}") {
      timeout(30) {
        dir("${home}") {
          def shell = 'bat'
          def rake = 'call rake'

          if (isUnix()) {
            shell = 'sh'
            rake = 'rake'
          }

          "${shell}" "${rake} stn:install:uep[nil,nil,nil,\"${version}\"] --trace"
        }
      }
    }
  }
}
