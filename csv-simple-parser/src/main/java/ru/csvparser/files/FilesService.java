package ru.csvparser.files;

import org.springframework.web.multipart.MultipartFile;

// Интерфес, который реализуется загрузчиком файлов FilesStorageLoader.
public interface FilesService {
	void saveToDatabase(MultipartFile file);
	void init();
}
