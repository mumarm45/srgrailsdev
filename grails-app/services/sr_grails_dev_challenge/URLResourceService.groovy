package sr_grails_dev_challenge

import grails.plugins.rest.client.RestBuilder
import org.springframework.beans.factory.annotation.Value

class URLResourceService {

    @Value('${googleAPi.url}') // read url from yml or properties file
    String restUri
    @Value('${googleAPi.key}') // read key from yml or properties file change that key, currently activated against my id on google.
    String key

    def shorten(String url) {
        def responseResult= [:]
        def rest = new RestBuilder(connectTimeout:10000, readTimeout:20000)
        def urlResource  = URLResource.findByLongUrl(url)
        try{
            if(urlResource){
                responseResult.put("status","ok")
                responseResult.put("shortUrl",urlResource?.shortUrl)
                responseResult.put("longUrl",urlResource?.longUrl)
            }else{
                if(!restUri && !key){
                    responseResult.put("status","error")
                    responseResult.put("message","Please specify google url and key in yml or properties file")
                    return  responseResult
                }
                def resp = rest.post("${restUri}?key=${key}") {
                    contentType "application/json"
                    json {
                        name = "test-group"
                        longUrl = url
                    }
                }
                if(200.equals(resp.getStatus())){
                    def srtUrl = resp?.json?.id
                    // if the url is already in database, then no need to create any Url Resource
                    urlResource = new URLResource(longUrl: url
                                ,shortUrl: resp.json.id)

                        urlResource.save(flush:true)
                    responseResult.put("status","ok")
                    responseResult.put("shortUrl",urlResource?.shortUrl)
                    responseResult.put("longUrl",urlResource?.longUrl)
                }
                else{
                    responseResult.put("status","error")
                    responseResult.put("statusCode",resp.getStatus())
                    log.error("Error while converting the url to short and the error is "+resp.getBody())
                    responseResult.put("message","Error while converting the url to short")

                }
            }

        }catch (Exception ex){
            log.error("Error while converting the url to short and the error is "+ex.getMessage())
            println("Error while converting the url to short and the error is "+ex.getMessage())
            responseResult.put("status","error")
            responseResult.put("message","Error while converting the url to short,see logs for more details")
        }

        return responseResult



    }


    def expand(String url){
        def responseResult= [:]
        def rest = new RestBuilder(connectTimeout:10000, readTimeout:20000)
        def urlResource  = URLResource.findByShortUrl(url)
        try{
            if(urlResource){
                responseResult.put("status","ok")
                responseResult.put("longUrl",urlResource?.longUrl)
                responseResult.put("short",urlResource?.shortUrl)
            }
            else{
                if(!restUri && !key){
                    responseResult.put("status","error")
                    responseResult.put("message","Please specify google url and key in yml or properties file")
                    return  responseResult
                }
                def resp = rest.get("${restUri}?key=${key}&shortUrl="+url) {
                    contentType "application/json"
                }
                if(200.equals(resp.getStatus())){
                    def srtUrl = resp?.json?.id

                    urlResource = new URLResource(longUrl: resp?.json?.longUrl
                            ,shortUrl: srtUrl)

                    urlResource.save(flush:true)

                    responseResult.put("status","ok")
                    responseResult.put("longUrl",urlResource?.longUrl)
                    responseResult.put("short",urlResource?.shortUrl)
                }
                else{
                    log.error("Error while converting the url to expand and the error is "+resp.getBody())
                    responseResult.put("status","error")
                    responseResult.put("statusCode",resp.getStatus())
                    responseResult.put("statusCode",resp.getStatus())
                    responseResult.put("errors",resp.getText())
                    responseResult.put("message","Error while converting the url to expand")

                }
            }

        }catch (Exception ex){
            log.error("Error while converting the url to expand and the error is "+ex.getMessage())
            println("Error while converting the url to expand and the error is "+ex.getMessage())
            responseResult.put("status","error")
            responseResult.put("message","Error while converting the url to expand,see logs for more details")
        }

        return responseResult
    }

}
