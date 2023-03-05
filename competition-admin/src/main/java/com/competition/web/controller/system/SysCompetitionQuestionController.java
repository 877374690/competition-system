package com.competition.web.controller.system;

import com.alibaba.fastjson.JSON;
import com.competition.common.annotation.Log;
import com.competition.common.core.controller.BaseController;
import com.competition.common.core.domain.AjaxResult;
import com.competition.common.core.page.TableDataInfo;
import com.competition.common.enums.BusinessType;
import com.competition.common.utils.StringUtils;
import com.competition.common.utils.poi.ExcelUtil;
import com.competition.system.domain.SysCompetitionQuestion;
import com.competition.system.service.ISysCompetitionQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * 题型设置Controller
 *
 * @author competition
 * @date 2022-03-13
 */
@RestController
@RequestMapping("/manage/question")
public class SysCompetitionQuestionController extends BaseController {
	@Autowired
	private ISysCompetitionQuestionService sysCompetitionQuestionService;

	/**
	 * 查询题型设置列表
	 */
	@PreAuthorize("@ss.hasPermi('manage:question:list')")
	@GetMapping("/list")
	public TableDataInfo list(SysCompetitionQuestion sysCompetitionQuestion) {
		startPage();
		List<SysCompetitionQuestion> list = sysCompetitionQuestionService.selectSysCompetitionQuestionList(sysCompetitionQuestion);
		return getDataTable(list);
	}

	/**
	 * 导出题型设置列表
	 */
	@PreAuthorize("@ss.hasPermi('manage:question:export')")
	@Log(title = "题型设置", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	public void export(HttpServletResponse response, SysCompetitionQuestion sysCompetitionQuestion) {
		List<SysCompetitionQuestion> list = sysCompetitionQuestionService.selectSysCompetitionQuestionList(sysCompetitionQuestion);
		ExcelUtil<SysCompetitionQuestion> util = new ExcelUtil<SysCompetitionQuestion>(SysCompetitionQuestion.class);
		util.exportExcel(response, list, "题型设置数据");
	}

	/**
	 * 获取题型设置详细信息
	 */
	@PreAuthorize("@ss.hasPermi('manage:question:query')")
	@GetMapping(value = "/{id}")
	public AjaxResult getInfo(@PathVariable("id") Long id) {
		return AjaxResult.success(sysCompetitionQuestionService.selectSysCompetitionQuestionById(id));
	}

	/**
	 * 新增题型设置
	 */
	@PreAuthorize("@ss.hasPermi('manage:question:add')")
	@Log(title = "题型设置", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@RequestBody String josnString) {
		HashMap<String, Object> map = JSON.parseObject(josnString, HashMap.class);
		if (StringUtils.isEmpty(map.get("competitionId").toString())){
			return AjaxResult.error("增加题项前请选择赛事");
		}
		List<SysCompetitionQuestion> sysCompetitionQuestions = JSON.parseArray(map.get("creditForm").toString(), SysCompetitionQuestion.class);
		for (SysCompetitionQuestion pojo :sysCompetitionQuestions){
			pojo.setCompetitionId(Long.valueOf(map.get("competitionId").toString()));
			pojo.setQuestionType("1");
		}

		for (SysCompetitionQuestion sysCompetitionQuestion :sysCompetitionQuestions){
			sysCompetitionQuestionService.insertSysCompetitionQuestion(sysCompetitionQuestion);
		}
		return toAjax(sysCompetitionQuestions.size());
	}

	/**
	 * 新增题型设置
	 */
	@PreAuthorize("@ss.hasPermi('manage:question:add')")
	@Log(title = "题型设置", businessType = BusinessType.INSERT)
	@PostMapping("/moment")
	public AjaxResult insert(@RequestBody String josnString) {
		HashMap<String, Object> map = JSON.parseObject(josnString, HashMap.class);
		if (StringUtils.isEmpty(map.get("competitionId1").toString())){
			return AjaxResult.error("增加题项前请选择赛事");
		}
		List<SysCompetitionQuestion> sysCompetitionQuestions = JSON.parseArray(map.get("momentForm").toString(), SysCompetitionQuestion.class);
		for (SysCompetitionQuestion pojo :sysCompetitionQuestions){
			pojo.setCompetitionId(Long.valueOf(map.get("competitionId1").toString()));
			pojo.setQuestionType("2");
		}

		for (SysCompetitionQuestion sysCompetitionQuestion :sysCompetitionQuestions){
			sysCompetitionQuestionService.insertSysCompetitionQuestion(sysCompetitionQuestion);
		}
		return toAjax(sysCompetitionQuestions.size());
	}

	/**
	 * 修改题型设置
	 */
	@PreAuthorize("@ss.hasPermi('manage:question:edit')")
	@Log(title = "题型设置", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@RequestBody SysCompetitionQuestion sysCompetitionQuestion) {
		return toAjax(sysCompetitionQuestionService.updateSysCompetitionQuestion(sysCompetitionQuestion));
	}

	/**
	 * 删除题型设置
	 */
	@PreAuthorize("@ss.hasPermi('manage:question:remove')")
	@Log(title = "题型设置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
	public AjaxResult remove(@PathVariable Long[] ids) {
		return toAjax(sysCompetitionQuestionService.deleteSysCompetitionQuestionByIds(ids));
	}
}
