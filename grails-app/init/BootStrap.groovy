import grails.converters.JSON
import sr_grails_dev_challenge.Customer
import sr_grails_dev_challenge.Number

class BootStrap {

    def init = { servletContext ->


        JSON.registerObjectMarshaller(Customer){
            def output = [:]
            output['id'] = it?.id
            output['name'] = it?.name
            output['numbers'] = it?.numbers?.number
            return output
        }
        JSON.registerObjectMarshaller(Number){
            def output = [:]
            output['id'] = it?.id
            output['number'] = it?.number
            output['active'] = it?.activate==0
            return output
        }
    }
    def destroy = {
    }
}
