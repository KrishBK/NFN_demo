package com.example.vishnuprabha.nfn_demo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.Stack;

import static com.example.vishnuprabha.nfn_demo.Splash.i;
import static com.example.vishnuprabha.nfn_demo.Splash.q;
import static com.example.vishnuprabha.nfn_demo.Splash.v;


public class MainActivity extends AppCompatActivity {
    Stack stack=new Stack();
    TextView tv;
    Button s,n,re;
    ListView sl,se;
    int c=0;
    static int r;
    static String selected;
    static ArrayAdapter<String> adapter;
    static ArrayList<String> f=new ArrayList<String>();
    ListView l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
       tv=(TextView)findViewById(R.id.qs);
        sl= (ListView) findViewById(R.id.sl);
        se= (ListView) findViewById(R.id.se);
        s=(Button) findViewById(R.id.skip);
        n=(Button) findViewById(R.id.next);
        re= (Button) findViewById(R.id.reset);
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);

        view(1);

        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f.removeAll(f);
                Intent open = new Intent(MainActivity.this, MainActivity.class);
                startActivity(open);
            }
        });

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    r = (Integer) stack.pop();
                }
                catch (Exception e){
                    Log.d("Exception",e.toString());
                }
                Log.d("Initial Pop", "Poping " + r);
                if (stack.empty()) {
                    Intent open = new Intent(MainActivity.this, SumActivity.class);
                    startActivity(open);
                }
                else {
                    r = (int) stack.peek();
                    Log.d("ViewPeek", "Peek " + r);
                    selected = "";
                    view(r);
                }    }


        });
        n.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                try{
                    r = (Integer) stack.pop();
                }
                catch (Exception e){
                    Log.d("Exception",e.toString());
                }
                Log.d("Initial Pop", "Poping " + r);
                Log.d("Spiner", "value" + selected);
                if (!selected.equals("No")) {
                    //  Log.d("check",""+(isContains(Integer.parseInt(1 + "" + stack.peek()))));
                    if (isContains(Integer.parseInt(1 + "" + r)) != 100) {
                        Log.d("Pushing", "Pushing 1" + r);
                        stack.push(Integer.parseInt(1 + "" + r));
                    }
                }
                if (stack.empty()) {
                    if (r == 15)
                        f.add(v[4] + selected);
                    set();
                    Intent open = new Intent(MainActivity.this, SumActivity.class);
                    startActivity(open);

                }
                else {
                    if (r == 11)
                        f.add(v[0] + selected);
                    if (r == 12)
                        f.add(v[1] + selected);
                    if (r == 13)
                        f.add(v[2] + selected);
                    if (r == 14)
                        f.add(v[3] + selected);
                    if (r == 15)
                        f.add(v[4] + selected);
                    if (r == 112)
                        f.add(v[5]);
                    set();
                    r = (int) stack.peek();
                    Log.d("ViewPeek", "Peek " + r);
                    selected="";
                    view(r);

                }
            }

        }  );
        sl.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                for (int i = 0; i < sl.getChildCount(); i++) {
                    if(position == i ){
                        sl.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.r1));
                    }else{
                        sl.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }
                String  itemValue    = (String) sl.getItemAtPosition(position);
                selected=itemValue;

            }



        });


    }
  public int isContains(int c){
      for(int j=0;j<i;j++){
        if(c==Integer.parseInt(String.valueOf(q[j][0]))){
            return j;
        }
  }
      return 100;
  }
public void view(int c){
    c=isContains(c);

    tv.setText(q[c][1]);
    ArrayList<String> itemsarray=new ArrayList<String>();
    for(String ss:q[c][2].split("-")){
       itemsarray.add(ss);
    }

    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, android.R.id.text1,itemsarray){

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view =super.getView(position, convertView, parent);
            TextView textView=(TextView) view.findViewById(android.R.id.text1);

            /*YOUR CHOICE OF COLOR*/

            textView.setTextColor(Color.BLACK);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize((float) 20.0);
            return view;

        }
    };

    sl.setAdapter(adapter);

}
void set(){
    if(!f.isEmpty()){

        Log.d("List","Added"+f);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,f){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);
                TextView textView=(TextView) view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.WHITE);
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize((float) 20.0);
                return view;
            }
        };
        TextView t1= (TextView) findViewById(R.id.t1);
        t1.setText("Selected Symptoms");
        se.setAdapter(adapter1);
    }
}


}
