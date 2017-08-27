package sr_grails_dev_challenge

import grails.converters.JSON

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class URLResourceController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    def URLResourceService

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        
        respond URLResource.list(params), model:[URLResourceCount: URLResource.count()]
    }
    def index(Integer max) {
        respond view: 'index'
    }
    

    @Transactional
    def shortUrl(){


        def url = params?.url
        if(url){
            def respo = URLResourceService.shorten(url)
            render respo as JSON
        }
        else
            render "Please provide url"

    }

    @Transactional
    def expandUrl(){


        def url = params?.url
        if(url){
            def respo = URLResourceService.expand(url)
            render respo as JSON
        }
        else
            render "Please provide url"

    }

    def show(URLResource URLResource) {
        respond URLResource
    }



    @Transactional
    def delete(URLResource URLResource) {

        if (URLResource == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        URLResource.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'URLResource.label', default: 'URLResource'), URLResource.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'URLResource.label', default: 'URLResource'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
