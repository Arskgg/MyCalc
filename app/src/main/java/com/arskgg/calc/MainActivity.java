package com.arskgg.calc;

import android.content.Intent;
import android.media.VolumeShaper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    TextView resultsView;

    public enum Operation
    {
        ADD,SUBTRACT,DIVIDE,MULTIPLY,EQUAL
    }

    String runningNumber = "";
    String leftValueStr = "";
    String rightValueStr = "";
    Operation currentOperation = null;
    double result = 0;
    private boolean divideByZero = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button oneBtn = (Button)findViewById(R.id.oneBtn);
        Button twoBtn = (Button)findViewById(R.id.twoBtn);
        Button threeBtn = (Button)findViewById(R.id.threeBtn);
        Button fourBtn = (Button)findViewById(R.id.fourBtn);
        Button fiveBtn = (Button)findViewById(R.id.fiveBtn);
        Button sixBtn = (Button)findViewById(R.id.sixBtn);
        Button sevenBtn = (Button)findViewById(R.id.sevenBtn);
        Button eightBtn = (Button)findViewById(R.id.eightBtn);
        Button nineBtn = (Button)findViewById(R.id.nineBtn);
        Button zeroBtn = (Button)findViewById(R.id.zeroBtn);
        Button dotBtn = (Button)findViewById(R.id.dotBtn);

        ImageButton calcBtn = (ImageButton)findViewById(R.id.calcBtn);
        ImageButton divideBtn = (ImageButton)findViewById(R.id.divideBtn);
        ImageButton multiplyBtn = (ImageButton)findViewById(R.id.multiplyBtn);
        ImageButton subtractBtn = (ImageButton)findViewById(R.id.subtractBtn);
        ImageButton addBtn = (ImageButton)findViewById(R.id.addBtn);

        Button clearBtn = (Button)findViewById(R.id.clearBtn);
        resultsView = (TextView)findViewById(R.id.resultsText);


        resultsView.setText("0");

        oneBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                numberPressed("1");
            }

        });

        twoBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                numberPressed("2");
            }

        });

        threeBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                numberPressed("3");
            }

        });

        fourBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                numberPressed("4");
            }

        });

        fiveBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                numberPressed("5");
            }

        });

        sixBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                numberPressed("6");
            }

        });

        sevenBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                numberPressed("7");
            }

        });

        eightBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                numberPressed("8");
            }

        });

        nineBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                numberPressed("9");
            }

        });

        zeroBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                numberPressed("0");
            }

        });

        dotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberPressed(".");
            }
        });



        addBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                processOperation(Operation.ADD);
            }

        });

        subtractBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                processOperation(Operation.SUBTRACT);
            }

        });

        multiplyBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                processOperation(Operation.MULTIPLY);
            }

        });

        divideBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                processOperation(Operation.DIVIDE);
            }

        });


        clearBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                leftValueStr = "";
                rightValueStr = "";
                result = 0;
                runningNumber = "";
                currentOperation = null;
                resultsView.setText("0");
            }

        });

        calcBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                processOperation(Operation.EQUAL);

            }

        });
    }

    void processOperation(Operation operation)
    {

            if(currentOperation != null)
            {
                if(runningNumber != "")
                {
                    rightValueStr = runningNumber;
                    runningNumber = "";
                    switch (currentOperation)
                    {
                        case ADD:
                            result = Double.parseDouble(leftValueStr) + Double.parseDouble(rightValueStr);

                            break;
                        case SUBTRACT:
                            result = Double.parseDouble(leftValueStr) - Double.parseDouble(rightValueStr);
                            break;
                        case MULTIPLY:
                            result = Double.parseDouble(leftValueStr) * Double.parseDouble(rightValueStr);
                            break;
                        case DIVIDE:
                            if(Double.parseDouble(rightValueStr) == 0)
                            {
                                divideByZero = true;
                                result = 0;
                            }
                            else
                            {
                                result = Double.parseDouble(leftValueStr) / Double.parseDouble(rightValueStr);
                            }

                            break;
                    }

                    leftValueStr = formatDouble(result);

                    if(divideByZero)
                    {
                        resultsView.setText("You can't divide by 0");
                        divideByZero = false;
                    }
                    else
                    {
                        resultsView.setText(leftValueStr);

                    }
                }

            }

            else
            {
                if(runningNumber.isEmpty())
                leftValueStr = "0";
                else
                    leftValueStr = runningNumber;
                    runningNumber = "";
                    currentOperation = operation;
            }
            currentOperation = operation;

    }

    void numberPressed(String number)
    {


        if(runningNumber.isEmpty() && number.contains(".")) {
            runningNumber += "0" + number;
            resultsView.setText(runningNumber);
        }
        else
            if(!runningNumber.contains("."))
            {
                runningNumber += number;
                resultsView.setText(runningNumber);
            }
            else if(!number.contains("."))
            {
                runningNumber += number;
                resultsView.setText(runningNumber);
            }

    }

    public static String formatDouble(double d)
    {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);
    }

}
