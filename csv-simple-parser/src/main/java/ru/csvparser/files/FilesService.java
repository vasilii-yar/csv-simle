package ru.csvparser.files;

import org.springframework.web.multipart.MultipartFile;

public interface FilesService {
	void saveToDatabase(MultipartFile file);
}
