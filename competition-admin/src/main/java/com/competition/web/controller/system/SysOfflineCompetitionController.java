package com.competition.web.controller.system;

import com.competition.common.annotation.Log;
import com.competition.common.core.controller.BaseController;
import com.competition.common.core.domain.AjaxResult;
import com.competition.common.core.page.TableDataInfo;
import com.competition.common.enums.BusinessType;
import com.competition.common.utils.poi.ExcelUtil;
import com.competition.system.domain.SysOfflineCompetition;
import com.competition.system.service.ISysOfflineCompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 线下赛事Controller
 *
 * @author competition
 * @date 2022-03-12
 */
@RestController
@RequestMapping("/manage/offline")
public class SysOfflineCompetitionController extends BaseController {
	@Autowired
	private ISysOfflineCompetitionService sysOfflineCompetitionService;

	/**
	 * 查询线下赛事列表
	 */
	@PreAuthorize("@ss.hasPermi('manage:offline:list')")
	@GetMapping("/list")
	public TableDataInfo list(SysOfflineCompetition sysOfflineCompetition) {
		startPage();
		List<SysOfflineCompetition> list = sysOfflineCompetitionService.selectSysOfflineCompetitionList(sysOfflineCompetition);
		return getDataTable(list);
	}

	/**
	 * 导出线下赛事列表
	 */
	@PreAuthorize("@ss.hasPermi('manage:offline:export')")
	@Log(title = "线下赛事", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	public void export(HttpServletResponse response, SysOfflineCompetition sysOfflineCompetition) {
		List<SysOfflineCompetition> list = sysOfflineCompetitionService.selectSysOfflineCompetitionList(sysOfflineCompetition);
		ExcelUtil<SysOfflineCompetition> util = new ExcelUtil<SysOfflineCompetition>(SysOfflineCompetition.class);
		util.exportExcel(response, list, "线下赛事数据");
	}

	/**
	 * 获取线下赛事详细信息
	 */
	@PreAuthorize("@ss.hasPermi('manage:offline:query')")
	@GetMapping(value = "/{id}")
	public AjaxResult getInfo(@PathVariable("id") Long id) {
		return AjaxResult.success(sysOfflineCompetitionService.selectSysOfflineCompetitionById(id));
	}

	/**
	 * 新增线下赛事
	 */
	@PreAuthorize("@ss.hasPermi('manage:offline:add')")
	@Log(title = "线下赛事", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@RequestBody SysOfflineCompetition sysOfflineCompetition) {
		sysOfflineCompetition.setCheckStatus("0");
		sysOfflineCompetition.setCompetitionType("3");
		return toAjax(sysOfflineCompetitionService.insertSysOfflineCompetition(sysOfflineCompetition));
	}

	/**
	 * 修改线下赛事
	 */
	@PreAuthorize("@ss.hasPermi('manage:offline:edit')")
	@Log(title = "线下赛事", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@RequestBody SysOfflineCompetition sysOfflineCompetition) {
		return toAjax(sysOfflineCompetitionService.updateSysOfflineCompetition(sysOfflineCompetition));
	}

	/**
	 * 删除线下赛事
	 */
	@PreAuthorize("@ss.hasPermi('manage:offline:remove')")
	@Log(title = "线下赛事", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
	public AjaxResult remove(@PathVariable Long[] ids) {
		return toAjax(sysOfflineCompetitionService.deleteSysOfflineCompetitionByIds(ids));
	}
}
