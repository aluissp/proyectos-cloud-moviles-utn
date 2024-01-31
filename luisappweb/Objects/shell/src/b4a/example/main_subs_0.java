package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,36);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 36;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(8);
 BA.debugLineNum = 37;BA.debugLine="Activity.LoadLayout(\"Layout\")";
Debug.ShouldStop(16);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Layout")),main.mostCurrent.activityBA);
 BA.debugLineNum = 38;BA.debugLine="End Sub";
Debug.ShouldStop(32);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,194);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 194;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(2);
 BA.debugLineNum = 196;BA.debugLine="End Sub";
Debug.ShouldStop(8);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,190);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_resume");}
 BA.debugLineNum = 190;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 192;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _button1_click() throws Exception{
try {
		Debug.PushSubsStack("Button1_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,198);
if (RapidSub.canDelegate("button1_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","button1_click");}
 BA.debugLineNum = 198;BA.debugLine="Sub Button1_Click";
Debug.ShouldStop(32);
 BA.debugLineNum = 199;BA.debugLine="xui.MsgboxAsync(\"Hello world!\", \"B4X\")";
Debug.ShouldStop(64);
main._xui.runVoidMethod ("MsgboxAsync",main.processBA,(Object)(BA.ObjectToCharSequence("Hello world!")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("B4X"))));
 BA.debugLineNum = 200;BA.debugLine="End Sub";
Debug.ShouldStop(128);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static void  _cmddelete_click() throws Exception{
try {
		Debug.PushSubsStack("cmdDelete_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,120);
if (RapidSub.canDelegate("cmddelete_click")) { b4a.example.main.remoteMe.runUserSub(false, "main","cmddelete_click"); return;}
ResumableSub_cmdDelete_Click rsub = new ResumableSub_cmdDelete_Click(null);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_cmdDelete_Click extends BA.ResumableSub {
public ResumableSub_cmdDelete_Click(b4a.example.main parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.main parent;
RemoteObject _id = RemoteObject.createImmutable(0);
RemoteObject _peticion = RemoteObject.declareNull("b4a.example.httpjob");
RemoteObject _respuesta = RemoteObject.declareNull("b4a.example.httpjob");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("cmdDelete_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,120);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 122;BA.debugLine="If txtId.Text.Trim = \"\" Then";
Debug.ShouldStop(33554432);
if (true) break;

case 1:
//if
this.state = 4;
if (RemoteObject.solveBoolean("=",parent.mostCurrent._txtid.runMethod(true,"getText").runMethod(true,"trim"),BA.ObjectToString(""))) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 BA.debugLineNum = 123;BA.debugLine="MsgboxAsync(\"Debes ingresar el ID de la película";
Debug.ShouldStop(67108864);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Debes ingresar el ID de la película para eliminarla.")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),main.processBA);
 BA.debugLineNum = 124;BA.debugLine="Return";
Debug.ShouldStop(134217728);
if (true) return ;
 if (true) break;

case 4:
//C
this.state = 5;
;
 BA.debugLineNum = 127;BA.debugLine="Dim id As Int = txtId.Text";
Debug.ShouldStop(1073741824);
_id = BA.numberCast(int.class, parent.mostCurrent._txtid.runMethod(true,"getText"));Debug.locals.put("id", _id);Debug.locals.put("id", _id);
 BA.debugLineNum = 128;BA.debugLine="Dim peticion As HttpJob";
Debug.ShouldStop(-2147483648);
_peticion = RemoteObject.createNew ("b4a.example.httpjob");Debug.locals.put("peticion", _peticion);
 BA.debugLineNum = 129;BA.debugLine="peticion.Initialize(\"jobDelete\", Me)";
Debug.ShouldStop(1);
_peticion.runClassMethod (b4a.example.httpjob.class, "_initialize" /*RemoteObject*/ ,main.processBA,(Object)(BA.ObjectToString("jobDelete")),(Object)(main.getObject()));
 BA.debugLineNum = 132;BA.debugLine="peticion.Delete(\"https://sqliteazurepelicula.azur";
Debug.ShouldStop(8);
_peticion.runClassMethod (b4a.example.httpjob.class, "_delete" /*RemoteObject*/ ,(Object)(RemoteObject.concat(RemoteObject.createImmutable("https://sqliteazurepelicula.azurewebsites.net/api/Peliculas/"),_id)));
 BA.debugLineNum = 135;BA.debugLine="Wait For (peticion) JobDone(respuesta As HttpJob)";
Debug.ShouldStop(64);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","jobdone", main.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "main", "cmddelete_click"), (_peticion));
this.state = 11;
return;
case 11:
//C
this.state = 5;
_respuesta = (RemoteObject) result.getArrayElement(false,RemoteObject.createImmutable(0));Debug.locals.put("respuesta", _respuesta);
;
 BA.debugLineNum = 136;BA.debugLine="If respuesta.Success Then";
Debug.ShouldStop(128);
if (true) break;

case 5:
//if
this.state = 10;
if (_respuesta.getField(true,"_success" /*RemoteObject*/ ).<Boolean>get().booleanValue()) { 
this.state = 7;
}else {
this.state = 9;
}if (true) break;

case 7:
//C
this.state = 10;
 BA.debugLineNum = 137;BA.debugLine="MsgboxAsync(\"Película borrada correctamente!\", \"";
Debug.ShouldStop(256);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Película borrada correctamente!")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Éxito"))),main.processBA);
 if (true) break;

case 9:
//C
this.state = 10;
 BA.debugLineNum = 139;BA.debugLine="MsgboxAsync(\"No se pudo borrar la película. \" &";
Debug.ShouldStop(1024);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("No se pudo borrar la película. "),_respuesta.getField(true,"_errormessage" /*RemoteObject*/ )))),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),main.processBA);
 if (true) break;

