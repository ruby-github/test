// name           : 补丁名称
// version        : 版本号
// display_version: 显示版本号
// sp_next        : 下一个SP补丁

currentBuild.displayName = "#${currentBuild.number}(${name})"

timestamps {
  stage("补丁汇总(${name})") {
    node("windows") {
      timeout(30) {
        dir("f:/build/stn/build/${name}") {
          bat "call rake stn:patch:install[nil,nil,\"${version}\",\"${display_version}\",${sp_next}] --trace"
        }
      }
    }
  }
}
