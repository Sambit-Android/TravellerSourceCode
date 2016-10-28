package com.bcits.entity;

import org.springframework.web.multipart.MultipartFile;

public class CircleDataUpload {

	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
