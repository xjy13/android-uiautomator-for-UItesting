package com.uia.example.my;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

import android.R.string;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class UiTest2  extends UiAutomatorTestCase{
	int round=0;
	int timeout=10000;
	//String ConnectStatusXD[]={"Connected","Authentication problem"};
	//String a = "http://www.ascc.sinica.edu.tw/iascc/netsrv/speed/check.php";
	public void test() throws UiObjectNotFoundException, IOException {
	    
				while(round<=333)
				 {	
				
				  if (round %3 == 0 || round %3 == 1){
										
					  try{
						     GoToSettings();
				    	     TurnOnWiFi (round);
					    	}
					    catch(UiObjectNotFoundException e){
					    		writelog("Device doesn't find WiFi AP in "+round+" round(s)" +'\n'); 
					    		execCommand("screencap  /data/local/tmp/NoConnectWithAP.png");
					    	}
					  try{
					  Control_Browser(round);
					  }
					  catch(UiObjectNotFoundException e){
				      writelog("Device doesn't surf Internet in "+round+" round(s)" +'\n'); 
				    		    	}
					  sleep(5000);
						try{
							TurnOffWiFi(round);
						}	
						catch (UiObjectNotFoundException e){
						writelog("Device doesn't find Marlins AP in "+round+" round(s)" +'\n'); 
					        }		    		
				    	}
				  else if(round%3==2){
					 	writelog("Device use WWAN to connect Internet"+'\n');
					    // getUiDevice().pressHome();
						// ���app menu
				      	//UiObject allAppsButton = new UiObject(new UiSelector().description("Apps"));  
						//allAppsButton.clickAndWaitForNewWindow();
						 try{
							  Control_Browser(round);
							  }
							  catch(UiObjectNotFoundException e)
							  {
						    		writelog("Device doesn't surf Internet in "+round+" round(s)" +'\n'); 
						    		execCommand("screencap  /data/local/tmp/NoSurf.png");
							  }
				    	sleep(5000);
					  
				    }
				  round++;
				  
				  }
			
			 }
	
	public void GoToSettings()throws UiObjectNotFoundException, IOException{
		//��home key
		getUiDevice().pressHome();
		// ���app menu
      	UiObject allAppsButton = new UiObject(new UiSelector().className("android.widget.TextView").index(2));  
		allAppsButton.clickAndWaitForNewWindow();
		//�bapp menu ���⦸confirm
		//UiObject appsTab = new UiObject(new UiSelector().text("Apps").index(0));
		//appsTab.clickAndWaitForNewWindow();
		//�ưʧ�settings
		UiScrollable appViews = new UiScrollable(new UiSelector().className("android.view.View").resourceId("com.android.launcher:id/apps_customize_pane_content").scrollable(true));
     	appViews.setAsHorizontalList();
     	//���settings �ë��L
      	UiObject settingsApp = appViews.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()),"Settings");
		settingsApp.clickAndWaitForNewWindow();
	
	//	appViews.setAsVerticalList(); //for 4.1.2
		//appViews.flingToBeginning (10); //for 4.1.2
		UiObject WiFiOn = new UiObject(new UiSelector().packageName("com.android.settings").className("android.widget.LinearLayout").index(1).clickable(true));//4.1.2
		WiFiOn.clickAndWaitForNewWindow(); //4.1.2
	
	}
	// �}��wi-fi AP �i��s�u		 
	public void TurnOnWiFi (int a) throws UiObjectNotFoundException, IOException
	{
		//�NWi-Fi�}��
		
		UiObject turnON = new UiObject(new UiSelector().className("android.widget.Switch").text("OFF").index(1));
		if (turnON.exists())
		  {
			turnON.clickAndWaitForNewWindow();
		  }	
		sleep(5000);
		UiScrollable appViews1 = new UiScrollable(new UiSelector().scrollable(true));
		appViews1.setAsVerticalList();	
		if (a%3==0)
		{
			  //appViews1.scrollTextIntoView("Marlins");
			//execCommand("screenrecord --size 800x480 /mnt/sdcard/ConnectMarlins.mp4");
			appViews1.setMaxSearchSwipes(100); 
			appViews1.scrollTextIntoView("Marlins");
			appViews1.waitForExists(2000);
			UiObject AP1tab = appViews1.getChildByText(new UiSelector().className("android.widget.TextView"), "Marlins");
			AP1tab.clickAndWaitForNewWindow();
			//execCommand("exit");
	    	writelog("Device enable to reach Marlins AP " +round+ " rounds" +'\n');
	    	UiObject doubleClick =(new UiObject(new UiSelector().className("android.widget.EditText").resourceId("com.android.settings:id/password").clickable(true)));
	    	doubleClick.clickAndWaitForNewWindow();
		    getUiDevice().pressBack();
		  	
		}
		else if (a%3==1)
		{
		 //  UiScrollable appViews1 = new UiScrollable(new UiSelector().scrollable(true));
		 //  appViews1.setAsVerticalList();
		  // execCommand("screenrecord --size 800x480 /mnt/sdcard/ConnectBigD.mp4");
		   appViews1.setMaxSearchSwipes(100); 
		   appViews1.scrollTextIntoView("big daddy");
		   appViews1.waitForExists(2000);
		   UiObject AP1tab = appViews1.getChildByText(new UiSelector().className("android.widget.TextView"),"big daddy");
		   AP1tab.clickAndWaitForNewWindow();
		  // execCommand("exit");
	       writelog("Device enable to reach big daddy AP " +round+ " rounds" +'\n');
	       UiObject doubleClick=(new UiObject(new UiSelector().className("android.widget.EditText").resourceId("com.android.settings:id/password").clickable(true)));
	       doubleClick.clickAndWaitForNewWindow();
	       getUiDevice().pressBack();
		}
	
    	new UiObject (new UiSelector().resourceId("com.android.settings:id/password").index(1)).setText("70772036");                                                      
     	UiObject ConnectAP = new UiObject(new UiSelector().className("android.widget.Button").text("Connect"));
    	ConnectAP.clickAndWaitForNewWindow();
    	UiScrollable appViews2 = new UiScrollable(new UiSelector().scrollable(true));
		appViews2.setAsVerticalList();
		appViews2.flingToBeginning (10); 
		sleep(30000);
		
		UiObject ConnectStatus= new UiObject(new UiSelector().className("android.widget.TextView").resourceId("android:id/summary").text("Connected").index(1));
			if (ConnectStatus.exists())
				{
					writelog ("Device can connect WIFI AP at " +round+ " round");
					
				}
			else 
			{
				writelog("Device  connect WIFI AP fail at " +round+ " round");
				execCommand("screencap  /data/local/tmp/ConnectionAP_Failure.png");
				
			}
				
	}

	//����browser function
	public void Control_Browser(int x) throws UiObjectNotFoundException, IOException
	{
		 getUiDevice().pressHome();
		 // ���app menu
      	 UiObject allAppsButton = new UiObject(new UiSelector().description("Apps"));  
		 allAppsButton.clickAndWaitForNewWindow();
		
		 UiScrollable TestConnect = new UiScrollable(new UiSelector().scrollable(true));
		 TestConnect.setAsHorizontalList();
	     //���browser �ë��L
	      UiObject BrowserApp;
		try {
			BrowserApp = TestConnect.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()),"Browser");
			BrowserApp.clickAndWaitForNewWindow();
		} 

		catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			 writelog("Device fail to tap Browser App. in"+round+" round(s)" +'\n'); 
			 execCommand("screencap  /data/local/tmp/BrowserNotFind.png");
			 e.printStackTrace();
		}  
	    //����̤W��A���editText
		try{
		      writelog("Device input URL/IP in "+round+" round(s)" +'\n'); 
		      UiScrollable BrowserPage = new UiScrollable(new UiSelector().scrollable(true));
		      BrowserPage.setAsVerticalList();
		      BrowserPage.flingToBeginning (10); 
		      UiObject URL = new UiObject (new UiSelector().className("android.widget.EditText"));
		      URL.clickAndWaitForNewWindow();
		      URL.clearTextField();
		      //��JURL
		      UiScrollable BrowserViews = new UiScrollable(new UiSelector().scrollable(true));
		      //UiObject FindResult;
		      int y=x % 3;
		      switch(y)
		      {
		      //��_��s�u
		      	case 0:
		      	  
		      	  UiObject FindResult= new UiObject (new UiSelector().packageName("com.android.browser").className("android.widget.EditText").enabled(true).index(1));
		      	  URL.setText("www.ntut.edu.tw");
		      	  getUiDevice().pressEnter();
		  	      sleep(120000);
		  	      BrowserViews.setAsVerticalList();
		  	      BrowserViews.flingToBeginning (10); 
		  	      //�X�{ server timeout msg ��UiWatcher �I��
		  	       UiDevice.getInstance().registerWatcher("Close Timeout Msg", PopTimeoutMsg);
				   UiDevice.getInstance().runWatchers();
		  	      	         
		  	        FindResult= new UiObject (new UiSelector().packageName("com.android.browser").className(android.widget.EditText.class.getName()).text("www.ntut.edu.tw/m/home.php").index(1));	
		  	        
		  	         if(FindResult.exists())
		  	         {
		  	        	// FindResult= new UiObject (new UiSelector().packageName("com.android.browser").className(android.widget.EditText.class.getName()).text("www.ntut.edu.tw/m/home.php").index(1));	 
		  	              writelog("Device connect NTUT pass in"+round+" round(s)" +'\n');
		  	              execCommand("screencap  /data/local/tmp/ConnectionNTUT.png");
		  	         }
		  	         else if (!FindResult.exists()){
		  	        	 
		  	        	writelog("Device connect NTUT failure in "+round+" round(s)" +'\n'); 
		  	        	execCommand("screencap  /data/local/tmp/ConnectionNTUTFailure.png");
		  	         }
		  	       //�I�sUiWatcher ���O 
		  	      
				  	    
		      		break;
		       //��yahoo �s�u
		      	case 1:
		      		UiObject FindResult2= new UiObject (new UiSelector().packageName("com.android.browser").className("android.widget.EditText").enabled(true).index(1));
		      		URL.setText("tw.yahoo.com");
		      		getUiDevice().pressEnter();
			  	    sleep(120000);
			  	    BrowserViews.setAsVerticalList();
			  	    BrowserViews.flingToBeginning (10); 
			  	  //�X�{ server timeout msg ��UiWatcher �I��
			  	    UiDevice.getInstance().registerWatcher("Close Timeout Msg", PopTimeoutMsg);
					UiDevice.getInstance().runWatchers();		    	
			     FindResult2= new UiObject (new UiSelector().packageName("com.android.browser").className(android.widget.EditText.class.getName()).text("https://tw.mobi.yahoo.com/").index(1));	
	  	         if(FindResult2.exists())
	  	         {
	  	        	// FindResult= new UiObject (new UiSelector().packageName("com.android.browser").className(android.widget.EditText.class.getName()).text("www.ntut.edu.tw/m/home.php").index(1));	 
	  	        	  writelog("Device connect Yahoo pass in "+round+" round(s)" +'\n');
			  		  execCommand("screencap  /data/local/tmp/ConnectionYahoo.png");	
	  	         }
	  	         else {
	  	        	 
	  	        	 writelog("Device connect Yahoo failure in "+round+" round(s)" +'\n');
			  		  execCommand("screencap  /data/local/tmp/ConnectYahooFailure.png");
	  	         }
	  	      		  	  
		      		break;
		      		
		      	//�򤤬�|���t�s�u	
		      	case 2:
		      	    UiObject FindResult3= new UiObject (new UiSelector().packageName("com.android.browser").className("android.widget.EditText").enabled(true).index(1));
		      		URL.setText("https://proxy.ntut.edu.tw/speed/index.php");
		      		       
		      		getUiDevice().pressEnter();
			  	    sleep(120000);
			  	  UiDevice.getInstance().registerWatcher("Close Timeout Msg", PopTimeoutMsg);
				  UiDevice.getInstance().runWatchers();
			  	  BrowserViews.setAsVerticalList();
		  	      BrowserViews.flingToBeginning (10); 
		  	      
			  	    
			  	  //�X�{ server timeout msg ��UiWatcher �I��
			  	     
			  	     FindResult3= new UiObject (new UiSelector().packageName("com.android.browser").className(android.widget.EditText.class.getName()).resourceId("com.android.browser:id/url").text("https://proxy.ntut.edu.tw/speed/index.php").index(1));
			  	                                                                                                                                    
			  	 if(FindResult3.exists())
	  	         {
	  	        	// FindResult= new UiObject (new UiSelector().packageName("com.android.browser").className(android.widget.EditText.class.getName()).text("www.ntut.edu.tw/m/home.php").index(1));	 
			  		 writelog("Device connect ascc_sinica  pass in "+round+" round(s)" +'\n');
			  		 execCommand("screencap /data/local/tmp/Connection_ascc_sinicaPass.png");	
			  		 //UiObject YoutuTab = new UiObject(new UiSelector().packageName("com.android.browser").className("android.view.View").descriptionContains("media control").index(0));
			  		 //YoutuTab.clickAndWaitForNewWindow();
	  	         }
			  	 
	  	         else if (!FindResult3.exists()){
	  	        	 
	  	        	execCommand("screencap  /data/local/tmp/Connec_ascc_sinicaAFailure.png");
			  		writelog("Device connect ascc_sinica failure in "+round+" round(s)" +'\n');
	  	         }
			  	
			   /*���p�J��connection timeout��
			  	 else if (TimeoutConnect_1.exists()){
		  	        	writelog("Device connect timeout in "+round+" round(s)" +'\n');  
		  	        	UiObject Presstimeoutbtn = new UiObject(new UiSelector().description("OK"));   
		  	        	Presstimeoutbtn.clickAndWaitForNewWindow();
		  	         }*/
			  	sleep(100000);
			  	//UiObject YoutuTab = new UiObject(new UiSelector().packageName("com.android.browser").className("android.view.View").descriptionContains("media control").index(0));
		  		//YoutuTab.clickAndWaitForNewWindow();
			  	break;
	           }
	      
	  	     }

		catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			
				 writelog("Device doesn't do connect website in "+round+" round(s)" +'\n'); 
				 execCommand("screencap  /data/local/tmp/ConnectURL_fail.png");
			
						
			e.printStackTrace();
		}
	}
	
	//��wifi	
	public void TurnOffWiFi(int a) throws UiObjectNotFoundException{
		 writelog("Device disconnect AP in "+round+" round(s)" +'\n'); 
		   try{ 
		    getUiDevice().pressHome();
			UiObject allAppsButton = new UiObject(new UiSelector().className("android.widget.TextView").index(2));  
			allAppsButton.clickAndWaitForNewWindow();
			
			UiScrollable appViews = new UiScrollable(new UiSelector().className("android.view.View").resourceId("com.android.launcher:id/apps_customize_pane_content").scrollable(true));
	     	appViews.setAsHorizontalList();
	     	//���settings �ë��L
	     	UiObject settingsApp = appViews.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()),"Settings");
			settingsApp.clickAndWaitForNewWindow();
			//if (!mWifiManager.isWifiEnabled()) 
			//appViews.setAsVerticalList();
			//appViews.flingToBeginning (10); 
			UiObject WiFiOn = new UiObject(new UiSelector().packageName("com.android.settings").className("android.widget.LinearLayout").index(1).clickable(true));//4.1.2
			WiFiOn.clickAndWaitForNewWindow(); //4.1.2
			
		   }
		   
		   catch (UiObjectNotFoundException e) {
				// TODO Auto-generated catch block
				writelog("Device occur proccess error in "+round+" round(s)" +'\n'); 
				e.printStackTrace();
			}
		   	
		   	 UiScrollable appViews1 = new UiScrollable(new UiSelector().scrollable(true));
			 appViews1.setAsVerticalList();	
			 if(a%3==0)
			 {
				 UiObject AP1tab1 = appViews1.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()),"Marlins");
				 AP1tab1.clickAndWaitForNewWindow();
			 }
			 else if (a%3==1)
			 {
				 UiObject AP1tab1 = appViews1.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()),"big daddy");
				 AP1tab1.clickAndWaitForNewWindow();
			 }
			 //�NAP�_�u
			 UiObject DisConnectAP = new UiObject(new UiSelector().text("Forget"));
			 DisConnectAP.clickAndWaitForNewWindow();
			 sleep(2000);
			 UiObject turnOFF = new UiObject(new UiSelector().text("ON"));
		     turnOFF.clickAndWaitForNewWindow();
			 getUiDevice().pressHome();		 
		   
	   }
	
	UiWatcher PopTimeoutMsg = new UiWatcher() {
	  	   @Override
	  	 //UiDevice.getInstance().registerWatcher("Close Timeout Msg", PopTimeoutMsg);
	  	   public boolean checkForCondition() 
	  	   {
	  	      UiObject TimeoutConnect = new UiObject(new UiSelector().packageName("com.android.browser").className("android.widget.TextView").resourceId("android:id/alertTitle").index(0));
	  	      if (TimeoutConnect.exists()) {
	  	    	UiObject Presstimeoutbtn = new UiObject(new UiSelector().text("OK"));  
	  	    	try {
					Presstimeoutbtn.clickAndWaitForNewWindow();
					return true;
				    } 
	  	    	catch (UiObjectNotFoundException e) 
	  	    	   {
					// TODO Auto-generated catch block
					e.printStackTrace();
									
				   }
	  	     }
			return false;
	  	   
	  	}
	};
	  
	//�I�sshell script
	public void execCommand(String command) throws IOException {
		 String[] args = new String[]{"sh", "-c", command};
        Runtime runtime = Runtime.getRuntime();
        // start the ls command running
        Process proc = runtime.exec(command); // �I�slinux shell
        // ��Xstderr �� stdout, ����k���i�����,�Y�o�ͫh�������B�~thread �h��stderr��stdout		
	}
	
	//�g�Jlog function
	public void writelog(String msg){
		FileWriter f;
		String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
			 try {
				f = new FileWriter("/data/local/tmp/WiFi_connect.log",true);
				f.write(mydate+" -- "+msg +"\n");
				f.flush();
				f.close();
				} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}     

	}
}