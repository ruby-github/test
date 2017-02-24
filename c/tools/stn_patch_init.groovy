// name     : 补丁名称
// version  : 版本号
// branch   : 分支名称

timestamps {
  stage("初始化补丁环境") {
    node("windows") {
      timeout(10) {
        dir("f:/build/stn/main") {
          bat "call rake jenkins:stn_patch_init[${name},\"${version}\",\"${branch}\"] --trace"
        }
      }
    }
  }
}
