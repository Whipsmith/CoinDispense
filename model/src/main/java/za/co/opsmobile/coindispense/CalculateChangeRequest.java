package za.co.opsmobile.coindispense;

import java.math.BigDecimal;

/**
 * Created by Daniel Oosthuizen on 2015/09/13.
 */
public class CalculateChangeRequest {
    private BigDecimal cost;
    private BigDecimal payment;

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }
}
