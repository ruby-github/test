// agent: Agent名称
// home : 工作目录

currentBuild.displayName = "#${currentBuild.number}(${agent})"

timestamps {
  stage("版本更新(devtools)") {
    node("${agent}") {
      timeout(120) {
        dir("${home}") {
          def shell = 'bat'
          def rake = 'call rake'

          if (isUnix()){
            shell = 'sh'
            rake = 'rake'
          }

          "${shell}" "${rake} bn:update:devtools --trace"
        }
      }
    }
  }
}
