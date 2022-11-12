package com.yytech.mpcdsepc.utils;

import com.yytech.mpcdsepc.entity.Account;
import com.yytech.mpcdsepc.entity.Person;
import com.yytech.mpcdsepc.entity.Tube;

import java.util.HashMap;
import java.util.Map;

public class LockUtil {
    private static Map<String,String> data = new HashMap<>();

    /**
     * 传入混管和混管内人员还有锁定账户来锁定编辑数据
     * @param tube
     * @param person
     * @param account
     * @return
     */
    public static boolean lockData(Tube tube, Person person, Account account){
        return true;
    }
}