case 10:
//C
this.state = -1;
;
 BA.debugLineNum = 142;BA.debugLine="peticion.Release";
Debug.ShouldStop(8192);
_peticion.runClassMethod (b4a.example.httpjob.class, "_release" /*RemoteObject*/ );
 BA.debugLineNum = 143;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
if (true) break;

            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static void  _jobdone(RemoteObject _respuesta) throws Exception{
}
public static void  _cmdinsertar_click() throws Exception{
try {
		Debug.PushSubsStack("cmdInsertar_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,147);
if (RapidSub.canDelegate("cmdinsertar_click")) { b4a.example.main.remoteMe.runUserSub(false, "main","cmdinsertar_click"); return;}
ResumableSub_cmdInsertar_Click rsub = new ResumableSub_cmdInsertar_Click(null);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_cmdInsertar_Click extends BA.ResumableSub {
public ResumableSub_cmdInsertar_Click(b4a.example.main parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.main parent;
RemoteObject _map = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _json = RemoteObject.createImmutable("");
RemoteObject _jsongenerator = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator");
RemoteObject _titulo = RemoteObject.createImmutable("");
RemoteObject _fechaestreno = RemoteObject.createImmutable("");
RemoteObject _encines = RemoteObject.createImmutable(false);
RemoteObject _peticion = RemoteObject.declareNull("b4a.example.httpjob");
RemoteObject _respuesta = RemoteObject.declareNull("b4a.example.httpjob");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("cmdInsertar_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,147);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 148;BA.debugLine="Dim map As Map";
Debug.ShouldStop(524288);
_map = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("map", _map);
 BA.debugLineNum = 149;BA.debugLine="Dim json As String";
Debug.ShouldStop(1048576);
_json = RemoteObject.createImmutable("");Debug.locals.put("json", _json);
 BA.debugLineNum = 150;BA.debugLine="Dim jsonGenerator As JSONGenerator";
Debug.ShouldStop(2097152);
_jsongenerator = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator");Debug.locals.put("jsonGenerator", _jsongenerator);
 BA.debugLineNum = 153;BA.debugLine="Dim titulo As String = txtTitulo.Text";
Debug.ShouldStop(16777216);
_titulo = parent.mostCurrent._txttitulo.runMethod(true,"getText");Debug.locals.put("titulo", _titulo);Debug.locals.put("titulo", _titulo);
 BA.debugLineNum = 154;BA.debugLine="Dim fechaEstreno As String = txtFechaEstreno.Text";
Debug.ShouldStop(33554432);
_fechaestreno = parent.mostCurrent._txtfechaestreno.runMethod(true,"getText");Debug.locals.put("fechaEstreno", _fechaestreno);Debug.locals.put("fechaEstreno", _fechaestreno);
 BA.debugLineNum = 155;BA.debugLine="Dim enCines As Boolean = txtEnCines.Checked";
Debug.ShouldStop(67108864);
_encines = parent.mostCurrent._txtencines.runMethod(true,"getChecked");Debug.locals.put("enCines", _encines);Debug.locals.put("enCines", _encines);
 BA.debugLineNum = 158;BA.debugLine="map.Initialize";
Debug.ShouldStop(536870912);
_map.runVoidMethod ("Initialize");
 BA.debugLineNum = 159;BA.debugLine="map.Put(\"titulo\", titulo)";
Debug.ShouldStop(1073741824);
_map.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("titulo"))),(Object)((_titulo)));
 BA.debugLineNum = 160;BA.debugLine="map.Put(\"enCines\", enCines)";
Debug.ShouldStop(-2147483648);
_map.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("enCines"))),(Object)((_encines)));
 BA.debugLineNum = 161;BA.debugLine="map.Put(\"fechaEstreno\", fechaEstreno)";
