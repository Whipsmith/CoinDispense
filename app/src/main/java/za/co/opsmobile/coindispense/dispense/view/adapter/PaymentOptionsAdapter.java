package za.co.opsmobile.coindispense.dispense.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import za.co.opsmobile.coindispense.R;
import za.co.opsmobile.coindispense.dispense.action.DispenseActionCreator;
import za.co.opsmobile.coindispense.dispense.view.PaymentOptionViewHolder;

/**
 * Created by Daniel Oosthuizen on 2015/09/08.
 */
public class PaymentOptionsAdapter extends RecyclerView.Adapter<PaymentOptionViewHolder> {

    private ArrayList<Float> denominations;

    private DispenseActionCreator actionCreator;

    public PaymentOptionsAdapter(ArrayList<Float> denominations, DispenseActionCreator actionCreator) {
        this.actionCreator = actionCreator;
        if (denominations.size() > 0) {
            Collections.sort(denominations);
        }
        this.denominations = denominations;
    }

    @Override
    public PaymentOptionViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.denomination_view, viewGroup, false);
        return new PaymentOptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PaymentOptionViewHolder paymentOptionViewHolder, int position) {
        paymentOptionViewHolder.bind(denominations.get(position), actionCreator);
    }

    @Override
    public int getItemCount() {
        return denominations.size();
    }


    public void setPaymentOptions(ArrayList<Float> validDenominations) {
        Comparator reverseComparator = Collections.reverseOrder();
        Collections.sort(validDenominations, reverseComparator);

        this.denominations = validDenominations;
        notifyDataSetChanged();
    }
}
