package za.co.opsmobile.coindispense.dispense.store;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public class PaymentTransaction implements Parcelable {
    private final ArrayList<Payment> payments;

    public PaymentTransaction(ArrayList<Payment> payments) {
        this.payments = payments;
    }

    public PaymentTransaction(HashMap<Denomination, Integer> paymentSet) {
        this.payments = new ArrayList<>();
        for (Denomination denomination : paymentSet.keySet()) {
            Integer count = paymentSet.get(denomination);
            if (count.compareTo(0) > 0) {
                payments.add(new Payment(count, denomination));
            }
        }

    }


    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public double getValue() {
        return sumPayments();
    }

    private double sumPayments() {
        double total = 0;
        for (Payment payment : payments) {
            total += payment.getValue();
        }
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentTransaction that = (PaymentTransaction) o;

        return payments.equals(that.payments);

    }

    @Override
    public int hashCode() {
        return payments.hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(payments);
    }

    protected PaymentTransaction(Parcel in) {
        this.payments = in.createTypedArrayList(Payment.CREATOR);
    }

    public static final Parcelable.Creator<PaymentTransaction> CREATOR = new Parcelable.Creator<PaymentTransaction>() {
        public PaymentTransaction createFromParcel(Parcel source) {
            return new PaymentTransaction(source);
        }

        public PaymentTransaction[] newArray(int size) {
            return new PaymentTransaction[size];
        }
    };
}
