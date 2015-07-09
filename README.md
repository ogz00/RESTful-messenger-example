# messengerRESTApi

--This is Basic Rest Service example with GET, POST, DELETE and UPDATE methods.

--I suggest you use 'PostMan REST Client Api' for testing Http Request and Responses.

--It include 3 models they are: Profile, Comments and Message. Comments model is depend on message id. So that
you can find usage example of the Path and PathVariable annotations

--Http Responses are JSON--

#Usage of RestApi on the messages

--Do Run on server application or do mvn clean install for create war package on the target directory. Then copy war package to webapp directory of the tomcat. I prefer this method. You can use jetty-plugin or something different from me. Anyway, Firstly Just run application.

--Open the PostMan and set header like:

    --authSessionID : "something"
    --Content-Type  : "application/json"
    
    --GET Method:
    
  --http://localhost:8080/messengerRestApi/webapi/messages/
    
--Usage of POST method on the messages:

  --http://localhost:8080/messengerRestApi/webapi/messages/
  
    --And create message in raw editor with like:
      --
        {
          "author": "OGUZHAN",
          "message": "HELLO WORLD NEW MESSAGE"
        }
      --
      
--With similar method you can GET and POST commit like that:

  --For get all comments of message 2
    
    --http://localhost:8080/messengerRestApi/webapi/messages/2/comments/
    
    --response should be like: 
      
      {
        "author": "OGUZHAN COM",
        "comment": "HELLO WORLD COMMENT",
        "created": "2015-06-10T09:55:00.433",
        "id": 1
      },
      
      {
        "author": "OGUZHAN COM",
        "comment": "HELLO WORLD COMMENT JERSEY",
        "created": "2015-06-10T09:55:00.433",
        "id": 2
      }
      
  --Just get first comment of the second messages is:
  
    --http://localhost:8080/messengerRestApi/webapi/messages/1/comments/1
    
-- POST comment to first message

  --http://localhost:8080/messengerRestApi/webapi/messages/1/comments/
  
  --then edit Raw editor like:
  
    {
      "author": "OGUZHAN COM",
      "comment": "HELLO WORLD COMMENT JERSEY POST"
    }
    
    --It should be response
    
      {
        "author": "OGUZHAN COM",
        "comment": "HELLO WORLD COMMENT JERSEY POST",
        "created": "2015-06-10T10:06:34.988",
        "id": 3
      }
    
    --Now you have another comment which id is 3 and its depend on the message 1 .
    
