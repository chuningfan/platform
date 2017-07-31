package org.march.platform.dto;

public class FileDemoDto {
	
	private String fileName;
	
	private String fullPath;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public FileDemoDto(String fileName, String fullPath) {
		this.fileName = fileName;
		this.fullPath = fullPath;
	}

	public FileDemoDto() {
	}
	
	
}
