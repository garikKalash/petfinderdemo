# petfinderdemo
Service helps to find animals from USA shelters


## Technology stack
  ### UI
    - Angular 13
  
  ### Backend 
    - Spring boot
    - Hibernate
    - PostgreSQL
    - Flyway integration
   #### API usage
      - https://petfinder-service.herokuapp.com/api/v1/pf
      - GET types - /types
      - GET organizations(count of 100) - /organization
      - GET animals - /search   please see params here https://www.petfinder.com/developers/v2/docs/#get-animals

   #### Design about storing data
      + Show how would you suggest saving the data and where?
      As you see I have stored all "static" data(animal types with their related values) in local relational DB and made some kind of cache for it, 
      because that filter criterias are not often chengable, as a result now all characteristics(not small data, only dog has 150 kind of breeds) load fast. 
      Also the downloading of types are very dynamic,if the API provides new values for types we will receive and add them automatically.
      
      I haven't stored any animal in this DEMO cause, decided to store that data into NoSQL db(there isn't any service to host free), 
      because we don't have big requests of updates and we should be ready for external structure changes, no-sql is better choice for unstructured and readable 
      data. Related to storing, I would suggest to schedule jobs and configurations for them, where we put some search criterias and in late hours our job will 
      load/update the specified animals, it will help us also to handle the repeat time for each query, so we can also prioritize the query-related data, 
      since API provides before/after parameters also we can take only data which is publish today or in 2 days or in some 
      concrete period.

      + Show how you would suggest reading data from the database?
      A small presentation already is implemented in view of Cache.
      Since our data contains some kind of "read-only" parts I prefer to work with Cache where it will be possible, reading the information of animals, 
      we should clarify which properties are must for us and save entities on our side only with that properties it will decrease payload sizes, 
      so reading every time all data from API not a good idea, 
      How to detect that we have already saved entries for your request?Here we can look up the config files of jobs for the downloading data and job 
      status ex. EXECUTED, FAILED, IN PROGRESS, if the query params match we read data from our side or not we will retrieve data from API, 
      I suggest also with every reading query send to server "needToSave" parameter, if it exists and query params don't match to any query in the job configs 
      we will write that query in the configs and will trigger a download job with that query on the next time. 

      



    
  ### Deployment
    - Heroku
