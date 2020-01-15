package api.rest.client;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class BusArrival {
    int port = 8111;

    public BusArrival() {
        System.out.println("Default port "+port);
    }

    public BusArrival(int port) {
        this.port = port;
        System.out.println("Custom port "+port);
    }

    public static void main(String[] args) {
        Integer eta = new BusArrival().checkEta("Connoly", "50");
        System.out.println("ETA = "+eta);
    }

    private Integer checkEta(String station, String nr) {
        try{
            String url = String.format("http://localhost:%d/bus/%s/%s", port, station, nr);
            System.out.println("URL: "+url);
            HttpResponse httpResponse = Request.Get(url).execute().returnResponse();
            String jsonResponse = EntityUtils.toString(httpResponse.getEntity());
            System.out.println("HTTP Response: "+ jsonResponse);
            JSONObject jsonObject = new JSONObject(jsonResponse);
            String eta = jsonObject.get("eta").toString();
            return new Integer(eta);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
