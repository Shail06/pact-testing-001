### CONTRACT TESTING USING PACT

1. Project Structure

    `spring-boot-bus-app`: A maven project for Spring based REST API to provide
     the Expected Time of Arrival (ETA) of a bus given a Station and the 
     Bus Number.
    
    `bus-client`: A maven project for CONSUMER, containing the Mock-Provider to generate
    the pacts. 
    
    