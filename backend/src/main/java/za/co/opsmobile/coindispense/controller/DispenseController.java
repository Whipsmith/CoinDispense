package za.co.opsmobile.coindispense.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import za.co.opsmobile.coindispense.CalculateChangeRequest;
import za.co.opsmobile.coindispense.ChangeResult;
import za.co.opsmobile.coindispense.worker.ChangeWorker;

/**
 * Created by Daniel Oosthuizen on 2015/09/13.
 */
@RestController
@RequestMapping("/dispense")
public class DispenseController {


    @RequestMapping("/ping")
    public String ping() {
        return "pong";
    }

    @RequestMapping(value = "/calculateChange",method = RequestMethod.POST, headers = "Accept=application/json")
    public ChangeResult calculateChange(@RequestBody CalculateChangeRequest calculateChangeRequest) {
        float change = calculateChangeRequest.getCost() - calculateChangeRequest.getPayment();
        return calculateDispense(change);
    }

    private ChangeResult calculateDispense(float change) {
        float[] denominations = new float[]{100f, 50f, 20f, 10f, 5f, 2f, 1f, .50f, .25f, .10f, .5f};
        return new ChangeWorker(denominations, change).calculateChange();
    }


}
