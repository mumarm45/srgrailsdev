package sr_grails_dev_challenge


class User {

    int amountCoins
    String name

    static constraints = {
        id size:10..11, matches:"[0-9]+",unique: true
        amountCoins   matches:"[1-9]+", blank:false
        name nullable: false,blank: false
    }
    static mapping = {
        id generator:'assigned'
    }
}
