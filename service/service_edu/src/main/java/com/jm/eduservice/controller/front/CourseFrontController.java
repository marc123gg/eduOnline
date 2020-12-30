package com.jm.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jm.commonutils.JwtUtils;
import com.jm.commonutils.R;
import com.jm.commonutils.ordervo.CourseWebVoOrder;
import com.jm.eduservice.entity.EduCourse;
import com.jm.eduservice.entity.chapter.ChapterVo;
import com.jm.eduservice.entity.frontvo.CourseFrontVo;
import com.jm.eduservice.entity.frontvo.CourseWebVo;
import com.jm.eduservice.entity.vo.CourseInfoVo;
import com.jm.eduservice.feignClient.OrderClient;
import com.jm.eduservice.service.EduChapterService;
import com.jm.eduservice.service.EduCourseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/coursefront")
@CrossOrigin
public class CourseFrontController {
    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduChapterService chapterService;
    @Autowired
    private OrderClient orderClient;

    //条件查询带分页查询课程
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required = false) CourseFrontVo courseFrontVo){
        Page<EduCourse> pageCourse = new Page<>(page, limit);
        Map<String, Object> map = courseService.getCourseFrontList(pageCourse, courseFrontVo);
        return R.ok().data(map);
    }

    //查询课程详情
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request){
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);
        List<ChapterVo> chapterVoList = chapterService.getChapterVideoByCourseId(courseId);
        boolean isBuy = false;
        if(StringUtils.isEmpty(request.getHeader("token"))){
            return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList", chapterVoList).data("isBuy", isBuy);
        }
        isBuy = orderClient.isBuyCourse(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList", chapterVoList).data("isBuy", isBuy);
    }

    //根据课程id查询课程信息
    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id) {
        CourseWebVo courseInfo = courseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseInfo,courseWebVoOrder);
        return courseWebVoOrder;
    }
}
