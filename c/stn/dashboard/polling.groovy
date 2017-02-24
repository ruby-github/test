timestamps {
  node('polling') {
    stage("更新u3_interface") {
      catchError {
        timeout(120) {
          checkout([$class: 'SubversionSCM', locations: [[credentialsId: 'u3build', local: 'stn/u3_interface', remote: 'https://10.5.72.55:8443/svn/Interface/trunk']]])
        }
      }
    }

    stage("更新interface") {
      catchError {
        timeout(60) {
          checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'stn/sdn_interface']], userRemoteConfigs: [[credentialsId: 'u3build', url: 'http://10.5.64.19/git/sdn_interface']]])
        }
      }
    }

    stage("更新framework") {
      catchError {
        timeout(60) {
          checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'stn/sdn_framework']], userRemoteConfigs: [[credentialsId: 'u3build', url: 'http://10.5.64.19/git/sdn_framework']]])
        }
      }
    }

    stage("更新application") {
      catchError {
        timeout(60) {
          checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'stn/sdn_application']], userRemoteConfigs: [[credentialsId: 'u3build', url: 'http://10.5.64.19/git/sdn_application']]])
        }
      }
    }

    stage("更新nesc") {
      catchError {
        timeout(60) {
          checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'stn/sdn_nesc']], userRemoteConfigs: [[credentialsId: 'u3build', url: 'http://10.5.64.19/git/sdn_nesc']]])
        }
      }
    }

    stage("更新tunnel") {
      catchError {
        timeout(60) {
          checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'stn/sdn_tunnel']], userRemoteConfigs: [[credentialsId: 'u3build', url: 'http://10.5.64.19/git/sdn_tunnel']]])
        }
      }
    }

    stage("更新ict") {
      catchError {
        timeout(60) {
          checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'stn/CTR-ICT']], userRemoteConfigs: [[credentialsId: 'u3build', url: 'http://10.5.64.19/git/CTR-ICT']]])
        }
      }
    }

    stage("更新e2e") {
      catchError {
        timeout(60) {
          checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'stn/SPTN-E2E']], userRemoteConfigs: [[credentialsId: 'u3build', url: 'http://10.5.64.19/git/SPTN-E2E']]])
        }
      }
    }

    stage("更新installation") {
      catchError {
        timeout(60) {
          checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'stn/sdn_installation']], userRemoteConfigs: [[credentialsId: 'u3build', url: 'http://10.5.64.19/git/sdn_installation']]])
        }
      }
    }
  }

  stage("获取文件变更信息") {
    node('dashboard') {
      timeout(30) {
        dir('/home/user/dashboard/stn') {
          sh "rake stn:dashboard:polling --trace"
        }
      }
    }
  }
}
