// name : 补丁名称

currentBuild.displayName = "#${currentBuild.number}(${name})"

timestamps {
  stage("补丁制作(${name})") {
    node("windows") {
      timeout(30) {
        dir("f:/build/stn/build/${name}") {
          bat "call rake stn:patch:patch --trace"
        }
      }
    }
  }
}
