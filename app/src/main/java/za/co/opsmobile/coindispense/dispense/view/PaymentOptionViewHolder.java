package za.co.opsmobile.coindispense.dispense.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import za.co.opsmobile.coindispense.R;
import za.co.opsmobile.coindispense.dispense.action.DispenseActionCreator;

/**
 * Created by Daniel Oosthuizen on 2015/09/08.
 */
public class PaymentOptionViewHolder extends RecyclerView.ViewHolder {


    @Bind(R.id.value)
    TextView value;

    private Float denomination;
    private DispenseActionCreator actionCreator;

    public PaymentOptionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Float denomination, DispenseActionCreator actionCreator) {
        this.denomination = denomination;
        this.actionCreator = actionCreator;
        DecimalFormat format = null;
        if (denomination >= 1) {
            format = new DecimalFormat("R ###");
        } else {
            format = new DecimalFormat("#.##c");
            format.setMaximumFractionDigits(2);
            format.setMinimumFractionDigits(2);
            format.setMaximumIntegerDigits(0);
        }

        value.setText(format.format(denomination));
    }

    @OnClick(R.id.card)
    public void onClick() {
        actionCreator.addPayment(denomination);
    }
}
