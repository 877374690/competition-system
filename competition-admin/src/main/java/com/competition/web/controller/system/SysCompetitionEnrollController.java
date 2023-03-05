package com.competition.web.controller.system;

import com.competition.common.annotation.Log;
import com.competition.common.core.controller.BaseController;
import com.competition.common.core.domain.AjaxResult;
import com.competition.common.core.page.TableDataInfo;
import com.competition.common.enums.BusinessType;
import com.competition.common.utils.poi.ExcelUtil;
import com.competition.system.domain.SysCompetitionApply;
import com.competition.system.domain.SysCompetitionEnroll;
import com.competition.system.service.ISysCompetitionEnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 报名Controller
 *
 * @author competition
 * @date 2022-03-14
 */
@RestController
@RequestMapping("/enroll/personal")
public class SysCompetitionEnrollController extends BaseController {
	@Autowired
	private ISysCompetitionEnrollService sysCompetitionEnrollService;

	/**
	 * 查询报名列表
	 */
	@PreAuthorize("@ss.hasPermi('enroll:personal:list')")
	@GetMapping("/list")
	public TableDataInfo list(SysCompetitionEnroll sysCompetitionEnroll) {
		startPage();
		List<SysCompetitionEnroll> list = sysCompetitionEnrollService.selectSysCompetitionEnrollList(sysCompetitionEnroll);
		return getDataTable(list);
	}

	@PreAuthorize("@ss.hasPermi('enroll:personal:list')")
	@GetMapping("/listCompetition")
	public List<SysCompetitionApply> listCompetition(SysCompetitionApply sysCompetitionApply) {
		return sysCompetitionEnrollService.listCompetition(sysCompetitionApply);
	}

	/**
	 * 导出报名列表
	 */
	@PreAuthorize("@ss.hasPermi('enroll:personal:export')")
	@Log(title = "报名", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	public void export(HttpServletResponse response, SysCompetitionEnroll sysCompetitionEnroll) {
		List<SysCompetitionEnroll> list = sysCompetitionEnrollService.selectSysCompetitionEnrollList(sysCompetitionEnroll);
		ExcelUtil<SysCompetitionEnroll> util = new ExcelUtil<SysCompetitionEnroll>(SysCompetitionEnroll.class);
		util.exportExcel(response, list, "报名数据");
	}

	/**
	 * 获取报名详细信息
	 */
	@PreAuthorize("@ss.hasPermi('enroll:personal:query')")
	@GetMapping(value = "/{id}")
	public AjaxResult getInfo(@PathVariable("id") Long id) {
		return AjaxResult.success(sysCompetitionEnrollService.selectSysCompetitionEnrollById(id));
	}

	/**
	 * 新增报名
	 */
	@PreAuthorize("@ss.hasPermi('enroll:personal:add')")
	@Log(title = "报名", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@RequestBody SysCompetitionEnroll sysCompetitionEnroll) {
		return toAjax(sysCompetitionEnrollService.insertSysCompetitionEnroll(sysCompetitionEnroll));
	}

	/**
	 * 修改报名
	 */
	@PreAuthorize("@ss.hasPermi('enroll:personal:edit')")
	@Log(title = "报名", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@RequestBody SysCompetitionEnroll sysCompetitionEnroll) {
		return toAjax(sysCompetitionEnrollService.updateSysCompetitionEnroll(sysCompetitionEnroll));
	}

	/**
	 * 删除报名
	 */
	@PreAuthorize("@ss.hasPermi('enroll:personal:remove')")
	@Log(title = "报名", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
	public AjaxResult remove(@PathVariable Long[] ids) {
		return toAjax(sysCompetitionEnrollService.deleteSysCompetitionEnrollByIds(ids));
	}
}
