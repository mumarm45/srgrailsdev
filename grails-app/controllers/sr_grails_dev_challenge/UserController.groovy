package sr_grails_dev_challenge

import grails.converters.JSON
import org.apache.commons.io.FilenameUtils

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def userService
    def index(Integer max) {
        respond view: 'index'
    }

    @Transactional
    def uploadBulkCallerSync() {
        def respo = []
        def csvFile = params?.file
        if (csvFile!=null & csvFile!="") {
            String fileExtension = FilenameUtils.getExtension(csvFile?.getOriginalFilename())
            if (!"csv".equalsIgnoreCase(fileExtension) && !"txt".equalsIgnoreCase(fileExtension)) {
                respo.add(["statusString": "Please choose only csv files!."])
                respond respo, [status: NOT_ACCEPTABLE]
            } else {
                def results = userService.uploadUser(csvFile)
                 render results as JSON

            }

        } else {
            respo.add(["statusString": "File is empty."])
            respond respo, [status: NOT_ACCEPTABLE]
        }
    }



    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
