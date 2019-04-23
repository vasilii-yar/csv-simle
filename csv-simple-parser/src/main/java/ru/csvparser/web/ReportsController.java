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
	
	@GetMapping(path="/reports")
	public String getReportsList() {
		return "reports";
	}
	
	@GetMapping(path="/report1")
	public String firstReportController(Model model) {
		List<String> head= new ArrayList<>();
		head.add("User Id");
		head.add("Group");
		head.add("URL");
		head.add("Form Id");
		
		model.addAttribute("head", head);
		
		List<CsvObject> csvObj = (List<CsvObject>) repo.findFirst15ByOrderByTimes();
		model.addAttribute("csvObjects", csvObj);
		return "top15table";
	}
	
	@GetMapping(path="/report2")
	public String secondReportController(Model model) {
		List<String> head= new ArrayList<>();
		head.add("User Id");
		head.add("Group");
		head.add("Type");
		head.add("URL");
		head.add("Code");
		
		model.addAttribute("head", head);
		List<CsvObject> csvObj = (List<CsvObject>) repo.findAllByOrgid("gibdd");
		model.addAttribute("csvObjects", csvObj);
		return "allgibddtable";
	}
	
	@GetMapping(path="/report3")
	public String thirdReportController(Model model) {
		List<String> head= new ArrayList<>();
		head.add("User Id");
		head.add("Group");
		head.add("Type");
		head.add("URL");
		head.add("Code");
		
		model.addAttribute("head", head);
		List<CsvObject> csvObj = (List<CsvObject>) repo.findWithTypeAndUrl("answer", "%#step_1");
		model.addAttribute("csvObjects", csvObj);
		return "answertable";
	}
}
