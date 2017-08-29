package sr_grails_dev_challenge

class Number {
    final static Integer ACTIVE = 0
    final static Integer DE_ACTIVE = 1

    String number
    Integer activate

    static belongsTo = [customer:Customer]
    static constraints = {
        number matches:"^\\d{10,11}\$"
        activate default:0
    }
}
