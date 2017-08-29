# Challenge number 1

There are three service 
1) Short Url
2) Expand url
3) Activate a number

#Short url 

Url =  /urlResource/shortUrl?url={url}

1) Request 
            
            url = full url to be shorten
2) Response

       url empty = Please provide url

       200 = if customer found plus array of numbers
       {
       "status":"ok",
       "shortUrl":"",
       "longUrl":""
       }
       OR
       {
       "status":"error",
       message:"Error while converting the url to short and the error is"
       }
                                                    

#Expand url

Url =  /urlResource/expandUrl?url={url}
1) Request 
            
            url = full url to be shorten

2) Response

       url empty = Please provide url
     
            200 = if customer found plus array of numbers
            {
            "status":"ok",
            "shortUrl":"",
            "longUrl":""
            }
            OR
            {
            "status":"error",
            message:"Error while converting the url to short and the error is"
            }
           
 
             
       
           
                  
                     