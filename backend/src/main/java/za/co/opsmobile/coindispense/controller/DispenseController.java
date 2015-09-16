package za.co.opsmobile.coindispense.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

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
        BigDecimal change = calculateChangeRequest.getPayment().subtract(calculateChangeRequest.getCost());
        return calculateDispense(change);
    }

    private ChangeResult calculateDispense(BigDecimal change) {
        BigDecimal[] denominations = new BigDecimal[]{
                new BigDecimal(100, new MathContext(3, RoundingMode.HALF_DOWN)),
                new BigDecimal(50, new MathContext(3, RoundingMode.HALF_DOWN)),
                new BigDecimal(20, new MathContext(3, RoundingMode.HALF_DOWN)),
                new BigDecimal(10, new MathContext(3, RoundingMode.HALF_DOWN)),
                new BigDecimal(5, new MathContext(3, RoundingMode.HALF_DOWN)),
                new BigDecimal(2, new MathContext(3, RoundingMode.HALF_DOWN)),
                new BigDecimal(1, new MathContext(3, RoundingMode.HALF_DOWN)),
                new BigDecimal(.5, new MathContext(3, RoundingMode.HALF_DOWN)),
                new BigDecimal(.25, new MathContext(3, RoundingMode.HALF_DOWN)),
                new BigDecimal(.1, new MathContext(2, RoundingMode.HALF_DOWN)),
                new BigDecimal(.05, new MathContext(1, RoundingMode.HALF_DOWN))
        };
        return new ChangeWorker(denominations, change).calculateChange();
    }


}
