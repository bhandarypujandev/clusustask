---
__Task :)__

- Request Fields(Deal Unique Id, From Currency ISO Code "Ordering Currency", To Currency ISO Code, Deal timestamp, Deal Amount in ordering currency).
- Validate row structure.(e.g: Missing fields, Type format..etc. We do not expect you to cover all possible cases but we'll look to how you'll implement validations)
- System should not import same request twice.
- No rollback allowed, what every rows imported should be saved in DB.

---


## Context Path
/clusus

File upload endpoint

    // Upload Csv
    Method:POST
    Path:/upload
    contentType:multipart/form-data

Success

    success
    

Some Errors

    "message": "File Already Uploaded",
    "dateTime": "2022-11-02T02:02:39.4740863",
    "code": "13"


    "message":"File is Invalid ! Please upload valid file 
     csv","dateTime":"2022-11-02T02:38:49.6446855","code":null


Since No rollback allowed, what every rows imported should be saved in DB, validation messages are maintained in Logs.

    



