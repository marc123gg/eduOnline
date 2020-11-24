package com.jm.educms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jm.commonutils.R;
import com.jm.educms.entity.CrmBanner;
import com.jm.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author marc
 * @since 2020-11-23
 */
@RestController
@RequestMapping("/educms/banneradmin")
@CrossOrigin

/**
 * 后天banner接口
 */
public class BannerAdminController {

    @Autowired
    private CrmBannerService bannerService;

    /**
     * 分页查询
     */
    @GetMapping("pageBanner/{currentPage}/{limit}")
    public R pageBanner(@PathVariable long currentPage, @PathVariable long limit){
        Page<CrmBanner> page = new Page<>(currentPage, limit);
        IPage data = bannerService.page(page, null);
        return R.ok().data("total", data.getTotal()).data("rows", data.getRecords());
    }

    /**
     * 根据id查询
     */
    @GetMapping("getBannerById/{id}")
    public R getBannerById(@PathVariable String id){
        return R.ok().data("item", bannerService.getById(id));
    }

    /**
     * 根据id删除
     */
    @DeleteMapping("deleteBannerById/{id}")
    public R deleteBannerById(@PathVariable String id){
        return R.ok();
    }

    /**
     * 修改banner
     * @param crmBanner
     * @return
     */
    @PostMapping("updateBanner")
    public R updateBanner(@PathVariable CrmBanner crmBanner){
        bannerService.updateById(crmBanner);
        return R.ok();
    }

    @PostMapping("addBanner")
    public R addBanner(@PathVariable CrmBanner crmBanner){
        bannerService.save(crmBanner);
        return R.ok();
    }

}