Debug.ShouldStop(1);
_map.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("fechaEstreno"))),(Object)((_fechaestreno)));
 BA.debugLineNum = 164;BA.debugLine="jsonGenerator.Initialize(map)";
Debug.ShouldStop(8);
_jsongenerator.runVoidMethod ("Initialize",(Object)(_map));
 BA.debugLineNum = 165;BA.debugLine="json = jsonGenerator.ToPrettyString(2)";
Debug.ShouldStop(16);
_json = _jsongenerator.runMethod(true,"ToPrettyString",(Object)(BA.numberCast(int.class, 2)));Debug.locals.put("json", _json);
 BA.debugLineNum = 168;BA.debugLine="Dim peticion As HttpJob";
Debug.ShouldStop(128);
_peticion = RemoteObject.createNew ("b4a.example.httpjob");Debug.locals.put("peticion", _peticion);
 BA.debugLineNum = 169;BA.debugLine="peticion.Initialize(\"jobInsertar\", Me)";
Debug.ShouldStop(256);
_peticion.runClassMethod (b4a.example.httpjob.class, "_initialize" /*RemoteObject*/ ,main.processBA,(Object)(BA.ObjectToString("jobInsertar")),(Object)(main.getObject()));
 BA.debugLineNum = 170;BA.debugLine="peticion.PostString(\"https://sqliteazurepelicula.";
Debug.ShouldStop(512);
_peticion.runClassMethod (b4a.example.httpjob.class, "_poststring" /*RemoteObject*/ ,(Object)(BA.ObjectToString("https://sqliteazurepelicula.azurewebsites.net/api/Peliculas/")),(Object)(_json));
 BA.debugLineNum = 171;BA.debugLine="peticion.GetRequest.SetContentType(\"application/j";
Debug.ShouldStop(1024);
_peticion.runClassMethod (b4a.example.httpjob.class, "_getrequest" /*RemoteObject*/ ).runVoidMethod ("SetContentType",(Object)(RemoteObject.createImmutable("application/json")));
 BA.debugLineNum = 174;BA.debugLine="Wait For (peticion) JobDone(respuesta As HttpJob)";
Debug.ShouldStop(8192);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","jobdone", main.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "main", "cmdinsertar_click"), (_peticion));
this.state = 7;
return;
case 7:
//C
this.state = 1;
_respuesta = (RemoteObject) result.getArrayElement(false,RemoteObject.createImmutable(0));Debug.locals.put("respuesta", _respuesta);
;
 BA.debugLineNum = 175;BA.debugLine="If respuesta.Success Then";
Debug.ShouldStop(16384);
if (true) break;

case 1:
//if
this.state = 6;
if (_respuesta.getField(true,"_success" /*RemoteObject*/ ).<Boolean>get().booleanValue()) { 
this.state = 3;
}else {
this.state = 5;
}if (true) break;

