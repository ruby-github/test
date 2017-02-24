timestamps {
  node('polling') {
    stage("更新interface") {
      catchError {
        timeout(120) {
          checkout([$class: 'SubversionSCM', locations: [[credentialsId: 'u3build', local: 'bn/Interface', remote: 'https://10.5.72.55:8443/svn/Interface/trunk']]])
        }
      }
    }

    stage("更新platform") {
      catchError {
        timeout(120) {
          checkout([$class: 'SubversionSCM', locations: [[credentialsId: 'u3build', local: 'bn/BN_Platform', remote: 'https://10.5.72.55:8443/svn/BN_Platform/trunk']]])
        }
      }
    }

    stage("更新necommon") {
      catchError {
        timeout(120) {
          checkout([$class: 'SubversionSCM', locations: [[credentialsId: 'u3build', local: 'bn/BN_NECOMMON', remote: 'https://10.5.72.55:8443/svn/BN_NECOMMON/trunk']]])
        }
      }
    }

    stage("更新uca") {
      catchError {
        timeout(120) {
          checkout([$class: 'SubversionSCM', locations: [[credentialsId: 'u3build', local: 'bn/BN_UCA', remote: 'https://10.5.72.55:8443/svn/BN_UCA/trunk']]])
        }
      }
    }

    stage("更新xmlfile") {
      catchError {
        timeout(120) {
          checkout([$class: 'SubversionSCM', locations: [[credentialsId: 'u3build', local: 'bn/NAF_XMLFILE', remote: 'https://10.5.72.55:8443/svn/NBI_XMLFILE/trunk']]])
        }
      }
    }

    stage("更新naf") {
      catchError {
        timeout(120) {
          // checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'bn/BN_NAF']], userRemoteConfigs: [[credentialsId: 'u3build', url: 'http://10.30.19.111:8080/tfs/YX/$/NBI']]])
        }
      }
    }

    stage("更新e2e") {
      catchError {
        timeout(120) {
          checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'bn/U31_E2E']], userRemoteConfigs: [[credentialsId: 'u3build', url: 'http://10.5.64.19/git/U31_E2E']]])
        }
      }
    }

    stage("更新sdh") {
      catchError {
        timeout(120) {
          checkout([$class: 'SubversionSCM', locations: [[credentialsId: 'u3build', local: 'bn/BN_SDH', remote: 'https://10.5.72.55:8443/svn/BN_SDH/trunk']]])
        }
      }
    }

    stage("更新wdm") {
      catchError {
        timeout(120) {
          checkout([$class: 'SubversionSCM', locations: [[credentialsId: 'u3build', local: 'bn/BN_WDM', remote: 'https://10.5.72.55:8443/svn/BN_WDM/trunk']]])
        }
      }
    }

    stage("更新ptn") {
      catchError {
        timeout(120) {
          checkout([$class: 'SubversionSCM', locations: [[credentialsId: 'u3build', local: 'bn/BN_PTN', remote: 'https://10.5.72.55:8443/svn/BN_PTN/trunk']]])
        }
      }
    }

    stage("更新ptn2") {
      catchError {
        timeout(120) {
          checkout([$class: 'SubversionSCM', locations: [[credentialsId: 'u3build', local: 'bn/BN_PTN2', remote: 'https://10.5.72.55:8443/svn/BN_PTN2/trunk']]])
        }
      }
    }

    stage("更新ip") {
      catchError {
        timeout(120) {
          checkout([$class: 'SubversionSCM', locations: [[credentialsId: 'u3build', local: 'bn/BN_IP', remote: 'https://10.5.72.55:8443/svn/BN_IP/trunk']]])
        }
      }
    }
  }

  node('dashboard') {
    stage("获取文件变更信息") {
      timeout(30) {
        dir('/home/user/dashboard/bn') {
          sh "rake bn:dashboard:polling --trace"
        }
      }
    }
  }
}
