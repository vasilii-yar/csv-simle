package ru.csvparser.files;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ru.csvparser.CsvObject;
import ru.csvparser.data.CsvObjectRepository;

@Service
public class FilesStorageLoader implements FilesService {
	private CsvObjectRepository objRep;
	private String fileName;
	private String root = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();

	public FilesStorageLoader(CsvObjectRepository objRepo) {
		this.objRep = objRepo;
	}

	// Загрузка фала в папку files на сервре, и вызов метода по его обработке.
	public void saveToDatabase(MultipartFile file) {
		// Получение имени файла и пути для его сохранения
		fileName = file.getOriginalFilename();
		Path store = Paths.get(getRootPath(), "files" , fileName);
		// Сохранение файла на сервере
		try(InputStream inp = file.getInputStream()) {
			Files.copy(inp, store, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new FilesException("Can not save file!!!" + " --- " + store.toString() + " ---  \n", e);
		}
		//Вызов метода по обработке файла
		dataSaver(store);
		
	}

	// метод очистки базы
	public void init() {
		objRep.deleteAll();
		Path folder;
		try {
			folder = Paths.get(getRootPath(), "files");
		} catch(InvalidPathException ex) {
			throw new FilesException("Can not get path for cleaninig", ex);
		}
		try {
			Files.list(folder).forEach(f -> {
				try {
					Files.delete(f);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			throw new FilesException("Can not clean directory: " + folder.toString() + "!!");
		}
	}

	// Метод обработки загруженного файла, и сохранение данных в БД.
	private void dataSaver(Path p) {
		List<CsvObject> objToSave = new ArrayList<>();
		// Получение потока из сохраненного файла и обработка
		try (Stream<String> fileStr = Files.lines(p)) {
			fileStr.skip(1).filter(n -> !n.contains("Unauthorized")).forEach(n -> parseLine(n, objToSave));
		} catch (IOException e) {
			throw new FilesException("Can not read file!!!!", e);
		}
		
		// Сохранение полученых объектов в БД
		objRep.saveAll(objToSave);
	}

	// Метод обработки строк файла. Из каждой строки формируется один объект, и
	// добавляется в массив.
	private void parseLine(String line, List<CsvObject> lst) {
		String[] items = line.split(";");
		if (items.length == 12) {
			CsvObject obj = new CsvObject(items[0], Long.parseLong(items[1]), items[2], items[3], items[4], items[5],
					items[6], items[7], items[8], items[9], parseDate(items[items.length - 1]));
			lst.add(obj);
		} else {
			// Если строка не соответствует формату, то создаем лог.
			String errorMessage = "Can not parse line with first element: " + items[0]
					+ ". This line have unsupported format!" + "\n";
			Path storeLog = Paths.get(getRootPath(), "files", fileName + ".log");
			try {
				Files.write(storeLog, errorMessage.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE,
						StandardOpenOption.APPEND);
			} catch (Exception e) {
				throw new FilesException("Can not create log file", e);
			}
		}

	}

	// Получение даты из строки формата год-месяц-день-час
	private long parseDate(String x) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-DD-HH");
		try {
			return format.parse(x).getTime();
		} catch (ParseException ex) {
			return 0l;
		}
	}
	
	// Получаем директорию с файлами в зависимости от ОС.
	private String  getRootPath() {
		String OS = System.getenv("OS") == null ? "none" : System.getenv("OS");
		if (OS.equals("Windows_NT")) {
			return root.substring(1, root.length() -1).replace('/', '\\');
		} else {
			return root.substring(0, root.length() - 1);
		}
	}

}
