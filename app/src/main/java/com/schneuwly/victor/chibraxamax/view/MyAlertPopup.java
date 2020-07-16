package com.schneuwly.victor.chibraxamax.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.schneuwly.victor.chibraxamax.R;

import java.util.Objects;

/**
 * <class description>
 *
 * @author Victor Schneuwly
 */
public class MyAlertPopup extends Dialog {
    private TextView title_view, message_view, butt1, butt2;

    public MyAlertPopup(@NonNull Context context) {
        super(context);
        this.setContentView(R.layout.alert_popup);

        title_view = this.findViewById(R.id.popup_title);
        message_view = this.findViewById(R.id.popup_message);

        butt1 = this.findViewById(R.id.popup_button_1);
        butt2 = this.findViewById(R.id.popup_button_2);


        butt1.setVisibility(View.GONE);
        butt1.setVisibility(View.GONE);

        Objects.requireNonNull(this.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public MyAlertPopup setTitle(String title) {
        title_view.setText(title);
        return this;
    }

    public MyAlertPopup setMessage(String message) {
        message_view.setText(message);
        return this;
    }

    public MyAlertPopup setButton(String text, View.OnClickListener listener) {
        butt1.setVisibility(View.VISIBLE);
        butt1.setText(text);
        butt1.setOnClickListener(listener);

        return this;
    }

    public MyAlertPopup setButton2(String text, View.OnClickListener listener) {
        butt2.setVisibility(View.VISIBLE);
        butt2.setText(text);
        butt2.setOnClickListener(listener);

        return this;
    }

}
