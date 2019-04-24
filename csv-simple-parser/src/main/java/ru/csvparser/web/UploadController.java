package ru.csvparser.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ru.csvparser.files.FilesException;
import ru.csvparser.files.FilesService;

@Controller
@RequestMapping("/upload")
public class UploadController {
	private final FilesService fileStore;
	
	@Autowired
	public UploadController(FilesService fls) {
		this.fileStore = fls;
	}
	@PostMapping
	public String uploadProcess (@RequestParam MultipartFile file, Model model) {
		try {
			// очищаем бд перед каждой загрузкой файла.
			fileStore.init();
			fileStore.saveToDatabase(file);
		} catch(Exception ex) {
			StringBuilder stack = new StringBuilder();
			String msg = ex.getMessage();
			StackTraceElement[] a = ex.getStackTrace();
			for (StackTraceElement x : a) {
				stack.append(x.toString());
				stack.append("\n");
			}
			model.addAttribute("ErrorMessage", msg + "    \n" + stack.toString());
			return "error";
		}
		return "reports";
	}
}
