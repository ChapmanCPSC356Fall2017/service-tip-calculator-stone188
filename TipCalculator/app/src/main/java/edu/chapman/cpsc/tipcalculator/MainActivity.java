package edu.chapman.cpsc.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SeekBar ratingBar;
    private Button subButton;
    private Button clrButton;
    private EditText resultTxt;
    private TextView numTxt;

    public double total;
    public double value;
    double preTotal;
    public float rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.ratingBar = (SeekBar) findViewById(R.id.rating_bar);
        this.subButton = (Button) findViewById(R.id.submt_btn);
        this.clrButton = (Button) findViewById(R.id.clr_btn);
        this.resultTxt = (EditText) findViewById(R.id.result_txt);
        this.numTxt = (TextView) findViewById(R.id.number);

        this.ratingBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                numTxt.setText(Integer.toString(progress));
                rate = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        this.subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str = resultTxt.getText().toString();

                if (str.matches("")) {
                    resultTxt.setText("ERROR: No value");
                    subButton.setEnabled(false);
                    subButton.setAlpha(.5f);
                }

                else {
                    total = Double.parseDouble(str);
                    String r = Float.toString(rate);
                    value = Double.parseDouble(r);

                    if (rate >= 1 && rate <= 3) {
                        preTotal = total * .1;
                        total = total + preTotal;
                    } else if (rate >= 4 && rate <= 5) {
                        preTotal = total * .13;
                        total = total + preTotal;
                    } else if (rate >= 6 && rate <= 7) {
                        preTotal = total * .15;
                        total = total + preTotal;
                    } else if (rate >= 8 && rate <= 9) {
                        preTotal = total * .2;
                        total = total + preTotal;
                    } else if (rate == 10) {
                        preTotal = total * .22;
                        total = total + preTotal;
                    }

                    total = Math.round(total * 100.0) / 100.0;
                    preTotal = Math.round(preTotal * 100.0) / 100.0;
                    String newPreTotal = String.valueOf(preTotal);
                    String newTotal = String.valueOf(total);
                    resultTxt.setText("Tip: $" + newPreTotal + " Total: $" + newTotal);
                    subButton.setEnabled(false);
                    subButton.setAlpha(.5f);
                }
            }
        });

        this.clrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTxt.setText("");
                numTxt.setText("");
                subButton.setEnabled(true);
                subButton.setAlpha(1f);
            }
        });
    }
}
