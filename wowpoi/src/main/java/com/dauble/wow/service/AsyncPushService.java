package com.dauble.wow.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service
public class AsyncPushService {
    private DeferredResult<String> deferredResult ;
    public DeferredResult<String> getDeferredResult() {
        deferredResult = new DeferredResult<String>();
        return  deferredResult;
    }

    @Scheduled(fixedDelay=1000)
    private void setDeferredResult(){
        if(deferredResult!=null){
            deferredResult.setResult(new String(System.currentTimeMillis()+""));
        }
    }
}
