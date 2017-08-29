package sr_grails_dev_challenge

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

class CustomerController  {
    static responseFormats = ['json', 'xml']
    static allowedMethods = [create: "POST", update: "PUT", delete: "DELETE", show: "GET"]


//testing for insert number
    def index(){
        Customer customer = new Customer(name:"mum")
        Number number = new Number( activate: Number.ACTIVE)
        number.number='03454722817'
        Number number1 = new Number( activate: Number.ACTIVE)
        number1.number="03454722818"
        customer.addToNumbers(number)
        customer.addToNumbers(number1)
        customer.save(flash:true)

        respond customer ,[status: OK]
    }

    def singleCustomerNumber(){
      def id = params?.getLong("id")

      Customer customer = Customer.findById(id)
        if(!customer){
            render status: NOT_FOUND
            return
        }

        respond customer.numbers?.number ,[status: OK]

    }


    def getAllNumbers(){
        respond Customer.getAll()?.numbers,[status: OK]
    }


    def setActivation(){
        def numberParam = params?.get("number")
        def status = params?.getBoolean("status")
        if(!numberParam){

            render  status: NOT_FOUND
            return
        }
        Number number = Number.findByNumber(numberParam?.toString())
        if(number){
            render  status: NOT_FOUND
            return
        }

        number.activate = status?0:1;

        respond number ,[status: OK]

    }








}
