package sr_grails_dev_challenge

class Customer {


    String name


    static hasMany = [numbers:Number]

    static constraints = {
        name nullable: false

    }
}
