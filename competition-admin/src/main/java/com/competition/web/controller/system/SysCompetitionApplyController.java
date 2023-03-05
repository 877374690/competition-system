package com.competition.web.controller.system;

import com.competition.common.annotation.Log;
import com.competition.common.core.controller.BaseController;
import com.competition.common.core.domain.AjaxResult;
import com.competition.common.core.page.TableDataInfo;
import com.competition.common.enums.BusinessType;
import com.competition.common.utils.poi.ExcelUtil;
import com.competition.system.domain.SysCompetitionApply;
import com.competition.system.domain.vo.SysCompetitionVo;
import com.competition.system.service.ISysCompetitionApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 添加赛事Controller
 *
 * @author competition
 * @date 2022-03-12
 */
@RestController
@RequestMapping("/manage/online")
public class SysCompetitionApplyController extends BaseController {
	@Autowired
	private ISysCompetitionApplyService sysCompetitionApplyService;

	/**
	 * 查询添加赛事列表
	 */
	@PreAuthorize("@ss.hasPermi('manage:online:list')")
	@GetMapping("/list")
	public TableDataInfo list(SysCompetitionApply sysCompetitionApply) {
		startPage();
		List<SysCompetitionApply> list = sysCompetitionApplyService.selectSysCompetitionApplyList(sysCompetitionApply);
		return getDataTable(list);
	}

	@PreAuthorize("@ss.hasPermi('manage:online:list')")
	@GetMapping("/answerList")
	public TableDataInfo answerList() {
		startPage();
		List<SysCompetitionVo> list = sysCompetitionApplyService.selectSysAnswerList();
		return getDataTable(list);
	}
	/**
	 * 查询已审核的赛事
	 */
	@PreAuthorize("@ss.hasPermi('manage:question:list')")
	@GetMapping("/getCompetitionList")
	public List<SysCompetitionApply> getCompetitionList(SysCompetitionApply sysCompetitionApply) {
		return sysCompetitionApplyService.getCompetitionList(sysCompetitionApply);
	}

	/**
	 * 查询已审核的赛事
	 */
	@PreAuthorize("@ss.hasPermi('manage:question:list')")
	@GetMapping("/getCompetitionList1")
	public List<SysCompetitionApply> getCompetitionList1(SysCompetitionApply sysCompetitionApply) {
		return sysCompetitionApplyService.getCompetitionList1(sysCompetitionApply);
	}

	@PreAuthorize("@ss.hasPermi('manage:question:list')")
	@GetMapping("/getWorkLists")
	public List<SysCompetitionApply> getWorkLists(SysCompetitionApply sysCompetitionApply) {
		return sysCompetitionApplyService.getWorkLists(sysCompetitionApply);
	}


	/**
	 * 查询未审核赛事列表
	 */
	@PreAuthorize("@ss.hasPermi('manage:check:list')")
	@GetMapping("/check/list")
	public TableDataInfo uncheckList(SysCompetitionApply sysCompetitionApply) {
		startPage();
		List<SysCompetitionApply> list = sysCompetitionApplyService.selectuncheckList(sysCompetitionApply);
		return getDataTable(list);
	}

	/**
	 * 查询未审核赛事列表
	 */
	@PreAuthorize("@ss.hasPermi('manage:check:list')")
	@GetMapping("/cancel/list")
	public TableDataInfo listCancel(SysCompetitionApply sysCompetitionApply) {
		startPage();
		List<SysCompetitionApply> list = sysCompetitionApplyService.listCancel(sysCompetitionApply);
		return getDataTable(list);
	}

	/**
	 * 导出添加赛事列表
	 */
	@PreAuthorize("@ss.hasPermi('manage:online:export')")
	@Log(title = "添加赛事", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	public void export(HttpServletResponse response, SysCompetitionApply sysCompetitionApply) {
		List<SysCompetitionApply> list = sysCompetitionApplyService.selectSysCompetitionApplyList(sysCompetitionApply);
		ExcelUtil<SysCompetitionApply> util = new ExcelUtil<SysCompetitionApply>(SysCompetitionApply.class);
		util.exportExcel(response, list, "添加赛事数据");
	}

	/**
	 * 获取添加赛事详细信息
	 */
	@PreAuthorize("@ss.hasPermi('manage:online:query')")
	@GetMapping(value = "/{id}")
	public AjaxResult getInfo(@PathVariable("id") Long id) {
		return AjaxResult.success(sysCompetitionApplyService.selectSysCompetitionApplyById(id));
	}

	/**
	 * 新增添加赛事
	 */
	@PreAuthorize("@ss.hasPermi('manage:online:add')")
	@Log(title = "添加赛事", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@RequestBody SysCompetitionApply sysCompetitionApply) {
		sysCompetitionApply.setCheckStatus("0");
		return toAjax(sysCompetitionApplyService.insertSysCompetitionApply(sysCompetitionApply));
	}

	/**
	 * 修改添加赛事
	 */
	@PreAuthorize("@ss.hasPermi('manage:online:edit')")
	@Log(title = "添加赛事", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@RequestBody SysCompetitionApply sysCompetitionApply) {
		return toAjax(sysCompetitionApplyService.updateSysCompetitionApply(sysCompetitionApply));
	}

	/**
	 * 删除添加赛事
	 */
	@PreAuthorize("@ss.hasPermi('manage:online:remove')")
	@Log(title = "添加赛事", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
	public AjaxResult remove(@PathVariable Long[] ids) {
		return toAjax(sysCompetitionApplyService.deleteSysCompetitionApplyByIds(ids));
	}
}
