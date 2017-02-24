// agent: Agent名称
// home : 工作目录

currentBuild.displayName = "#${currentBuild.number}(${agent})"

timestamps {
  stage("klockwork检查忽略模块") {
    node("${agent}") {
      timeout(30) {
        dir("${home}") {
          sh "rake bn:check:kloc_ignore --trace"
        }
      }
    }
  }
}
