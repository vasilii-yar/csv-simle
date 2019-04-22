package ru.csvparser.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ru.csvparser.CsvObject;
import ru.csvparser.data.CsvObjectRepository;

@Controller
public class ReportsController {
	private CsvObjectRepository repo;
	
	@Autowired
	public ReportsController(CsvObjectRepository obRep) {
		this.repo = obRep;
	}
	
	@GetMapping(path="/report1")
	public String firstReportController(Model model) {
		List<String> head= new ArrayList<>();
		head.add("User Id");
		head.add("Group");
		head.add("URL");
		head.add("Form Id");
		
		model.addAttribute("head", head);
		
		List<CsvObject> csvObj = (List<CsvObject>) repo.findAll();
		model.addAttribute("csvObjects", csvObj);
		return "top15table";
	}
	
	@GetMapping(path="/report2")
	public String secondReportController(Model model) {
		
		return "allgibddtable";
	}
	
	@GetMapping(path="/report3")
	public String thirdReportController(Model model) {
		
		return "answertable";
	}
}
