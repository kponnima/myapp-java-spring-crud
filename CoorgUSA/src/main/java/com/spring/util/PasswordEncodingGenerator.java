package com.spring.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncodingGenerator {

	public static void main(String[] args) {
		String password = "Pooja89";
		// String userpassword =
		// "$2a$10$WKPyaHjCcCEy3idxAuHiCuhgwtrdZwkmN/umuBaqMeX4DbYo654Kq";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println(passwordEncoder.encode(password));
		/*
		 * if (passwordEncoder.matches(password, userpassword)){
		 * System.out.println("matches"); }else{
		 * System.out.println("Does not match"); }
		 */

	}

}
