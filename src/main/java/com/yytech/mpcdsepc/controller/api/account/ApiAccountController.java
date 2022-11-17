package com.yytech.mpcdsepc.controller.api.account;
/**
 * @Author: Lettle
 * @Create: 2022-10-26 22:46
 * @Description: api for account
 **/

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.yytech.mpcdsepc.entity.Account;
import com.yytech.mpcdsepc.entity.Person;
import com.yytech.mpcdsepc.result.Result;
import com.yytech.mpcdsepc.service.impl.AccountServiceImpl;
import com.yytech.mpcdsepc.utils.StatusCodeUtil;
import com.yytech.mpcdsepc.utils.TencentMsgUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Lettle
 * Create by 2022/10/26 22:46
 */
@RestController
@CrossOrigin
@RequestMapping("/mpcdsepc/api/account")
public class ApiAccountController {

    @Resource
    private AccountServiceImpl accountService;

    @PostMapping("login")
    public Result login(@RequestBody Map<String, String> map) {
        LambdaQueryWrapper<Account> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Account::getUserName,map.get("username")).eq(Account::getPassWord,map.get("mm"));
        Account account = accountService.getOne(lambdaQueryWrapper);
        if(account == null){
            return Result.build(StatusCodeUtil.LoginError,"账号或密码错误");
        }
        return Result.build(StatusCodeUtil.LoginError,account);

    }


    @GetMapping("findById/{accountId}")
    public Result select(@PathVariable int  accountId) {
        LambdaQueryWrapper<Account> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Account::getManagerId,accountId);
        List<Account> list = accountService.list(lambdaQueryWrapper);
        if (list.size() == 0) {
            return Result.fail();
        }
        return Result.ok(list);
    }

    /**
     * /mpcdsepc/api/account/queryAll
     * 获取所有的用户信息
     * TODO: 这里需要识别合法token
     * @return
     */
//    @GetMapping("queryAll")
//    public Result queryAllUser() {
//        return Result.ok(accountService.list());
//    }

    /**
     * TODO: 这里需要识别合法token
     * @param account passed by browser.
     */
    @RequestMapping(value = "/updateAccount", method = RequestMethod.PUT)
    public Result update(@RequestBody Account account) {
        System.out.println(account);
        boolean flag = accountService.updateById(account);
        if (!flag) {
            return Result.build(StatusCodeUtil.UpdateError,"更新失败");
        }
        return Result.build(StatusCodeUtil.UpdateSuccess,"更新成功");
    }

    @PostMapping("addAccount")
    public Result addAccount(@RequestBody Account account){
        boolean save = accountService.save(account);
        if (!save) {
            return Result.build(StatusCodeUtil.RegError,"注册失败");
        }
        return Result.build(StatusCodeUtil.RegSuccess,"注册成功");
    }

    @GetMapping("queryAccountByRole/{role}")
    public Result addAccount(@PathVariable int role){
        LambdaQueryWrapper<Account> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Account::getRole,role);
        List<Account> list = accountService.list(lambdaQueryWrapper);
        if (list.size() == 0) {
            return Result.fail("无数据");
        }
        return Result.ok(list);
    }

    @PostMapping("sendMsg/{PhoneNum}/{name}")
    public Result sendMsg(@PathVariable String PhoneNum,
                          @PathVariable String name) throws TencentCloudSDKException {
        boolean flag = TencentMsgUtils.sendMsg(PhoneNum, name);
        if (!flag) {
            return Result.fail();
        }
        return Result.ok();
    }


    @DeleteMapping("delAccountById/{id}")
    public Result delAccountById(@PathVariable int id){
        boolean flag = accountService.removeById(id);
        if (!flag) {
            return Result.fail();
        }
        return Result.ok();
    }

    @PostMapping("logout")
    public Result logout() {
        return Result.ok("Logout Success");
    }
}
