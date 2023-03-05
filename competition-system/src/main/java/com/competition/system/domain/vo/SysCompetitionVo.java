package com.competition.system.domain.vo;

import com.competition.common.annotation.Excel;

/**
 * @package: com.competition.system.domain.vo
 * @author: luweihong
 * @description:
 * @create: 2022/4/10 17:04
 * @version: 1.0
 */
public class SysCompetitionVo {

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

	private String contestants;

	private String competitionName;

	public Long getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(Long competitionId) {
		this.competitionId = competitionId;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
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

	public String getContestants() {
		return contestants;
	}

	public void setContestants(String contestants) {
		this.contestants = contestants;
	}

	public String getCompetitionName() {
		return competitionName;
	}

	public void setCompetitionName(String competitionName) {
		this.competitionName = competitionName;
	}
}
