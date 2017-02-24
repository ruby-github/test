timestamps {
  stage("跨项目代码修改走查") {
    node("windows") {
      timeout(30) {
        dir("d:/build/main") {
          bat "call rake jenkins:scm_change --trace"
        }
      }
    }
  }
}