case 3:
//C
this.state = 6;
 BA.debugLineNum = 176;BA.debugLine="MsgboxAsync(\"Película insertada correctamente.\",";
Debug.ShouldStop(32768);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Película insertada correctamente.")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Éxito"))),main.processBA);
 if (true) break;

case 5:
//C
this.state = 6;
 BA.debugLineNum = 178;BA.debugLine="MsgboxAsync(\"No se pudo insertar la película. \"";
Debug.ShouldStop(131072);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("No se pudo insertar la película. "),_respuesta.getField(true,"_errormessage" /*RemoteObject*/ )))),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),main.processBA);
 if (true) break;

case 6:
//C
this.state = -1;
;
 BA.debugLineNum = 182;BA.debugLine="peticion.Release";
Debug.ShouldStop(2097152);
_peticion.runClassMethod (b4a.example.httpjob.class, "_release" /*RemoteObject*/ );
 BA.debugLineNum = 183;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
if (true) break;

            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static void  _cmdread_click() throws Exception{
try {
		Debug.PushSubsStack("cmdRead_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,42);
if (RapidSub.canDelegate("cmdread_click")) { b4a.example.main.remoteMe.runUserSub(false, "main","cmdread_click"); return;}
ResumableSub_cmdRead_Click rsub = new ResumableSub_cmdRead_Click(null);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_cmdRead_Click extends BA.ResumableSub {
public ResumableSub_cmdRead_Click(b4a.example.main parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.main parent;
RemoteObject _peliculaid = RemoteObject.createImmutable(0);
RemoteObject _peticion = RemoteObject.declareNull("b4a.example.httpjob");
RemoteObject _respuesta = RemoteObject.declareNull("b4a.example.httpjob");
RemoteObject _json = RemoteObject.createImmutable("");
RemoteObject _jsonparser = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.JSONParser");
RemoteObject _map = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("cmdRead_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,42);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 43;BA.debugLine="Dim peliculaId As Int";
Debug.ShouldStop(1024);
_peliculaid = RemoteObject.createImmutable(0);Debug.locals.put("peliculaId", _peliculaid);
 BA.debugLineNum = 44;BA.debugLine="Try";
Debug.ShouldStop(2048);
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
 BA.debugLineNum = 45;BA.debugLine="peliculaId = txtId.Text";
Debug.ShouldStop(4096);
_peliculaid = BA.numberCast(int.class, parent.mostCurrent._txtid.runMethod(true,"getText"));Debug.locals.put("peliculaId", _peliculaid);
 Debug.CheckDeviceExceptions();
if (true) break;

case 5:
//C
this.state = 6;
this.catchState = 0;
 BA.debugLineNum = 47;BA.debugLine="MsgboxAsync(\"El ID de la película debe ser numér";
Debug.ShouldStop(16384);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("El ID de la película debe ser numérico.")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),main.processBA);
 BA.debugLineNum = 48;BA.debugLine="Return";
Debug.ShouldStop(32768);
if (true) return ;
 if (true) break;
if (true) break;

case 6:
//C
this.state = 7;
this.catchState = 0;
;
 BA.debugLineNum = 51;BA.debugLine="Dim peticion As HttpJob";
Debug.ShouldStop(262144);
_peticion = RemoteObject.createNew ("b4a.example.httpjob");Debug.locals.put("peticion", _peticion);
 BA.debugLineNum = 52;BA.debugLine="peticion.Initialize(\"\", Me)";
Debug.ShouldStop(524288);
_peticion.runClassMethod (b4a.example.httpjob.class, "_initialize" /*RemoteObject*/ ,main.processBA,(Object)(BA.ObjectToString("")),(Object)(main.getObject()));
 BA.debugLineNum = 53;BA.debugLine="peticion.Download(\"https://sqliteazurepelicula.az";
Debug.ShouldStop(1048576);
_peticion.runClassMethod (b4a.example.httpjob.class, "_download" /*RemoteObject*/ ,(Object)(RemoteObject.concat(RemoteObject.createImmutable("https://sqliteazurepelicula.azurewebsites.net/api/Peliculas/"),_peliculaid)));
 BA.debugLineNum = 54;BA.debugLine="Wait For (peticion) JobDone(respuesta As HttpJob)";
Debug.ShouldStop(2097152);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","jobdone", main.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "main", "cmdread_click"), (_peticion));
this.state = 13;
return;
case 13:
//C
this.state = 7;
_respuesta = (RemoteObject) result.getArrayElement(false,RemoteObject.createImmutable(0));Debug.locals.put("respuesta", _respuesta);
;
 BA.debugLineNum = 56;BA.debugLine="If respuesta.Success Then";
Debug.ShouldStop(8388608);
if (true) break;

case 7:
//if
this.state = 12;
if (_respuesta.getField(true,"_success" /*RemoteObject*/ ).<Boolean>get().booleanValue()) { 
this.state = 9;
}else {
this.state = 11;
}if (true) break;

case 9:
//C
this.state = 12;
 BA.debugLineNum = 57;BA.debugLine="Dim json As String";
Debug.ShouldStop(16777216);
_json = RemoteObject.createImmutable("");Debug.locals.put("json", _json);
 BA.debugLineNum = 58;BA.debugLine="json = respuesta.GetString";
Debug.ShouldStop(33554432);
_json = _respuesta.runClassMethod (b4a.example.httpjob.class, "_getstring" /*RemoteObject*/ );Debug.locals.put("json", _json);
 BA.debugLineNum = 60;BA.debugLine="Dim jsonParser As JSONParser";
Debug.ShouldStop(134217728);
_jsonparser = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.JSONParser");Debug.locals.put("jsonParser", _jsonparser);
 BA.debugLineNum = 61;BA.debugLine="jsonParser.Initialize(json)";
Debug.ShouldStop(268435456);
_jsonparser.runVoidMethod ("Initialize",(Object)(_json));
 BA.debugLineNum = 63;BA.debugLine="Dim map As Map = jsonParser.NextObject";
Debug.ShouldStop(1073741824);
_map = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_map = _jsonparser.runMethod(false,"NextObject");Debug.locals.put("map", _map);Debug.locals.put("map", _map);
 BA.debugLineNum = 66;BA.debugLine="txtId.Text= map.Get(\"id\")";
Debug.ShouldStop(2);
parent.mostCurrent._txtid.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_map.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("id"))))));
 BA.debugLineNum = 67;BA.debugLine="txtTitulo.Text = map.Get(\"titulo\")";
