package sr_grails_dev_challenge

import grails.transaction.Transactional
import grails.web.context.ServletContextHolder
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import org.grails.web.util.WebUtils
import org.hibernate.SessionFactory
import org.springframework.context.ApplicationContext
import org.springframework.web.multipart.MultipartFile

@Transactional
class UserService {

    def uploadUser(file) {
        def groovyFile
        ArrayList<String> line
        def count = 0;
        try {
         //  def session = getSession()
            groovyFile = multipartToFile(file)
            List<User> users = new ArrayList<User>()
            groovyFile.eachLine {
                count++
                line = it.split(',').toList()
                if(line.size()<3){
                    return [status:"error","line":count,"message":"On line this ${count}: Invalid number of arguments"]
                }
                else{
                    try{
                        User user  = new User(id:line?.get(0)?.toInteger(),amountCoins: line?.get(1)?.toInteger()
                                ,name: line?.get(2))
                        user.id =line?.get(0)?.toInteger()
                        if(user.validate()){// this will verify the uniqueness and other validation contraint in domain class
                            users.add(user)

                        }
                        else{
                            return [status:"error","line":count,"message":"On line ${count}: User invalid and reason is "+user.errors]
                        }
                    }catch (Exception ex){
                        return [status:"error","line":count,"message":"On line ${count}: User invalid and reason is "+ex.getMessage()]
                    }
                }

            }

            User.saveAll(users)
           // session?.flush()

            return [status:"ok","message":"Upload contact successfully"]
        }catch (Exception ex){
            log.error("Error while uploading file and error is  "+ ex.getMessage())
            return [status:"error","message":"Something went wrong please see error logs"]
        }
    }
    def static multipartToFile(multipart)
    {
        File convFile = new File(multipart?.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipart?.getBytes());
        fos.close();
        return convFile;
    }

   
}
