// agent  : Agent名称
// home   : 工作目录
// cmdline: 编译命令

timestamps {
  node("${agent}") {
    dir("${home}") {
      def shell = 'bat'
      def rake = 'call rake'

      if (isUnix()) {
        shell = 'sh'
        rake = 'rake'
      }

      stage("版本更新(devtools)") {
        catchError {
          timeout(120) {
            "${shell}" "${rake} bn:update:devtools --trace"
          }
        }
      }

      stage("版本更新(interface)") {
        catchError {
          timeout(120) {
            "${shell}" "${rake} bn:update:update[interface] --trace"
          }
        }
      }

      stage("版本更新(platform)") {
        catchError {
          timeout(120) {
            "${shell}" "${rake} bn:update:update[platform] --trace"
          }
        }
      }

      stage("版本更新(necommon)") {
        catchError {
          timeout(120) {
            "${shell}" "${rake} bn:update:update[necommon] --trace"
          }
        }
      }

      stage("版本编译C++(interface)") {
        catchError {
          timeout(120) {
            "${shell}" "${rake} bn:compile:mvn_cpp[interface,\"${cmdline}\",true,true,nil,nil] --trace"
          }
        }
      }

      stage("版本编译C++(platform)") {
        catchError {
          timeout(120) {
            "${shell}" "${rake} bn:compile:mvn_cpp[platform,\"${cmdline}\",true,true,nil,nil] --trace"
          }
        }
      }

      stage("版本编译C++(necommon)") {
        catchError {
          timeout(120) {
            "${shell}" "${rake} bn:compile:mvn_cpp[necommon,\"${cmdline}\",true,true,nil,nil] --trace"
          }
        }
      }
    }
  }
}