Debug.ShouldStop(4);
parent.mostCurrent._txttitulo.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_map.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("titulo"))))));
 BA.debugLineNum = 68;BA.debugLine="txtEnCines.Checked = map.Get(\"enCines\")";
Debug.ShouldStop(8);
parent.mostCurrent._txtencines.runMethodAndSync(true,"setChecked",BA.ObjectToBoolean(_map.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("enCines"))))));
 BA.debugLineNum = 69;BA.debugLine="txtFechaEstreno.Text = map.Get(\"fechaEstreno\")";
Debug.ShouldStop(16);
parent.mostCurrent._txtfechaestreno.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_map.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("fechaEstreno"))))));
 if (true) break;

case 11:
//C
this.state = 12;
 BA.debugLineNum = 72;BA.debugLine="MsgboxAsync(\"No se pudo obtener la información d";
Debug.ShouldStop(128);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("No se pudo obtener la información de la película. "),_respuesta.getField(true,"_errormessage" /*RemoteObject*/ )))),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),main.processBA);
 if (true) break;

case 12:
//C
this.state = -1;
;
 BA.debugLineNum = 75;BA.debugLine="peticion.Release";
Debug.ShouldStop(1024);
_peticion.runClassMethod (b4a.example.httpjob.class, "_release" /*RemoteObject*/ );
 BA.debugLineNum = 76;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
