package com.buynsell.utils;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mailer {
	String from = null;
	String host = null;

	public Mailer(String from, String host) {
		if (from == null)
			this.from = "aneesh@labs.ecommit.com";
		if (host == null)
			this.host = "mail.labs.ecommit.com";
	}

	public void sendMail(String to, String subject, String contentBeforeImage, String image, String contentAfterImage) {
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(props, null);
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);

			MimeMultipart mp = new MimeMultipart();
			mp.setSubType("related");

			MimeBodyPart mbp1 = new MimeBodyPart();
			String html = contentBeforeImage + "<IMG SRC=cid:23abc@pc27>" + contentAfterImage;
			mbp1.setContent(html, "text/html");

			MimeBodyPart mbp2 = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(image);
			mbp2.setFileName(fds.getName());
			mbp2.setText("com.buynsell Mailer Reminds you !");
			mbp2.setDataHandler(new DataHandler(fds));
			mbp2.setHeader("Content-ID", "<23abc@pc27>");

			mp.addBodyPart(mbp1);
			mp.addBodyPart(mbp2);
			msg.setContent(mp);

			msg.setSentDate(new Date());
			Transport.send(msg);
			System.out.println(mp.getCount());
			System.out.println("\nMail was sent successfully.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}