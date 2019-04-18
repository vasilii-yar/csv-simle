package ru.csvparser.files;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.NoArgsConstructor;
import ru.csvparser.data.CsvObjectRepository;

@Service
public class FilesStorageLoader implements FilesService {
	private CsvObjectRepository objRep;
	private final String storage = "resources/files/";
	
	public FilesStorageLoader(CsvObjectRepository objRepo) {
		this.objRep = objRepo;
	}
	public void saveToDatabase(MultipartFile file) {
		String fileName = file.getName();
		Path store = FileSystems.getDefault().getPath(storage, fileName);
		try(InputStream inp = file.getInputStream()) {
			Files.copy(inp, store, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new FilesException("Can not save file!!!!!", e);
		}
	}
	
	public void init() {
		
	}
	
	private void dataSaver(Path p) {
		try(Stream<String> fileStr = Files.lines(p)) {
			
		} catch (IOException e) {
			throw new FilesException("Can not read file!!!!", e);
		}
	}

}
