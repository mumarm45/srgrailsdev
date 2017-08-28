package tempratureConvert

import grails.converters.JSON

class TemperatureConvectorController {

    def temperatureConvectorService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    def index() {

        respond view: 'index',[unitsArrays:temperatureConvectorService.allUnits()]


    }

    def convertTemperate(){
        render temperatureConvectorService.convertTemperature(params?.temp,params?.fromUnit,params.toUnit) as JSON
    }

}
