package com.havmedia.havradio;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle; 
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.appwidget.*;
import android.bluetooth.*;
import android.content.Intent;

public class Main extends Activity {
	letsradio yeah;
	Process a;
	Process b;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
       
       
	
		button1.setOnClickListener(new OnClickListener() {

			@Override
			
			public void onClick(View v) {  // onClick Method
				
				yeah = new letsradio();
				
				yeah.listen();
				

			}});
		
		button2.setOnClickListener(new OnClickListener() {

			@Override
			
			public void onClick(View v) {  // onClick Method
				
				// BLUETOOTH
				// get bluetooth  
		        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		        // enable bluetooth if disabled
		        if (!mBluetoothAdapter.isEnabled()) {
		            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
		            int requestcode = 2;
		            startActivityForResult(enableBtIntent, requestcode);
		        }
		        
				yeah = null; 
				yeah = new letsradio(100700); // attempts to create new letsradio with def FREQ 100.7
				
				//yeah.listen2();
				yeah.enableradio();

			}});
		
		button3.setOnClickListener(new OnClickListener() {

			@Override
			
			public void onClick(View v) {  // onClick Method
				
				// Turning BT off turns radio off
				// get bluetooth  
		        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
				//turn off
		        mBluetoothAdapter.disable();
				
				
				
				/*
				//ArrayList<String> commands = new ArrayList<String>();
				//commands.add("fm stop");
				//commands.add("hcitool cmd 0x3f 0x137 0x00 0x00");
				//commands.add("bttest disable");
				//commands.add("sleep 3");
				//commands.add("exit 0");

				for(String i : commands){
					
					yeah.execCommandLine(i);
				}
				yeah = null;
				
				*/
				//Toast.makeText(this, "Radio stopped", Toast.LENGTH_LONG);
				
				

			}});
        
		button4.setOnClickListener(new OnClickListener() {

			@Override
			
			public void onClick(View v) {  // onClick Method
				// int FREQR = getinput();
			yeah = null;
			
	    	EditText editor = (EditText) findViewById(R.id.editer);
			//Editable wom = editor.getText();
			//String wom2 = (String) wom;
			int result = Integer.parseInt(editor.getText().toString());
			
				yeah = new letsradio(result);
				
				yeah.listen2();
				

			}});
		
		button5.setOnClickListener(new OnClickListener() {

			@Override
			
			public void onClick(View v) {  // onClick Method
				// int FREQR = getinput();
			yeah = null;
			
		    EditText first = (EditText) findViewById(R.id.first);
		    EditText second = (EditText) findViewById(R.id.second);
		    
		    int t = 1000;
		    int t2 = 100;
		    int result = 105400;
		    String firstIR;
		    String secondIR;

		    firstIR = first.getText().toString();
		    secondIR = second.getText().toString();
		    	
		    	// FirstI 
		    int firstI = Integer.parseInt(firstIR);
		    if (firstI < 100){ t = 10000; }
		    		    
		    int secondI = Integer.parseInt(secondIR);
  		    if (secondI > 9){ t2 = 10;}
		    
		    firstI = firstI * t;
		    secondI = secondI * t2;
		    result = firstI + secondI;
		    
		    
		    	if(result < 875000 || firstIR == "" || secondIR == ""){result = 105400;}
		    	
				yeah = new letsradio(result);
				
				yeah.listen2();
				

			}});
		
		
      // letsradio yeah = new letsradio();
       
       //yeah.askforroot();

        try {
      	   
     		a = Runtime.getRuntime().exec("su");
     		
     	} catch (IOException e) {
     		// TODO Auto-generated catch block
     		e.printStackTrace();
     	}
     	
     	
     	
            
         }
    
    
    
    


    
     public int getinput() {
    	 
    EditText first = (EditText) findViewById(R.id.first);
    EditText second = (EditText) findViewById(R.id.second);
    
    int firstI = Integer.parseInt(first.getText().toString());
    int secondI = Integer.parseInt(first.getText().toString());
    firstI = firstI * 100;
    secondI = secondI * 100;
    int result = firstI + secondI;
    
    // return firstI + secondI;
    return 105400;
    
    	 
    	 
	}







	void jam(CharSequence cheese){
    	
    	Toast.makeText(this, cheese, Toast.LENGTH_LONG);
    	
    }
}


class letsradio extends Main{
	public int FREQ;
	
	
	// constructors
	
	public letsradio(){
		FREQ = 105400;
	}
		public letsradio(int FREQR){
		FREQ = FREQR;
		}
		


	
	void listen2(){
		
        try {
      	   String test = "fm" + " " + FREQ;
     		b = Runtime.getRuntime().exec(test);

     		
     	} catch (IOException e) {
     		// TODO Auto-generated catch block
     		e.printStackTrace();
     	}
		
	}
    void listen(){
    	
        try {
     	b = Runtime.getRuntime().exec("hextra");
 		b = Runtime.getRuntime().exec("fm 105400");

 		
 	} catch (IOException e) {
 		// TODO Auto-generated catch block
 		e.printStackTrace();
 	}
 	
 	
        
     }
    
