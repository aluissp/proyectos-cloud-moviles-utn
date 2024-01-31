package b4a.example;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;
    public static boolean dontPause;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        if (!dontPause)
            BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (main) Pause event (activity is not paused). **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        if (!dontPause) {
            processBA.setActivityPaused(true);
            mostCurrent = null;
        }

        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            main mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }



public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}
public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}

private static BA killProgramHelper(BA ba) {
    if (ba == null)
        return null;
    anywheresoftware.b4a.BA.SharedProcessBA sharedProcessBA = ba.sharedProcessBA;
    if (sharedProcessBA == null || sharedProcessBA.activityBA == null)
        return null;
    return sharedProcessBA.activityBA.get();
}
public static void killProgram() {
     {
            Activity __a = null;
            if (main.previousOne != null) {
				__a = main.previousOne.get();
			}
            else {
                BA ba = killProgramHelper(main.mostCurrent == null ? null : main.mostCurrent.processBA);
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

BA.applicationContext.stopService(new android.content.Intent(BA.applicationContext, starter.class));
}
public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtid = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txttitulo = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtfechaestreno = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _txtencines = null;
public b4a.example.starter _starter = null;
public b4a.example.httputils2service _httputils2service = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=131072;
 //BA.debugLineNum = 131072;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=131073;
 //BA.debugLineNum = 131073;BA.debugLine="Activity.LoadLayout(\"Layout\")";
mostCurrent._activity.LoadLayout("Layout",mostCurrent.activityBA);
RDebugUtils.currentLine=131074;
 //BA.debugLineNum = 131074;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=524288;
 //BA.debugLineNum = 524288;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=524290;
 //BA.debugLineNum = 524290;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=458752;
 //BA.debugLineNum = 458752;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=458754;
 //BA.debugLineNum = 458754;BA.debugLine="End Sub";
return "";
}
public static String  _button1_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "button1_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "button1_click", null));}
RDebugUtils.currentLine=589824;
 //BA.debugLineNum = 589824;BA.debugLine="Sub Button1_Click";
RDebugUtils.currentLine=589825;
 //BA.debugLineNum = 589825;BA.debugLine="xui.MsgboxAsync(\"Hello world!\", \"B4X\")";
_xui.MsgboxAsync(processBA,BA.ObjectToCharSequence("Hello world!"),BA.ObjectToCharSequence("B4X"));
RDebugUtils.currentLine=589826;
 //BA.debugLineNum = 589826;BA.debugLine="End Sub";
return "";
}
public static void  _cmddelete_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "cmddelete_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "cmddelete_click", null); return;}
ResumableSub_cmdDelete_Click rsub = new ResumableSub_cmdDelete_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_cmdDelete_Click extends BA.ResumableSub {
public ResumableSub_cmdDelete_Click(b4a.example.main parent) {
this.parent = parent;
}
b4a.example.main parent;
int _id = 0;
b4a.example.httpjob _peticion = null;
b4a.example.httpjob _respuesta = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="main";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=327682;
 //BA.debugLineNum = 327682;BA.debugLine="If txtId.Text.Trim = \"\" Then";
if (true) break;

case 1:
//if
this.state = 4;
if ((parent.mostCurrent._txtid.getText().trim()).equals("")) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
RDebugUtils.currentLine=327683;
 //BA.debugLineNum = 327683;BA.debugLine="MsgboxAsync(\"Debes ingresar el ID de la película";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Debes ingresar el ID de la película para eliminarla."),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=327684;
 //BA.debugLineNum = 327684;BA.debugLine="Return";
if (true) return ;
 if (true) break;

case 4:
//C
this.state = 5;
;
RDebugUtils.currentLine=327687;
 //BA.debugLineNum = 327687;BA.debugLine="Dim id As Int = txtId.Text";
_id = (int)(Double.parseDouble(parent.mostCurrent._txtid.getText()));
RDebugUtils.currentLine=327688;
 //BA.debugLineNum = 327688;BA.debugLine="Dim peticion As HttpJob";
_peticion = new b4a.example.httpjob();
RDebugUtils.currentLine=327689;
 //BA.debugLineNum = 327689;BA.debugLine="peticion.Initialize(\"jobDelete\", Me)";
_peticion._initialize /*String*/ (null,processBA,"jobDelete",main.getObject());
RDebugUtils.currentLine=327692;
 //BA.debugLineNum = 327692;BA.debugLine="peticion.Delete(\"https://sqliteazurepelicula.azur";
_peticion._delete /*String*/ (null,"https://sqliteazurepelicula.azurewebsites.net/api/Peliculas/"+BA.NumberToString(_id));
RDebugUtils.currentLine=327695;
 //BA.debugLineNum = 327695;BA.debugLine="Wait For (peticion) JobDone(respuesta As HttpJob)";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "main", "cmddelete_click"), (Object)(_peticion));
this.state = 11;
return;
case 11:
//C
this.state = 5;
_respuesta = (b4a.example.httpjob) result[0];
;
RDebugUtils.currentLine=327696;
 //BA.debugLineNum = 327696;BA.debugLine="If respuesta.Success Then";
if (true) break;

case 5:
//if
this.state = 10;
if (_respuesta._success /*boolean*/ ) { 
this.state = 7;
}else {
this.state = 9;
}if (true) break;

case 7:
//C
this.state = 10;
RDebugUtils.currentLine=327697;
 //BA.debugLineNum = 327697;BA.debugLine="MsgboxAsync(\"Película borrada correctamente!\", \"";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Película borrada correctamente!"),BA.ObjectToCharSequence("Éxito"),processBA);
 if (true) break;

case 9:
//C
this.state = 10;
RDebugUtils.currentLine=327699;
 //BA.debugLineNum = 327699;BA.debugLine="MsgboxAsync(\"No se pudo borrar la película. \" &";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("No se pudo borrar la película. "+_respuesta._errormessage /*String*/ ),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;

case 10:
//C
this.state = -1;
;
RDebugUtils.currentLine=327702;
 //BA.debugLineNum = 327702;BA.debugLine="peticion.Release";
_peticion._release /*String*/ (null);
RDebugUtils.currentLine=327703;
 //BA.debugLineNum = 327703;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _cmdinsertar_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "cmdinsertar_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "cmdinsertar_click", null); return;}
ResumableSub_cmdInsertar_Click rsub = new ResumableSub_cmdInsertar_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_cmdInsertar_Click extends BA.ResumableSub {
public ResumableSub_cmdInsertar_Click(b4a.example.main parent) {
this.parent = parent;
}
b4a.example.main parent;
anywheresoftware.b4a.objects.collections.Map _map = null;
String _json = "";
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _jsongenerator = null;
String _titulo = "";
String _fechaestreno = "";
boolean _encines = false;
b4a.example.httpjob _peticion = null;
b4a.example.httpjob _respuesta = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="main";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=393217;
 //BA.debugLineNum = 393217;BA.debugLine="Dim map As Map";
_map = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=393218;
 //BA.debugLineNum = 393218;BA.debugLine="Dim json As String";
_json = "";
RDebugUtils.currentLine=393219;
 //BA.debugLineNum = 393219;BA.debugLine="Dim jsonGenerator As JSONGenerator";
_jsongenerator = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();
RDebugUtils.currentLine=393222;
 //BA.debugLineNum = 393222;BA.debugLine="Dim titulo As String = txtTitulo.Text";
_titulo = parent.mostCurrent._txttitulo.getText();
RDebugUtils.currentLine=393223;
 //BA.debugLineNum = 393223;BA.debugLine="Dim fechaEstreno As String = txtFechaEstreno.Text";
_fechaestreno = parent.mostCurrent._txtfechaestreno.getText();
RDebugUtils.currentLine=393224;
 //BA.debugLineNum = 393224;BA.debugLine="Dim enCines As Boolean = txtEnCines.Checked";
_encines = parent.mostCurrent._txtencines.getChecked();
RDebugUtils.currentLine=393227;
 //BA.debugLineNum = 393227;BA.debugLine="map.Initialize";
_map.Initialize();
RDebugUtils.currentLine=393228;
 //BA.debugLineNum = 393228;BA.debugLine="map.Put(\"titulo\", titulo)";
_map.Put((Object)("titulo"),(Object)(_titulo));
RDebugUtils.currentLine=393229;
 //BA.debugLineNum = 393229;BA.debugLine="map.Put(\"enCines\", enCines)";
_map.Put((Object)("enCines"),(Object)(_encines));
RDebugUtils.currentLine=393230;
 //BA.debugLineNum = 393230;BA.debugLine="map.Put(\"fechaEstreno\", fechaEstreno)";
_map.Put((Object)("fechaEstreno"),(Object)(_fechaestreno));
RDebugUtils.currentLine=393233;
 //BA.debugLineNum = 393233;BA.debugLine="jsonGenerator.Initialize(map)";
_jsongenerator.Initialize(_map);
RDebugUtils.currentLine=393234;
 //BA.debugLineNum = 393234;BA.debugLine="json = jsonGenerator.ToPrettyString(2)";
_json = _jsongenerator.ToPrettyString((int) (2));
RDebugUtils.currentLine=393237;
 //BA.debugLineNum = 393237;BA.debugLine="Dim peticion As HttpJob";
_peticion = new b4a.example.httpjob();
RDebugUtils.currentLine=393238;
 //BA.debugLineNum = 393238;BA.debugLine="peticion.Initialize(\"jobInsertar\", Me)";
_peticion._initialize /*String*/ (null,processBA,"jobInsertar",main.getObject());
RDebugUtils.currentLine=393239;
 //BA.debugLineNum = 393239;BA.debugLine="peticion.PostString(\"https://sqliteazurepelicula.";
_peticion._poststring /*String*/ (null,"https://sqliteazurepelicula.azurewebsites.net/api/Peliculas/",_json);
RDebugUtils.currentLine=393240;
 //BA.debugLineNum = 393240;BA.debugLine="peticion.GetRequest.SetContentType(\"application/j";
_peticion._getrequest /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ (null).SetContentType("application/json");
RDebugUtils.currentLine=393243;
 //BA.debugLineNum = 393243;BA.debugLine="Wait For (peticion) JobDone(respuesta As HttpJob)";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "main", "cmdinsertar_click"), (Object)(_peticion));
this.state = 7;
return;
case 7:
//C
this.state = 1;
_respuesta = (b4a.example.httpjob) result[0];
;
RDebugUtils.currentLine=393244;
 //BA.debugLineNum = 393244;BA.debugLine="If respuesta.Success Then";
if (true) break;

case 1:
//if
this.state = 6;
if (_respuesta._success /*boolean*/ ) { 
this.state = 3;
}else {
this.state = 5;
}if (true) break;

case 3:
//C
this.state = 6;
RDebugUtils.currentLine=393245;
 //BA.debugLineNum = 393245;BA.debugLine="MsgboxAsync(\"Película insertada correctamente.\",";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Película insertada correctamente."),BA.ObjectToCharSequence("Éxito"),processBA);
 if (true) break;

case 5:
//C
this.state = 6;
RDebugUtils.currentLine=393247;
 //BA.debugLineNum = 393247;BA.debugLine="MsgboxAsync(\"No se pudo insertar la película. \"";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("No se pudo insertar la película. "+_respuesta._errormessage /*String*/ ),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;

case 6:
//C
this.state = -1;
;
RDebugUtils.currentLine=393251;
 //BA.debugLineNum = 393251;BA.debugLine="peticion.Release";
_peticion._release /*String*/ (null);
RDebugUtils.currentLine=393252;
 //BA.debugLineNum = 393252;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _cmdread_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "cmdread_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "cmdread_click", null); return;}
ResumableSub_cmdRead_Click rsub = new ResumableSub_cmdRead_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_cmdRead_Click extends BA.ResumableSub {
public ResumableSub_cmdRead_Click(b4a.example.main parent) {
this.parent = parent;
}
b4a.example.main parent;
int _peliculaid = 0;
b4a.example.httpjob _peticion = null;
b4a.example.httpjob _respuesta = null;
String _json = "";
anywheresoftware.b4a.objects.collections.JSONParser _jsonparser = null;
anywheresoftware.b4a.objects.collections.Map _map = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="main";

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=196609;
 //BA.debugLineNum = 196609;BA.debugLine="Dim peliculaId As Int";
_peliculaid = 0;
RDebugUtils.currentLine=196610;
 //BA.debugLineNum = 196610;BA.debugLine="Try";
if (true) break;

case 1:
//try
this.state = 6;
this.catchState = 5;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 6;
this.catchState = 5;
RDebugUtils.currentLine=196611;
 //BA.debugLineNum = 196611;BA.debugLine="peliculaId = txtId.Text";
_peliculaid = (int)(Double.parseDouble(parent.mostCurrent._txtid.getText()));
 if (true) break;

case 5:
//C
this.state = 6;
this.catchState = 0;
RDebugUtils.currentLine=196613;
 //BA.debugLineNum = 196613;BA.debugLine="MsgboxAsync(\"El ID de la película debe ser numér";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("El ID de la película debe ser numérico."),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=196614;
 //BA.debugLineNum = 196614;BA.debugLine="Return";
if (true) return ;
 if (true) break;
if (true) break;

case 6:
//C
this.state = 7;
this.catchState = 0;
;
RDebugUtils.currentLine=196617;
 //BA.debugLineNum = 196617;BA.debugLine="Dim peticion As HttpJob";
_peticion = new b4a.example.httpjob();
RDebugUtils.currentLine=196618;
 //BA.debugLineNum = 196618;BA.debugLine="peticion.Initialize(\"\", Me)";
_peticion._initialize /*String*/ (null,processBA,"",main.getObject());
RDebugUtils.currentLine=196619;
 //BA.debugLineNum = 196619;BA.debugLine="peticion.Download(\"https://sqliteazurepelicula.az";
_peticion._download /*String*/ (null,"https://sqliteazurepelicula.azurewebsites.net/api/Peliculas/"+BA.NumberToString(_peliculaid));
RDebugUtils.currentLine=196620;
 //BA.debugLineNum = 196620;BA.debugLine="Wait For (peticion) JobDone(respuesta As HttpJob)";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "main", "cmdread_click"), (Object)(_peticion));
this.state = 13;
return;
case 13:
//C
this.state = 7;
_respuesta = (b4a.example.httpjob) result[0];
;
RDebugUtils.currentLine=196622;
 //BA.debugLineNum = 196622;BA.debugLine="If respuesta.Success Then";
if (true) break;

case 7:
//if
this.state = 12;
if (_respuesta._success /*boolean*/ ) { 
this.state = 9;
}else {
this.state = 11;
}if (true) break;

case 9:
//C
this.state = 12;
RDebugUtils.currentLine=196623;
 //BA.debugLineNum = 196623;BA.debugLine="Dim json As String";
_json = "";
RDebugUtils.currentLine=196624;
 //BA.debugLineNum = 196624;BA.debugLine="json = respuesta.GetString";
_json = _respuesta._getstring /*String*/ (null);
RDebugUtils.currentLine=196626;
 //BA.debugLineNum = 196626;BA.debugLine="Dim jsonParser As JSONParser";
_jsonparser = new anywheresoftware.b4a.objects.collections.JSONParser();
RDebugUtils.currentLine=196627;
 //BA.debugLineNum = 196627;BA.debugLine="jsonParser.Initialize(json)";
_jsonparser.Initialize(_json);
RDebugUtils.currentLine=196629;
 //BA.debugLineNum = 196629;BA.debugLine="Dim map As Map = jsonParser.NextObject";
_map = new anywheresoftware.b4a.objects.collections.Map();
_map = _jsonparser.NextObject();
RDebugUtils.currentLine=196632;
 //BA.debugLineNum = 196632;BA.debugLine="txtId.Text= map.Get(\"id\")";
parent.mostCurrent._txtid.setText(BA.ObjectToCharSequence(_map.Get((Object)("id"))));
RDebugUtils.currentLine=196633;
 //BA.debugLineNum = 196633;BA.debugLine="txtTitulo.Text = map.Get(\"titulo\")";
parent.mostCurrent._txttitulo.setText(BA.ObjectToCharSequence(_map.Get((Object)("titulo"))));
RDebugUtils.currentLine=196634;
 //BA.debugLineNum = 196634;BA.debugLine="txtEnCines.Checked = map.Get(\"enCines\")";
parent.mostCurrent._txtencines.setChecked(BA.ObjectToBoolean(_map.Get((Object)("enCines"))));
RDebugUtils.currentLine=196635;
 //BA.debugLineNum = 196635;BA.debugLine="txtFechaEstreno.Text = map.Get(\"fechaEstreno\")";
parent.mostCurrent._txtfechaestreno.setText(BA.ObjectToCharSequence(_map.Get((Object)("fechaEstreno"))));
 if (true) break;

case 11:
//C
this.state = 12;
RDebugUtils.currentLine=196638;
 //BA.debugLineNum = 196638;BA.debugLine="MsgboxAsync(\"No se pudo obtener la información d";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("No se pudo obtener la información de la película. "+_respuesta._errormessage /*String*/ ),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;

case 12:
//C
this.state = -1;
;
RDebugUtils.currentLine=196641;
 //BA.debugLineNum = 196641;BA.debugLine="peticion.Release";
_peticion._release /*String*/ (null);
RDebugUtils.currentLine=196642;
 //BA.debugLineNum = 196642;BA.debugLine="End Sub";
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
processBA.setLastException(e0);}
            }
        }
    }
}
public static void  _cmdupdate_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "cmdupdate_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "cmdupdate_click", null); return;}
ResumableSub_cmdUpdate_Click rsub = new ResumableSub_cmdUpdate_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_cmdUpdate_Click extends BA.ResumableSub {
public ResumableSub_cmdUpdate_Click(b4a.example.main parent) {
this.parent = parent;
}
b4a.example.main parent;
anywheresoftware.b4a.objects.collections.Map _map = null;
String _json = "";
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _jsongenerator = null;
b4a.example.httpjob _peticion = null;
int _id = 0;
b4a.example.httpjob _respuesta = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="main";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=262146;
 //BA.debugLineNum = 262146;BA.debugLine="If txtId.Text.Trim = \"\" Then";
if (true) break;

case 1:
//if
this.state = 4;
if ((parent.mostCurrent._txtid.getText().trim()).equals("")) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
RDebugUtils.currentLine=262147;
 //BA.debugLineNum = 262147;BA.debugLine="MsgboxAsync(\"El ID de la película es necesario p";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("El ID de la película es necesario para actualizar."),BA.ObjectToCharSequence("Error"),processBA);
RDebugUtils.currentLine=262148;
 //BA.debugLineNum = 262148;BA.debugLine="Return";
if (true) return ;
 if (true) break;

case 4:
//C
this.state = 5;
;
RDebugUtils.currentLine=262152;
 //BA.debugLineNum = 262152;BA.debugLine="Dim map As Map";
_map = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=262153;
 //BA.debugLineNum = 262153;BA.debugLine="Dim json As String";
_json = "";
RDebugUtils.currentLine=262154;
 //BA.debugLineNum = 262154;BA.debugLine="Dim jsonGenerator As JSONGenerator";
_jsongenerator = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();
RDebugUtils.currentLine=262155;
 //BA.debugLineNum = 262155;BA.debugLine="Dim peticion As HttpJob";
_peticion = new b4a.example.httpjob();
RDebugUtils.currentLine=262156;
 //BA.debugLineNum = 262156;BA.debugLine="peticion.Initialize(\"jobUpdate\", Me)";
_peticion._initialize /*String*/ (null,processBA,"jobUpdate",main.getObject());
RDebugUtils.currentLine=262158;
 //BA.debugLineNum = 262158;BA.debugLine="map.Initialize";
_map.Initialize();
RDebugUtils.currentLine=262159;
 //BA.debugLineNum = 262159;BA.debugLine="map.Put(\"id\", txtId.Text)";
_map.Put((Object)("id"),(Object)(parent.mostCurrent._txtid.getText()));
RDebugUtils.currentLine=262160;
 //BA.debugLineNum = 262160;BA.debugLine="map.Put(\"titulo\", txtTitulo.Text)";
_map.Put((Object)("titulo"),(Object)(parent.mostCurrent._txttitulo.getText()));
RDebugUtils.currentLine=262161;
 //BA.debugLineNum = 262161;BA.debugLine="map.Put(\"enCines\", txtEnCines.Checked)";
_map.Put((Object)("enCines"),(Object)(parent.mostCurrent._txtencines.getChecked()));
RDebugUtils.currentLine=262162;
 //BA.debugLineNum = 262162;BA.debugLine="map.Put(\"fechaEstreno\", txtFechaEstreno.Text)";
_map.Put((Object)("fechaEstreno"),(Object)(parent.mostCurrent._txtfechaestreno.getText()));
RDebugUtils.currentLine=262164;
 //BA.debugLineNum = 262164;BA.debugLine="Dim id As Int = txtId.Text";
_id = (int)(Double.parseDouble(parent.mostCurrent._txtid.getText()));
RDebugUtils.currentLine=262167;
 //BA.debugLineNum = 262167;BA.debugLine="jsonGenerator.Initialize(map)";
_jsongenerator.Initialize(_map);
RDebugUtils.currentLine=262168;
 //BA.debugLineNum = 262168;BA.debugLine="json = jsonGenerator.ToString";
_json = _jsongenerator.ToString();
RDebugUtils.currentLine=262171;
 //BA.debugLineNum = 262171;BA.debugLine="peticion.PutString(\"https://sqliteazurepelicula.a";
_peticion._putstring /*String*/ (null,"https://sqliteazurepelicula.azurewebsites.net/api/Peliculas/"+BA.NumberToString(_id),_json);
RDebugUtils.currentLine=262172;
 //BA.debugLineNum = 262172;BA.debugLine="peticion.GetRequest.SetContentType(\"application/j";
_peticion._getrequest /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ (null).SetContentType("application/json");
RDebugUtils.currentLine=262175;
 //BA.debugLineNum = 262175;BA.debugLine="Wait For (peticion) JobDone(respuesta As HttpJob)";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "main", "cmdupdate_click"), (Object)(_peticion));
this.state = 11;
return;
case 11:
//C
this.state = 5;
_respuesta = (b4a.example.httpjob) result[0];
;
RDebugUtils.currentLine=262176;
 //BA.debugLineNum = 262176;BA.debugLine="If respuesta.Success Then";
if (true) break;

case 5:
//if
this.state = 10;
if (_respuesta._success /*boolean*/ ) { 
this.state = 7;
}else {
this.state = 9;
}if (true) break;

case 7:
//C
this.state = 10;
RDebugUtils.currentLine=262177;
 //BA.debugLineNum = 262177;BA.debugLine="MsgboxAsync(\"Película actualizada correctamente!";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Película actualizada correctamente!"),BA.ObjectToCharSequence("Éxito"),processBA);
 if (true) break;

case 9:
//C
this.state = 10;
RDebugUtils.currentLine=262179;
 //BA.debugLineNum = 262179;BA.debugLine="MsgboxAsync(\"No se pudo actualizar la pelicula.";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("No se pudo actualizar la pelicula. "+_respuesta._errormessage /*String*/ ),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;

case 10:
//C
this.state = -1;
;
RDebugUtils.currentLine=262181;
 //BA.debugLineNum = 262181;BA.debugLine="peticion.Release";
_peticion._release /*String*/ (null);
RDebugUtils.currentLine=262182;
 //BA.debugLineNum = 262182;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
}