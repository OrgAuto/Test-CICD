def workspace = "${WORKSPACE}"
def now = new Date()
def build_time = now.format("yyMMdd_HHmm", TimeZone.getTimeZone('PST'))
def buildName = Jenkins.instance.getItemByFullName("OrgAuto/Test/main")
buildnumber = buildName.getLastSuccessfulBuild().getNumber()
properties([parameters([string(defaultValue: ${buildnumber}, description: 'Buils number', name: 'Buld No: ', trim: false)])])
// env.uploadSpec = """{
//             "files": [
//                 {
//                     "pattern": "*.zip",
//                     "target": "myrepo/${currentBuild.number}_${build_time}/${env.GIT_COMMIT}/",
//                     "props": "type=zip;status=ready"
//                 }
//             ]
//     }"""
env.downloadSpec = """{
            "files": [
                {
                    "pattern": "myrepo/${buildnumber}_*/*/**",
                    "target": "${WORKSPACE}/${env.JOB_BASE_NAME}/temp/",
                    "recursive": "true"
                }
            ]
    }"""