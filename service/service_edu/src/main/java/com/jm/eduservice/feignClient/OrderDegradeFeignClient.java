package com.jm.eduservice.feignClient;

public class OrderDegradeFeignClient implements OrderClient{
    @Override
    public boolean isBuyCourse(String courseId, String memberId) {
        return false;
    }
}
