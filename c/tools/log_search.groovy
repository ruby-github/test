timestamps {
  stage("自动提交日志搜索结果") {
    node("windows") {
      timeout(120) {
        dir("d:/build/main") {
          bat "call rake jenkins:log_search --trace"
        }
      }
    }
  }
}
