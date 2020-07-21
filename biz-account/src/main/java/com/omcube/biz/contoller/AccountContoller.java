package com.omcube.biz.contoller;

import com.omcube.biz.entity.AccountEntity;
import com.omcube.biz.service.account.AccountService;
import com.omcube.comm.dto.Param;
import com.omcube.comm.dto.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account/")
public class AccountContoller {

    @Autowired
    AccountService accountService;


    @GetMapping("/get/{message}")
    public String test(@PathVariable("message") String message){



        return message;
    }

    @PostMapping("/save")
    public R save(@RequestBody Param<AccountEntity> param){
        R  result = new R();
        try {
            accountService.save(param.getData());
            result.success(param.getData());
        }catch (Exception e){
            e.printStackTrace();
            result.fail("保存失败");
        }
        return result;
    }


}
