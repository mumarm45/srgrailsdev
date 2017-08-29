package sr_grails_dev_challenge


class User {

    int amountCoins
    String name

    static constraints = {
        id size:1..10, matches:"^\\d{1,10}\$",unique: true
        amountCoins   matches:"^[1-9]+\$", blank:false
        name nullable: false,blank: false
    }
    static mapping = {
        id generator:'assigned'
    }
}
