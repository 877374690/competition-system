package com.competition.system.domain;

import com.competition.common.annotation.Excel;
import com.competition.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 比赛申请对象 sys_competition_application
 *
 * @author competition
 * @date 2022-03-15
 */
public class SysCompetitionApplication extends BaseEntity {
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
	 * 简介
	 */
	@Excel(name = "简介")
	private String competitionDesc;

	/**
	 * 参赛人员
	 */
	@Excel(name = "参赛人员")
	private String contestants;

	/**
	 * 作品简介
	 */
	@Excel(name = "作品简介")
	private String introduction;

	/**
	 * 类型
	 */
	@Excel(name = "类型")
	private String applyType;

	/**
	 * 题目答案
	 */
	@Excel(name = "题目答案")
	private String answer;

	private String score;

	private String enrollName;

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

	public void setCompetitionDesc(String competitionDesc) {
		this.competitionDesc = competitionDesc;
	}

	public String getCompetitionDesc() {
		return competitionDesc;
	}

	public void setContestants(String contestants) {
		this.contestants = contestants;
	}

	public String getContestants() {
		return contestants;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAnswer() {
		return answer;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getEnrollName() {
		return enrollName;
	}

	public void setEnrollName(String enrollName) {
		this.enrollName = enrollName;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("id", getId())
				.append("competitionName", getCompetitionName())
				.append("competitionDesc", getCompetitionDesc())
				.append("contestants", getContestants())
				.append("introduction", getIntroduction())
				.append("applyType", getApplyType())
				.append("answer", getAnswer())
				.append("createTime", getCreateTime())
				.append("createBy", getCreateBy())
				.append("updateTime", getUpdateTime())
				.append("updateBy", getUpdateBy())
				.append("score",getScore())
				.toString();
	}
}
