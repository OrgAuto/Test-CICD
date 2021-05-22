def workspace = "${WORKSPACE}"
def now = new Date()
def build_time = now.format("yyMMdd_HHmm", TimeZone.getTimeZone('PST'))
def buildName = Jenkins.instance.getItemByFullName("OrgAuto/Test/main")
env.buildnumber = buildName.getLastSuccessfulBuild().getNumber()
properties(
                [
                    parameters
                    (
                            [
                                string
                                        (
                                            defaultValue: env.buildnumber, 
                                            description: 'Build number', 
                                            name: 'BuildNo', 
                                            trim: false
                                        )
                            ]
                    )
                ]
            )


env.downloadSpec = """{
            "files": [
                {
                    "pattern": "myrepo/${params.BuildNo}_*/*/**",
                    "target": "${WORKSPACE}/${env.JOB_BASE_NAME}/temp/",
                    "recursive": "true"
                }
            ]
    }"""