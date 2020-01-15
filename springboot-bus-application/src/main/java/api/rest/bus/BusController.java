package api.rest.bus;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class BusController {
    @RequestMapping("/bus/{station}/{nr}")
    public BusInfo bus(@PathVariable String station, @PathVariable String nr){
        int eta = getEtaForBus();
        BusInfo busInfo = new BusInfo(station, nr, eta);
        return busInfo;
    }

    private int getEtaForBus() {
        Random rn = new Random();
        int min = 1;
        int max = 10;
        return rn.nextInt(max - min + 1) + min;
    }
}