    void enableradio(){
    	ArrayList<String> commands = new ArrayList<String>();
    	//commands.add("su");
    	commands.add("fm 87500");
    	commands.add("echo test");
    	commands.add("echo test");
    	commands.add("echo test");
    	commands.add("echo test");
        commands.add("hcitool cmd 0x3f 0x137 0x01 0x01");
        commands.add("hcitool cmd 0x3f 0x133 0x20 0x02 0x00");
        commands.add("hcitool cmd 0x3f 0x133 0x20 0x02 0x00");
        commands.add("hcitool cmd 0x3f 0x135 0x20 0x02 0x00 0x00 0x03");
        commands.add("hcitool cmd 0x3f 0x133 0x20 0x02 0x00");
        commands.add("hcitool cmd 0x3f 0x135 0x1d 0x02 0x00 0x00 0x03");
        commands.add("hcitool cmd 0x3f 0x135 0x11 0x02 0x00 0x00 0x00");
        commands.add("hcitool cmd 0x3f 0x135 0x0c 0x02 0x00 0x00 0x00");
        commands.add("hcitool cmd 0x3f 0x135 0x0d 0x02 0x00 0x00 0x01");
        commands.add("hcitool cmd 0x3f 0x135 0x0e 0x02 0x00 0x00 0x00");
        commands.add("hcitool cmd 0x3f 0x135 0x23 0x02 0x00 0x00 0x02");
        commands.add("hcitool cmd 0x3f 0x135 0x10 0x02 0x00 0x00 0x00");
        commands.add("hcitool cmd 0x3f 0x135 0x38 0x02 0x00 0x00 0x04");
        commands.add("hcitool cmd 0x3f 0x135 0x2d 0x02 0x00 0x00 0x01");
        commands.add("hcitool cmd 0x3f 0x133 0x0a 0x02 0x00");
        commands.add("hcitool cmd 0x3f 0x133 0x01 0x02 0x00");
        commands.add("hcitool cmd 0x3f 0x135 0x1c 0x02 0x00 0x10 0x00");
        commands.add("snd_set_device 9");
        
        for(String i : commands){
        	//i = i + " \n";
        	
        	execCommandLine (i);
        	// this needs streamlined - using \n ?	
        }

    	
    }
	
    void execCommandLine(String command)
    {
        Runtime runtime = Runtime.getRuntime();
        Process proc = null;
        OutputStreamWriter osw = null;

        try
        {
            proc = runtime.exec("su");
            osw = new OutputStreamWriter(proc.getOutputStream());
            osw.write(command);
            osw.flush();
            osw.close();
        }
        catch (IOException ex)
        {
            Log.e("execCommandLine()", "Command resulted in an IO Exception: " + command);
            return;
        }
        finally
        {
            if (osw != null)
            {
                try
                {
                    osw.close();
                }
                catch (IOException e){}
            }
        }

        try 
        {
            proc.waitFor();
        }
        catch (InterruptedException e){}

        if (proc.exitValue() != 0)
        {
            Log.e("execCommandLine()", "Command returned error: " + command + "\n  Exit code: " + proc.exitValue());
        }
    }
    
    
	void askforroot(){
		
		 try {  
      	   // Preform su to get root privledges  
			
      	   a = Runtime.getRuntime().exec("su");
      	
      	 
      	   // Attempt to write a file to a root-only  
      	   
      	   DataOutputStream os = new DataOutputStream(a.getOutputStream());  
      	   os.writeBytes("echo \"Do I have root?\" >/system/sd/temporary.txt\n");  
      	  
      	   // Close the terminal  
      	   os.writeBytes("exit\n");        
      	   os.flush();  
      	   try {  
      	      a.waitFor();  
      	           if (a.exitValue() != 255) {  
      	              // TODO Code to run on success  
      	            //  Toast.makeText(this, "root", Toast.LENGTH_LONG);  
      	        //    jam("root");
      	           }  
      	           else {  
      	               // TODO Code to run on unsuccessful
      	        //	   Toast.makeText(this, "no root", Toast.LENGTH_LONG);
      	          //    jam("not root");  
      	           }  
      	   } catch (InterruptedException e) {  
      	      // TODO Code to run in interrupted exception  
      		  // Toast.makeText(this, "no root", Toast.LENGTH_LONG);
      		// jam("no root");
      	   }  
      	} catch (IOException e) {  
      	   // TODO Code to run in input/output exception  
      		 // Toast.makeText(this, "root", Toast.LENGTH_LONG);
      			//jam("root");
      	}  
      	
      	
      
        a = null;
  }	// end ask for root
	
	
	//UNUSED METHODS
	public void stop() {
		// UNUSED METHOD
		// UNUSED METHOD
		
		ArrayList<String> commands = new ArrayList<String>();
		commands.add("hcitool cmd 0x3f 0x137 0x00 0x00");
		commands.add("bttest disable");
		//commands.add("sleep 3");
		//commands.add("exit 0");

		for(String i : commands){
			
			execCommandLine(i);
		}
		Toast.makeText(this, "Radio stopped", Toast.LENGTH_LONG);
		
		
		/*
		try {
			a = Runtime.getRuntime().exec("fm stop");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(a.getInputStream()));
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		*/
		
	}

	
	
	}
	
	
	
	
	
	
	
