package za.co.opsmobile.coindispense.dispense.store;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public class Payment implements Parcelable {

    private final int count;
    private final Denomination denomination;

    public Payment(int count, Denomination denomination) {
        this.count = count;
        this.denomination = denomination;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public double getValue() {
        return denomination.getValue(count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (count != payment.count) return false;
        return denomination.equals(payment.denomination);

    }

    @Override
    public int hashCode() {
        int result = count;
        result = 31 * result + denomination.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.count);
        dest.writeParcelable(this.denomination, 0);
    }

    protected Payment(Parcel in) {
        this.count = in.readInt();
        this.denomination = in.readParcelable(Denomination.class.getClassLoader());
    }

    public static final Parcelable.Creator<Payment> CREATOR = new Parcelable.Creator<Payment>() {
        public Payment createFromParcel(Parcel source) {
            return new Payment(source);
        }

        public Payment[] newArray(int size) {
            return new Payment[size];
        }
    };
}
