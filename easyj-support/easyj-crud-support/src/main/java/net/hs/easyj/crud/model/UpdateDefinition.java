package net.hs.easyj.crud.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 更新定义
 *
 * @author Gavin Hu
 * @create 2015/6/30
 */
public class UpdateDefinition {

	private Long id;

	private String name;

	private String label;

	private String description;

	private String version;

	private String update1;

	private String update2;

	private String update3;

	private Boolean enabled;

	private String viewTemplate;

	private Boolean update1Enabled;

	private Boolean update2Enabled;

	private Boolean update3Enabled;

	private List<UpdateInDefinition> inDefinitions = new ArrayList<UpdateInDefinition>();

	/**
     * 三位数，每一位分别对应update1,update2,update3的实现类型 {@link Constants}
     */
	private String implType = "000";

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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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

	public String getUpdate1() {
		return update1;
	}

	public void setUpdate1(String update1) {
		this.update1 = update1;
	}

	public String getUpdate2() {
		return update2;
	}

	public void setUpdate2(String update2) {
		this.update2 = update2;
	}

	public String getUpdate3() {
		return update3;
	}

	public void setUpdate3(String update3) {
		this.update3 = update3;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getViewTemplate() {
		return viewTemplate;
	}

	public void setViewTemplate(String viewTemplate) {
		this.viewTemplate = viewTemplate;
	}

	public List<UpdateInDefinition> getInDefinitions() {
		return inDefinitions;
	}

	public void setInDefinitions(List<UpdateInDefinition> inDefinitions) {
		this.inDefinitions = inDefinitions;
	}

	public Boolean getUpdate1Enabled() {
		return update1Enabled;
	}

	public void setUpdate1Enabled(Boolean update1Enabled) {
		this.update1Enabled = update1Enabled;
	}

	public Boolean getUpdate2Enabled() {
		return update2Enabled;
	}

	public void setUpdate2Enabled(Boolean update2Enabled) {
		this.update2Enabled = update2Enabled;
	}

	public Boolean getUpdate3Enabled() {
		return update3Enabled;
	}

	public void setUpdate3Enabled(Boolean update3Enabled) {
		this.update3Enabled = update3Enabled;
	}

	public String getImplType() {
		return implType;
	}

	public void setImplType(String implType) {
		this.implType = implType;
	}
}
