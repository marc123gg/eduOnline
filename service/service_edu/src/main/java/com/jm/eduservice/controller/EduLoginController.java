package com.jm.eduservice.controller;

import com.jm.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @author marc
 */
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {
    @PostMapping("login")
    public R login(){
        return R.ok().data("token", "admin");
    }

    /**
     * info
     * @return R.ok
     */
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name", "admin").data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
