// agent: Agent名称
// home : 工作目录
// name : 模块名称

currentBuild.displayName = "#${currentBuild.number}(${name}-${agent})"

timestamps {
  stage("版本更新(${name})") {
    node("${agent}") {
      timeout(120) {
        dir("${home}") {
          def shell = 'bat'
          def rake = 'call rake'

          if (isUnix()) {
            shell = 'sh'
            rake = 'rake'
          }

          "${shell}" "${rake} bn:update:update[${name}] --trace"
        }
      }
    }
  }
}
