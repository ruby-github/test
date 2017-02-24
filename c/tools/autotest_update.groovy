timestamps {
  stage("更新自动化测试用例") {
    node("command") {
      timeout(120) {
        dir("d:/autotest") {
          bat "svn cleanup"

          bat "svn update"
        }
      }
    }
  }
}
