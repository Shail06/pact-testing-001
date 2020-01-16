package api.rest.client;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;

import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

public class BusArrivalTest {
    @Rule
    public PactProviderRuleMk2 provider = new PactProviderRuleMk2("BusService", "localhost", 8112, this);

    @Pact(consumer = "BusServiceClient")
    public RequestResponsePact createPact(PactDslWithProvider builder){
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        DslPart etaResults = new PactDslJsonBody()
                .stringType("station", "Connoly")
                .stringType("nr", "14")
                .integerType("eta", 4)
                .asBody();

        return builder.given("Bus number 14 at Connoly Station")
                .uponReceiving("Request for Bus number 14 at Connoly Station")
                .path("/bus/Connoly/14")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body(etaResults).toPact();
    }

    /**
     * Represents the test with Mock Provider
     */
    @Test
    @PactVerification
    public void busArrivalTest(){
        System.setProperty("pact.RootDir", "target/pacts"); // This statement somehow doesn't work!
        Integer eta = new BusArrival(provider.getPort()).checkEta("Connoly", "14");
        System.out.println("TEST ETA = "+ eta);
        assertTrue(eta >= 0 );
    }
}
