package com.ualr.layoutassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private boolean isNumber(String val) {
        for (int i = 0; i < val.length(); i++) {
            char c = val.charAt(i);
            if (!((c > '0' && c < '9') || c == '.'))
                return false;
        }
        return true;
    }

    public void calculateTotal(View view) {
        LinearLayout invoiceList = findViewById(R.id.invoiceList);
        int product_count = invoiceList.getChildCount();
        float total = 0;
        for (int i = 2; i < product_count; i++) {
            LinearLayout layout = (LinearLayout)invoiceList.getChildAt(i);
            MaterialCheckBox checkBox  = layout.findViewById(R.id.checkBox);
            if (checkBox.isChecked()) {
                TextInputEditText editText = layout.findViewById(R.id.textInputEditText);
                String str_value = editText.getEditableText().toString();
                if (str_value != "" && isNumber(str_value)) {
                    float float_value = Float.parseFloat(str_value);
                    total += float_value;
                }
            }
        }
        // Apply Discount if Checked
        MaterialButtonToggleGroup buttonGroup = findViewById(R.id.discountButtonGroup);
        int i = buttonGroup.getCheckedButtonId();
        if (i == R.id.discount_button) total *= 0.75;
        TextView textViewTotal = findViewById(R.id.textViewTotalValue);
        textViewTotal.setText("$ "+Float.toString(total));
    }
}