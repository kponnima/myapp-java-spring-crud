package com.spring.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.security.crypto.codec.Base64;

public class ImageTaglib extends SimpleTagSupport {

	private byte[] usrImage;

	public void setUsrImage(byte[] usrImage) {
		this.usrImage = usrImage;
	}

	@Override
	public void doTag() throws JspException, IOException {

		try {
			JspWriter out = getJspContext().getOut();
			if (usrImage != null && usrImage.length > 0) {
				byte[] encodeBase64 = Base64.encode(usrImage);
				String base64Encoded = new String(encodeBase64, "UTF-8");
				out.print("data:image/jpeg;base64," + base64Encoded);
			}
		} catch (Exception e) {
			throw new JspException("Error: " + e.getMessage());
		}
	}

}
