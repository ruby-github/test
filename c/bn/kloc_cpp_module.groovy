// agent: Agent名称
// home : 工作目录
// name : 模块名称

currentBuild.displayName = "#${currentBuild.number}(${name}-${agent})"

timestamps {
  stage("klockwork检查C++(${name})") {
    node("${agent}") {
      timeout(48*60) {
        dir("${home}") {
          sh "rake bn:check:kloc_cpp[${name}] --trace"
        }
      }
    }
  }
}
