package sr_grails_dev_challenge

import grails.transaction.Transactional
import net.webservicex.TemperatureUnit

import  webservicexnet.Webservicexnet;


@Transactional
class TemperatureConvectorService {

    def convertTemperature(temp,fromUnit,toUnit){

        double resultTemp = 0.0
       try{
           double tempC = Double.parseDouble(temp)
           resultTemp =   Webservicexnet.conversionRate(tempC,fromUnit,toUnit)


       }catch (Exception ex){
          return [status: "error","result":ex.getMessage()]
       }
        return [status:"ok",result:resultTemp]
    }


    def allUnits(){
        def arrayUnits  = []

        for(c in TemperatureUnit.values()){
            arrayUnits.add(c.value())
        }

        return  arrayUnits
    }



}
