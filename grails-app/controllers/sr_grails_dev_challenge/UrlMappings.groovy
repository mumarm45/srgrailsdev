package sr_grails_dev_challenge

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller:"temperatureConvector",action:  "index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
