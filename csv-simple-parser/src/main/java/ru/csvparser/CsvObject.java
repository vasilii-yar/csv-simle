package ru.csvparser;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class CsvObject {
	@Id
	@NotNull
    private String ssoid;
	private long time;
	private String grpEvent;
	private String typeEvent;
	private String subtypeEvent;
	private String url;
	private String orgId;
	private String formId;
	private String sessionKey;
	private String serviceResp;
	private Date date;
}
