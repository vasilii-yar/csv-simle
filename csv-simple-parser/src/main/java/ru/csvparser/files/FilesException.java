package ru.csvparser.files;

public class FilesException extends RuntimeException {
	
	public FilesException(String message) {
		super(message);
	}
	
	public FilesException(String message, Throwable e) {
		super(message, e);
	}
}
