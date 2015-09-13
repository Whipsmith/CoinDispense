package za.co.opsmobile.coindispense;

/**
 * Created by Daniel Oosthuizen on 2015/09/13.
 */
public class CalculateChangeRequest {
    private float cost;
    private float payment;

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getPayment() {
        return payment;
    }

    public void setPayment(float payment) {
        this.payment = payment;
    }
}
