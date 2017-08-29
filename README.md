# Challenge number 4

There are three service 
1) Single Customer numbers
2) Get All numbers
3) Activate a number

#Single Customer numbers

Url =  /customer/singleCustomerNumber/{id}

1) Request 
            
            id = Customer id 
2) Response

       404 not found = if no customer found

       200 = if customer found plus array of numbers
       [
                   "03454722817",
                    "03454722818"
        ]
                                                    

#Get all numbers

Url =  /customer/getAllNumbers

2) Response

       200 =Array list of all numbers
        [
               {
                   "id": 1,
                   "number": "03454722817",
                   "active": true
               },
               {
                   "id": 2,
                   "number": "03454722818",
                   "active": true
               }
           ]
           
 #Get all numbers
  Url =  /customer/setActivation
  
  method = post
  
  1) Request parameters
           
           number = mandatory|string
           status = mandatory| true||false
   
           
  2) Response
                         
            404 not found = if number not specified and 
            number not found in database
            
            200 = if number found change the staus 
             
       
           
                  
                     