BA.rdebugUtils.runVoidMethod("setLastException",main.processBA, e0.toString());}
            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static void  _cmdupdate_click() throws Exception{
try {
		Debug.PushSubsStack("cmdUpdate_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,80);
if (RapidSub.canDelegate("cmdupdate_click")) { b4a.example.main.remoteMe.runUserSub(false, "main","cmdupdate_click"); return;}
ResumableSub_cmdUpdate_Click rsub = new ResumableSub_cmdUpdate_Click(null);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_cmdUpdate_Click extends BA.ResumableSub {
public ResumableSub_cmdUpdate_Click(b4a.example.main parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.main parent;
RemoteObject _map = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _json = RemoteObject.createImmutable("");
RemoteObject _jsongenerator = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator");
RemoteObject _peticion = RemoteObject.declareNull("b4a.example.httpjob");
RemoteObject _id = RemoteObject.createImmutable(0);
RemoteObject _respuesta = RemoteObject.declareNull("b4a.example.httpjob");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("cmdUpdate_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,80);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 82;BA.debugLine="If txtId.Text.Trim = \"\" Then";
Debug.ShouldStop(131072);
if (true) break;

case 1:
//if
this.state = 4;
if (RemoteObject.solveBoolean("=",parent.mostCurrent._txtid.runMethod(true,"getText").runMethod(true,"trim"),BA.ObjectToString(""))) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 BA.debugLineNum = 83;BA.debugLine="MsgboxAsync(\"El ID de la película es necesario p";
Debug.ShouldStop(262144);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("El ID de la película es necesario para actualizar.")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),main.processBA);
 BA.debugLineNum = 84;BA.debugLine="Return";
Debug.ShouldStop(524288);
if (true) return ;
 if (true) break;

case 4:
//C
this.state = 5;
;
 BA.debugLineNum = 88;BA.debugLine="Dim map As Map";
Debug.ShouldStop(8388608);
_map = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("map", _map);
 BA.debugLineNum = 89;BA.debugLine="Dim json As String";
Debug.ShouldStop(16777216);
_json = RemoteObject.createImmutable("");Debug.locals.put("json", _json);
 BA.debugLineNum = 90;BA.debugLine="Dim jsonGenerator As JSONGenerator";
Debug.ShouldStop(33554432);
_jsongenerator = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator");Debug.locals.put("jsonGenerator", _jsongenerator);
 BA.debugLineNum = 91;BA.debugLine="Dim peticion As HttpJob";
Debug.ShouldStop(67108864);
_peticion = RemoteObject.createNew ("b4a.example.httpjob");Debug.locals.put("peticion", _peticion);
 BA.debugLineNum = 92;BA.debugLine="peticion.Initialize(\"jobUpdate\", Me)";
Debug.ShouldStop(134217728);
_peticion.runClassMethod (b4a.example.httpjob.class, "_initialize" /*RemoteObject*/ ,main.processBA,(Object)(BA.ObjectToString("jobUpdate")),(Object)(main.getObject()));
 BA.debugLineNum = 94;BA.debugLine="map.Initialize";
Debug.ShouldStop(536870912);
_map.runVoidMethod ("Initialize");
 BA.debugLineNum = 95;BA.debugLine="map.Put(\"id\", txtId.Text)";
Debug.ShouldStop(1073741824);
_map.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("id"))),(Object)((parent.mostCurrent._txtid.runMethod(true,"getText"))));
 BA.debugLineNum = 96;BA.debugLine="map.Put(\"titulo\", txtTitulo.Text)";
Debug.ShouldStop(-2147483648);
_map.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("titulo"))),(Object)((parent.mostCurrent._txttitulo.runMethod(true,"getText"))));
 BA.debugLineNum = 97;BA.debugLine="map.Put(\"enCines\", txtEnCines.Checked)";
Debug.ShouldStop(1);
_map.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("enCines"))),(Object)((parent.mostCurrent._txtencines.runMethod(true,"getChecked"))));
 BA.debugLineNum = 98;BA.debugLine="map.Put(\"fechaEstreno\", txtFechaEstreno.Text)";
Debug.ShouldStop(2);
_map.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("fechaEstreno"))),(Object)((parent.mostCurrent._txtfechaestreno.runMethod(true,"getText"))));
 BA.debugLineNum = 100;BA.debugLine="Dim id As Int = txtId.Text";
Debug.ShouldStop(8);
_id = BA.numberCast(int.class, parent.mostCurrent._txtid.runMethod(true,"getText"));Debug.locals.put("id", _id);Debug.locals.put("id", _id);
 BA.debugLineNum = 103;BA.debugLine="jsonGenerator.Initialize(map)";
