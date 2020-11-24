package com.jm.eduservice.feignClient;

import com.jm.commonutils.R;

import java.util.List;

public class VodFileDegradeFeignClient implements VodClient {

    /**
     * 出错之后会执行
     */
    @Override
    public R deleteAlyVideo(String videoId) {
        return R.fail().msg("调用远程服务删除视频出错");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.fail().msg("调用远程服务批量删除视频出错");
    }
}
