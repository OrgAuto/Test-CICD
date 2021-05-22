def workspace = "${WORKSPACE}"
def now = new Date()
def build_time = now.format("yyMMdd_HHmm", TimeZone.getTimeZone('PST'))
def buildName = Jenkins.instance.getItemByFullName("OrgAuto/Test/main")
env.buildnumber = buildName.getLastSuccessfulBuild().getNumber()
def buildnumberParam = ${params.BuildNo}
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
                    "pattern": "myrepo/${buildnumberParam}_*/*/**",
                    "target": "${WORKSPACE}/${env.JOB_BASE_NAME}/temp/",
                    "recursive": "true"
                }
            ]
    }"""