package com.competition.system.domain;

import com.competition.common.annotation.Excel;
import com.competition.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 添加赛事对象 sys_competition_apply
 *
 * @author competition
 * @date 2022-03-12
 */
public class SysCompetitionApply extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 竞赛名称
	 */
	@Excel(name = "竞赛名称")
	private String competitionName;

	/**
	 * 报名截止时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Excel(name = "报名截止时间", width = 30, dateFormat = "yyyy-MM-dd")
	private Date enrollEndTime;

	/**
	 * 比赛日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Excel(name = "比赛日期", width = 30, dateFormat = "yyyy-MM-dd")
	private Date competitionTime;

	/**
	 * 参赛人员限制
	 */
	@Excel(name = "参赛人员限制")
	private String personnelLimit;

	/**
	 * 比赛类型
	 */
	@Excel(name = "比赛类型")
	private String competitionType;

	private String checkStatus;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setCompetitionName(String competitionName) {
		this.competitionName = competitionName;
	}

	public String getCompetitionName() {
		return competitionName;
	}

	public void setEnrollEndTime(Date enrollEndTime) {
		this.enrollEndTime = enrollEndTime;
	}

	public Date getEnrollEndTime() {
		return enrollEndTime;
	}

	public void setCompetitionTime(Date competitionTime) {
		this.competitionTime = competitionTime;
	}

	public Date getCompetitionTime() {
		return competitionTime;
	}

	public void setPersonnelLimit(String personnelLimit) {
		this.personnelLimit = personnelLimit;
	}

	public String getPersonnelLimit() {
		return personnelLimit;
	}

	public void setCompetitionType(String competitionType) {
		this.competitionType = competitionType;
	}

	public String getCompetitionType() {
		return competitionType;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("id", getId())
				.append("competitionName", getCompetitionName())
				.append("enrollEndTime", getEnrollEndTime())
				.append("competitionTime", getCompetitionTime())
				.append("personnelLimit", getPersonnelLimit())
				.append("competitionType", getCompetitionType())
				.append("createTime", getCreateTime())
				.append("createBy", getCreateBy())
				.append("updateTime", getUpdateTime())
				.append("updateBy", getUpdateBy())
				.toString();
	}
}
