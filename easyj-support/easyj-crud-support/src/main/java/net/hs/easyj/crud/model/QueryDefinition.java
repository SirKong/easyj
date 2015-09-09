package net.hs.easyj.crud.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 查询定义
 *
 * @author Gavin Hu
 * @create 2015/6/30
 */
public class QueryDefinition {

	private Long id;
	
	private String name;
	
	private String label;
	
	private String description;
	
	private String version;
	
	private String query;
	
	private Boolean enabled;
	
	private String pageSize;

	private String viewTemplate;
	
	private Date  createDate;

	private List<QueryInDefinition> inDefinitions = new ArrayList<QueryInDefinition>();

	private List<QueryOutDefinition> outDefinitions = new ArrayList<QueryOutDefinition>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getViewTemplate() {
		return viewTemplate;
	}

	public void setViewTemplate(String viewTemplate) {
		this.viewTemplate = viewTemplate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<QueryInDefinition> getInDefinitions() {
		return inDefinitions;
	}

	public void setInDefinitions(List<QueryInDefinition> inDefinitions) {
		this.inDefinitions = inDefinitions;
	}

	public List<QueryOutDefinition> getOutDefinitions() {
		return outDefinitions;
	}

	public void setOutDefinitions(List<QueryOutDefinition> outDefinitions) {
		this.outDefinitions = outDefinitions;
	}

}