Debug.ShouldStop(64);
_jsongenerator.runVoidMethod ("Initialize",(Object)(_map));
 BA.debugLineNum = 104;BA.debugLine="json = jsonGenerator.ToString";
Debug.ShouldStop(128);
_json = _jsongenerator.runMethod(true,"ToString");Debug.locals.put("json", _json);
 BA.debugLineNum = 107;BA.debugLine="peticion.PutString(\"https://sqliteazurepelicula.a";
Debug.ShouldStop(1024);
_peticion.runClassMethod (b4a.example.httpjob.class, "_putstring" /*RemoteObject*/ ,(Object)(RemoteObject.concat(RemoteObject.createImmutable("https://sqliteazurepelicula.azurewebsites.net/api/Peliculas/"),_id)),(Object)(_json));
 BA.debugLineNum = 108;BA.debugLine="peticion.GetRequest.SetContentType(\"application/j";
Debug.ShouldStop(2048);
_peticion.runClassMethod (b4a.example.httpjob.class, "_getrequest" /*RemoteObject*/ ).runVoidMethod ("SetContentType",(Object)(RemoteObject.createImmutable("application/json")));
 BA.debugLineNum = 111;BA.debugLine="Wait For (peticion) JobDone(respuesta As HttpJob)";
Debug.ShouldStop(16384);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","jobdone", main.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "main", "cmdupdate_click"), (_peticion));
this.state = 11;
return;
case 11:
//C
this.state = 5;
_respuesta = (RemoteObject) result.getArrayElement(false,RemoteObject.createImmutable(0));Debug.locals.put("respuesta", _respuesta);
;
 BA.debugLineNum = 112;BA.debugLine="If respuesta.Success Then";
Debug.ShouldStop(32768);
if (true) break;

case 5:
//if
this.state = 10;
if (_respuesta.getField(true,"_success" /*RemoteObject*/ ).<Boolean>get().booleanValue()) { 
this.state = 7;
}else {
this.state = 9;
}if (true) break;

case 7:
//C
this.state = 10;
 BA.debugLineNum = 113;BA.debugLine="MsgboxAsync(\"Película actualizada correctamente!";
Debug.ShouldStop(65536);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Película actualizada correctamente!")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Éxito"))),main.processBA);
 if (true) break;

case 9:
//C
this.state = 10;
 BA.debugLineNum = 115;BA.debugLine="MsgboxAsync(\"No se pudo actualizar la pelicula.";
Debug.ShouldStop(262144);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("No se pudo actualizar la pelicula. "),_respuesta.getField(true,"_errormessage" /*RemoteObject*/ )))),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),main.processBA);
 if (true) break;

case 10:
//C
this.state = -1;
;
 BA.debugLineNum = 117;BA.debugLine="peticion.Release";
Debug.ShouldStop(1048576);
_peticion.runClassMethod (b4a.example.httpjob.class, "_release" /*RemoteObject*/ );
 BA.debugLineNum = 118;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
if (true) break;

            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 28;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 30;BA.debugLine="Private txtId As EditText";
main.mostCurrent._txtid = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 31;BA.debugLine="Private txtTitulo As EditText";
main.mostCurrent._txttitulo = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 32;BA.debugLine="Private txtFechaEstreno As EditText";
main.mostCurrent._txtfechaestreno = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 33;BA.debugLine="Private txtEnCines As CheckBox";
main.mostCurrent._txtencines = RemoteObject.createNew ("anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper");
 //BA.debugLineNum = 34;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main_subs_0._process_globals();
starter_subs_0._process_globals();
httputils2service_subs_0._process_globals();
main.myClass = BA.getDeviceClass ("b4a.example.main");
starter.myClass = BA.getDeviceClass ("b4a.example.starter");
httputils2service.myClass = BA.getDeviceClass ("b4a.example.httputils2service");
httpjob.myClass = BA.getDeviceClass ("b4a.example.httpjob");
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 19;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 22;BA.debugLine="Private xui As XUI";
main._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
}