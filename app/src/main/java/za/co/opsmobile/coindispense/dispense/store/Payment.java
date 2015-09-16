package za.co.opsmobile.coindispense.dispense.store;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public class Payment implements Parcelable {

    private final int count;
    private final Float denomination;

    public Payment(int count, Float denomination) {
        this.count = count;
        this.denomination = denomination;
    }

    public Float getDenomination() {
        return denomination;
    }

    public float getValue() {
        return denomination * count;
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
        dest.writeValue(this.denomination);
    }

    protected Payment(Parcel in) {
        this.count = in.readInt();
        this.denomination = (Float) in.readValue(Float.class.getClassLoader());
    }

    public static final Creator<Payment> CREATOR = new Creator<Payment>() {
        public Payment createFromParcel(Parcel source) {
            return new Payment(source);
        }

        public Payment[] newArray(int size) {
            return new Payment[size];
        }
    };

    public int getCount() {
        return count;
    }

    public static class Comparator implements java.util.Comparator<Payment> {

        @Override
        public int compare(Payment lhs, Payment rhs) {
            return ((Float)lhs.getValue()).compareTo(((Float)rhs.getValue()));
        }
    }

    public static class ReverseComparator implements java.util.Comparator<Payment> {

        @Override
        public int compare(Payment lhs, Payment rhs) {
            return ((Float)rhs.getValue()).compareTo(((Float)lhs.getValue()));
        }
    }
}
