// os         : OS名称
// name       : 补丁名称
// module_name: 模块名称

currentBuild.displayName = "#${currentBuild.number}(${name}_${module_name}_${os})"

timestamps {
  stage("补丁制作(${name}_${module_name})") {
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

      timeout(180) {
        dir(home) {
          "${shell}" "${rake} bn:patch:patch[${module_name}] --trace"
        }
      }
    }
  }
}
