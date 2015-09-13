package za.co.opsmobile.coindispense.dispense.store;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public class Denomination implements Parcelable {

    private final float value;

    public CurrencyTypes getCurrencyType() {
        return currencyType;
    }

    private final CurrencyTypes currencyType;

    public Denomination(float value, CurrencyTypes currencyType) {
        this.value = value;
        this.currencyType = currencyType;
    }

    public double getValue(int count) {
        return value * count;
    }


    public enum CurrencyTypes {
        NOTE,
        COIN
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Denomination that = (Denomination) o;

        if (Double.compare(that.value, value) != 0) return false;
        return currencyType == that.currencyType;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(value);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + currencyType.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.value);
        dest.writeInt(this.currencyType == null ? -1 : this.currencyType.ordinal());
    }

    protected Denomination(Parcel in) {
        this.value = in.readFloat();
        int tmpCurrencyType = in.readInt();
        this.currencyType = tmpCurrencyType == -1 ? null : CurrencyTypes.values()[tmpCurrencyType];
    }

    public static final Parcelable.Creator<Denomination> CREATOR = new Parcelable.Creator<Denomination>() {
        public Denomination createFromParcel(Parcel source) {
            return new Denomination(source);
        }

        public Denomination[] newArray(int size) {
            return new Denomination[size];
        }
    };
}
