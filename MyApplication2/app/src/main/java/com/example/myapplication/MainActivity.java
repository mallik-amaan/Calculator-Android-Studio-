package com.example.calculator;
import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
public class MainActivity extends AppCompatActivity implements
        View.OnClickListener{
    TextView resulttext,answertext;
    MaterialButton
            b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bequals,bplus,bminus,bdivide,bclear,bopenbrea
    c,bclosebrac,bdot,bmultiply,bac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resulttext=findViewById(R.id.text_result);
        answertext=findViewById(R.id.text_answer);
        b0=findViewById(R.id.button_0);
        b0.setOnClickListener(this);
        b1=findViewById(R.id.button_1);
        b1.setOnClickListener(this);
        b2=findViewById(R.id.button_2);
        b2.setOnClickListener(this);
        b3=findViewById(R.id.button_3);
        b3.setOnClickListener(this);
        b4=findViewById(R.id.button_4);
        b4.setOnClickListener(this);
        b5=findViewById(R.id.button_5);
        b5.setOnClickListener(this);
        b6=findViewById(R.id.button_6);
        b6.setOnClickListener(this);
        b7=findViewById(R.id.button_7);
        b7.setOnClickListener(this);
        b8=findViewById(R.id.button_8);
        b8.setOnClickListener(this);
        b9=findViewById(R.id.button_9);
        b9.setOnClickListener(this);
        bequals=findViewById(R.id.button_equals);
        bequals.setOnClickListener(this);
        bplus=findViewById(R.id.button_plus);
        bplus.setOnClickListener(this);
        bminus=findViewById(R.id.button_minus);
        bminus.setOnClickListener(this);
        bmultiply=findViewById(R.id.button_multiply);
        bmultiply.setOnClickListener(this);
        bdivide=findViewById(R.id.button_divide);
        bdivide.setOnClickListener(this);
        bdot=findViewById(R.id.button_point);
        bdot.setOnClickListener(this);
        bclear=findViewById(R.id.button_C);
        bclear.setOnClickListener(this);
        bac=findViewById(R.id.button_clear);
        bac.setOnClickListener(this);
        bopenbreac=findViewById(R.id.button_openbracket);
        bopenbreac.setOnClickListener(this);
        bclosebrac=findViewById(R.id.button_closebracket);
        bclosebrac.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton) view;
 /* final MediaPlayer
mediaPlayer=MediaPlayer.create(this,R.raw.click);
 button.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View v) {
 mediaPlayer.start();
 }
 });*/
        String button_text=button.getText().toString();
        String equation=answertext.getText().toString();
        if(button_text.equals("=") && !equation.equals(""))
        {
            String result=getresult(equation);
            if(!result.equals("err"))
                resulttext.setText(result);
            else
                resulttext.setText("Error");
        }
        else if(button_text.equals("=") && equation.equals(""));
        else if(button_text.equals("AC"))
        {
            answertext.setText("");
            resulttext.setText("0");
            return;
        }
        else if(button_text.equals("C") && !equation.equals(""))
        {
            String equation2=equation.substring(0,equation.length()-1);
            answertext.setText(equation2);
        }
        else if(button_text.equals("C") && equation.equals(""));
        else
        {
            equation=equation+button_text;
            answertext.setText(equation);
        }
    }
    String getresult(String equation)
    { try {
        Context context=Context.enter();
        context.setOptimizationLevel(-1);
        Scriptable scriptable=context.initSafeStandardObjects();
        String
                finalresult=context.evaluateString(scriptable,equation,"Javascript",1,null)
                .toString();
        if (finalresult.endsWith(".0"))
        {
            finalresult=finalresult.replace(".0","");
        }
        else if(finalresult.equals("Infinity"))
        {
            finalresult="∞";
        }
        return finalresult;
    }
    catch (Exception e)
    {
        return "err";
    }
    }
}
