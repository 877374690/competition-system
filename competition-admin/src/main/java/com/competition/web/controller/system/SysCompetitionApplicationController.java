package com.competition.web.controller.system;

import com.competition.common.annotation.Log;
import com.competition.common.core.controller.BaseController;
import com.competition.common.core.domain.AjaxResult;
import com.competition.common.core.page.TableDataInfo;
import com.competition.common.enums.BusinessType;
import com.competition.common.utils.poi.ExcelUtil;
import com.competition.system.domain.SysCompetitionApplication;
import com.competition.system.service.ISysCompetitionApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 比赛申请Controller
 *
 * @author competition
 * @date 2022-03-15
 */
@RestController
@RequestMapping("/competition/apply")
public class SysCompetitionApplicationController extends BaseController {
	@Autowired
	private ISysCompetitionApplicationService sysCompetitionApplicationService;


	@PreAuthorize("@ss.hasAnyPermi('apply:works:list,apply:answer:list,apply:progress:list')")
	@GetMapping("/listCountApply")
	public TableDataInfo listCountApply(SysCompetitionApplication sysCompetitionApplication) {
		startPage();
		List<SysCompetitionApplication> list = sysCompetitionApplicationService.listCountApply(sysCompetitionApplication);
		return getDataTable(list);
	}
	/**
	 * 查询比赛申请列表
	 */
	@PreAuthorize("@ss.hasAnyPermi('apply:works:list,apply:answer:list,apply:progress:list')")
	@GetMapping("/list")
	public TableDataInfo list(SysCompetitionApplication sysCompetitionApplication) {
		startPage();
		List<SysCompetitionApplication> list = sysCompetitionApplicationService.selectSysCompetitionApplicationList(sysCompetitionApplication);
		return getDataTable(list);
	}
	@PreAuthorize("@ss.hasAnyPermi('apply:works:list,apply:answer:list,apply:progress:list')")
	@GetMapping("/listApplyResult")
	public TableDataInfo listApplyResult(SysCompetitionApplication sysCompetitionApplication) {
		startPage();
		List<SysCompetitionApplication> list = sysCompetitionApplicationService.listApplyResult(sysCompetitionApplication);
		return getDataTable(list);
	}

	@PreAuthorize("@ss.hasAnyPermi('apply:works:list,apply:answer:list,apply:progress:list')")
	@GetMapping("/listCompetition/{type}")
	public List listCompetition(@PathVariable("type") String type) {
		return sysCompetitionApplicationService.selectListCompetition(type);
	}

	/**
	 * 导出比赛申请列表
	 */
	@PreAuthorize("@ss.hasAnyPermi('apply:works:list,apply:answer:list,apply:progress:list')")
	@Log(title = "比赛申请", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	public void export(HttpServletResponse response, SysCompetitionApplication sysCompetitionApplication) {
		List<SysCompetitionApplication> list = sysCompetitionApplicationService.selectSysCompetitionApplicationList(sysCompetitionApplication);
		ExcelUtil<SysCompetitionApplication> util = new ExcelUtil<SysCompetitionApplication>(SysCompetitionApplication.class);
		util.exportExcel(response, list, "比赛申请数据");
	}

	/**
	 * 获取比赛申请详细信息
	 */
	@PreAuthorize("@ss.hasAnyPermi('apply:works:list,apply:answer:list,apply:progress:list')")
	@GetMapping(value = "/{id}")
	public AjaxResult getInfo(@PathVariable("id") Long id) {
		return AjaxResult.success(sysCompetitionApplicationService.selectSysCompetitionApplicationById(id));
	}

	/**
	 * 新增比赛申请
	 */
	@PreAuthorize("@ss.hasAnyPermi('apply:works:list,apply:answer:list,apply:progress:list')")
	@Log(title = "比赛申请", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@RequestBody SysCompetitionApplication sysCompetitionApplication) {
		return toAjax(sysCompetitionApplicationService.insertSysCompetitionApplication(sysCompetitionApplication));
	}

	/**
	 * 修改比赛申请
	 */
	@PreAuthorize("@ss.hasAnyPermi('apply:works:list,apply:answer:list,apply:progress:list')")
	@Log(title = "比赛申请", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@RequestBody SysCompetitionApplication sysCompetitionApplication) {
		return toAjax(sysCompetitionApplicationService.updateSysCompetitionApplication(sysCompetitionApplication));
	}

	/**
	 * 删除比赛申请
	 */
	@PreAuthorize("@ss.hasAnyPermi('apply:works:list,apply:answer:list,apply:progress:list')")
	@Log(title = "比赛申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
	public AjaxResult remove(@PathVariable Long[] ids) {
		return toAjax(sysCompetitionApplicationService.deleteSysCompetitionApplicationByIds(ids));
	}
}
