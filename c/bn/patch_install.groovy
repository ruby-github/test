// os             : OS名称
// name           : 补丁名称
// version        : 版本号
// display_version: 显示版本号
// sp_next        : 下一个SP补丁
// type           : 版本类型

currentBuild.displayName = "#${currentBuild.number}(${name}_${os})"

timestamps {
  stage("补丁汇总(${name})") {
    def agent = 'windows'
    def home = "d:/build/build/${name}"

    if ("${os}" == 'linux') {
      agent = 'linux'
      home = "/home/user/build/build/${name}"
    }

    if ("${os}" == 'solaris') {
      agent = 'solaris'
      home = "/home/user/build/build/${name}"
    }

    if ("${os}" == 'windows32') {
      home = "e:/build/build/${name}"
    }

    node(agent) {
      def shell = 'bat'
      def rake = 'call rake'

      if (isUnix()) {
        shell = 'sh'
        rake = 'rake'
      }

      timeout(30) {
        dir(home) {
          "${shell}" "${rake} bn:patch:install[nil,nil,\"${version}\",\"${display_version}\",${sp_next},${type}] --trace"
        }
      }
    }
  }
}
