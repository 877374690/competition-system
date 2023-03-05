package com.competition.system.domain;

import com.competition.common.annotation.Excel;
import com.competition.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 报名对象 sys_competition_enroll
 *
 * @author competition
 * @date 2022-03-14
 */
public class SysCompetitionEnroll extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 报名姓名
	 */
	@Excel(name = "报名姓名")
	private String enrollName;

	/**
	 * 赛事名称
	 */
	@Excel(name = "赛事名称")
	private String competitionName;

	/**
	 * 手机号
	 */
	@Excel(name = "手机号")
	private String phoneNumber;

	/**
	 * 邮箱
	 */
	@Excel(name = "邮箱")
	private String email;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setEnrollName(String enrollName) {
		this.enrollName = enrollName;
	}

	public String getEnrollName() {
		return enrollName;
	}

	public void setCompetitionName(String competitionName) {
		this.competitionName = competitionName;
	}

	public String getCompetitionName() {
		return competitionName;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("id", getId())
				.append("enrollName", getEnrollName())
				.append("competitionName", getCompetitionName())
				.append("phoneNumber", getPhoneNumber())
				.append("email", getEmail())
				.append("createTime", getCreateTime())
				.append("createBy", getCreateBy())
				.append("updateTime", getUpdateTime())
				.append("updateBy", getUpdateBy())
				.toString();
	}
}
