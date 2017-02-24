// name       : 补丁名称
// version    : 版本号
// branch     : 分支名称
// windows    : windows系统
// windows32  : windows32系统
// linux      : linux系统
// solaris    : solaris系统

timestamps {
  catchError {
    if ("${windows}" == 'true') {
      stage("初始化补丁环境(windows)") {
        node("windows") {
          timeout(10) {
            dir("d:/build/main") {
              bat "call rake jenkins:bn_patch_init[${name},\"${version}\",\"${branch}\"] --trace"
            }
          }
        }
      }
    }
  }

  catchError {
    if ("${windows32}" == 'true') {
      stage("初始化补丁环境(windows32)") {
        node("windows") {
          timeout(10) {
            dir("e:/build/main") {
              bat "call rake jenkins:bn_patch_init[${name},\"${version}\",\"${branch}\"] --trace"
            }
          }
        }
      }
    }
  }

  catchError {
    if ("${linux}" == 'true') {
      stage("初始化补丁环境(linux)") {
        node("linux") {
          timeout(10) {
            dir("/home/user/build/main") {
              sh "rake jenkins:bn_patch_init[${name},\"${version}\",\"${branch}\"] --trace"
            }
          }
        }
      }
    }
  }

  catchError {
    if ("${solaris}" == 'true') {
      stage("初始化补丁环境(solaris)") {
        node("solaris") {
          timeout(10) {
            dir("/home/user/build/main") {
              sh "rake jenkins:bn_patch_init[${name},\"${version}\",\"${branch}\"] --trace"
            }
          }
        }
      }
    }
  }
}
