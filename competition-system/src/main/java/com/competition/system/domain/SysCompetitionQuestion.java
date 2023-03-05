package com.competition.system.domain;

import com.competition.common.annotation.Excel;
import com.competition.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 题型设置对象 sys_competition_question
 *
 * @author competition
 * @date 2022-03-13
 */
public class SysCompetitionQuestion extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 赛事id
	 */
	@Excel(name = "赛事id")
	private Long competitionId;

	/**
	 * 题目
	 */
	@Excel(name = "题目")
	private String questionName;

	/**
	 * 分数
	 */
	@Excel(name = "分数")
	private String score;

	private String questionType;

	private String questionAnswer;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setCompetitionId(Long competitionId) {
		this.competitionId = competitionId;
	}

	public Long getCompetitionId() {
		return competitionId;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getScore() {
		return score;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("id", getId())
				.append("competitionId", getCompetitionId())
				.append("questionName", getQuestionName())
				.append("score", getScore())
				.append("questionType", getQuestionType())
				.toString();
	}
}
