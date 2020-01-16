package api.rest.verifier;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import org.junit.runner.RunWith;

@RunWith(PactRunner.class)
@Provider("BusService")
@PactFolder("target/pacts")
public class BusStopContractTest {

    @State("Bus number 14 at Connoly Station")
    public void ConnolyStation(){
        System.out.println("Bus number 14 at Connoly Station");
    }

    @TestTarget
    public final Target target = new HttpTarget(8111);
}
