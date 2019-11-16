package com.example.game;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/*
General comments:
This app is designed to be played as an anagram transformer game with 3 levels.
the first level will be changing "tea" into "eat", the second changing "save" into vase",
and the third changing "listen" into "silent". All the tranformation can only be achieved
through 3 types of command: push, pop, and pass. As you can see in the app's interface, the
player has a stack container besides the original string queue. "Push" will push the first leaving instance
from the remaining letter queue into the stack, and "pop" will print the leaving node(letter) following
the printed ones. "Pass" will print the letter from the original queue directly.


 */
public class MainActivity extends AppCompatActivity {
    Button pushB = (Button) findViewById(R.id.pushButton);
    Button popB = (Button) findViewById(R.id.popBotton);
    Button passB = (Button) findViewById(R.id.passButton);
    Button resetB = (Button) findViewById(R.id.resetButton);
    final TextView targetT = (TextView) findViewById(R.id.targetText);
    final TextView bankT = (TextView) findViewById(R.id.bankText);
    TextView stackT = (TextView) findViewById(R.id.stackText);
    final TextView printT = (TextView) findViewById(R.id.printText);

    Stack bank = new Stack();//bank is the extra available stack to hold moving letters
    Queue print = new Queue();//print is the final product that will be compared to the target anagram

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        targetT.setText("eat");
        bankT.setText("tea");
        pushB.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        if(bankT.getText().toString().length()>=1){
                            Queue remains= new Queue();
                            for(int i =0; i< bankT.getText().toString().length(); i++){
                                remains.enqueue(new Node(bankT.getText().toString().substring(i,i+1)));
                            }
                            String letter = bankT.getText().toString().substring(0,1);
                            bank.push(new Node(letter));
                            remains.dequeue();
                            stackT.setText(bank.display());
                            bankT.setText(remains.display());
                        }

                    }
                }
        );
        popB.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        if (stackT.getText().toString().length() >= 1) {
                            String letter = (String) bank.pop().getData();
                            print.enqueue(new Node(letter));
                            printT.setText(print.display());
                            stackT.setText(bank.display());
                            if(print.display().equals(targetT.getText().toString())){
                                if(targetT.getText().equals("eat")){
                                    bankT.setText("save");
                                    targetT.setText("vase");
                                    Stack bank = new Stack();
                                    Queue print = new Queue();
                                }
                                else if(targetT.getText().equals("vase")){
                                    bankT.setText("listen");
                                    targetT.setText("silent");
                                    Stack bank = new Stack();
                                    Queue print = new Queue();
                                }
                                else{
                                    bankT.setText("Win!!!");
                                }
                            }
                        }
                    }
                }
        );
        passB.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        if(bankT.getText().toString().length()>=1){
                        String letter = bankT.getText().toString().substring(0,1);
                      print.enqueue(new Node(letter));
                      printT.setText(print.display());
                    }
                        if(targetT.getText().equals("eat")){
                            bankT.setText("save");
                            targetT.setText("vase");
                            Stack bank = new Stack();
                            Queue print = new Queue();
                        }
                        else if(targetT.getText().equals("vase")){
                            bankT.setText("listen");
                            targetT.setText("silent");
                            Stack bank = new Stack();
                            Queue print = new Queue();
                        }
                        else{
                            bankT.setText("Win!!!");
                        }
                    }

                }
        );
        resetB.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        TextView bankT = (TextView) findViewById(R.id.bankText);

                        if(targetT.getText().equals("eat")){
                            bankT.setText("tea");
                            Stack bank = new Stack();
                            Queue print = new Queue();
                        }
                        else if(targetT.getText().equals("vase")){
                            bankT.setText("save");
                            Stack bank = new Stack();
                            Queue print = new Queue();
                        }
                        else{
                            bankT.setText("listen");
                            Stack bank = new Stack();
                            Queue print = new Queue();
                        }

                    }
                }
        );


    }

}
