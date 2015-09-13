package za.co.opsmobile.coindispense.dispense.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

import za.co.opsmobile.coindispense.R;
import za.co.opsmobile.coindispense.dispense.store.Denomination;
import za.co.opsmobile.coindispense.dispense.view.PaymentOptionViewHolder;

/**
 * Created by Daniel Oosthuizen on 2015/09/08.
 */
public class PaymentOptionsAdapter extends RecyclerView.Adapter<PaymentOptionViewHolder> {

    private ArrayList<Denomination> denominations;
    private Comparator<? super Denomination> denominationComparator = new Comparator<Denomination>() {
        @Override
        public int compare(Denomination lhs, Denomination rhs) {
            if (lhs.getValue(1) > rhs.getValue(1)) {
                return 1;
            }
            if (lhs.getValue(1) == rhs.getValue(1)) {
                return 0;
            }
            return -1;
        }
    };

    public PaymentOptionsAdapter(ArrayList<Denomination> denominations) {
        if (denominations.size() > 0) {
            Collections.sort(denominations, denominationComparator);
        }
        this.denominations = denominations;
    }

    @Override
    public PaymentOptionViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Denomination.CurrencyTypes type = Denomination.CurrencyTypes.values()[viewType];
        switch (type) {
            case NOTE:
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_view, viewGroup, false);
                break;
            case COIN:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(PaymentOptionViewHolder paymentOptionViewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return denominations.size();
    }

    @Override
    public int getItemViewType(int position) {
        return denominations.get(position).getCurrencyType().ordinal();
    }

    public void setPaymentOptions(ArrayList<Denomination> validDenominations) {
        Collections.sort(validDenominations, denominationComparator);
        this.denominations = validDenominations;
        notifyDataSetChanged();
    }
}
