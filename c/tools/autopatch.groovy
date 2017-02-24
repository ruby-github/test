timestamps {
  stage("自动分发补丁") {
    node("master") {
      def shell = 'bat'
      def rake = 'call rake'

      if (isUnix()) {
        shell = 'sh'
        rake = 'rake'
      }

      timeout(10) {
        dir("/home/workspace/auto/command") {
          "${shell}" "${rake} jenkins:autopatch --trace"
        }
      }
    }
  }
}
