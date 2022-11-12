package com.yytech.mpcdsepc.utils;

import com.yytech.mpcdsepc.entity.Account;
import com.yytech.mpcdsepc.entity.Person;
import com.yytech.mpcdsepc.entity.Tube;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LockUtil {
    private static Map<String, String> data = new HashMap<>();

    /**
     * 传入混管和混管内人员还有锁定账户来锁定编辑数据
     *
     * @param tube
     * @param person
     * @param account
     * @return
     */
    public static boolean lockDataUtil(Tube tube, Person person, Account account) {
        try {
            JSONObject jsonData = new JSONObject();
            jsonData.put("time", System.currentTimeMillis());
            jsonData.put("accountId", account.getId());
            data.put(String.valueOf(tube.id) + person.ID, jsonData.toString());
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 传入混管和混管内人员还有锁定账户来锁定编辑数据
     *
     * @param tubeId
     * @param personId
     * @param accountId
     * @return
     */
    public static boolean lockDataUtil(String tubeId, String personId, String accountId) {
        try {
            JSONObject jsonData = new JSONObject();
            jsonData.put("time", System.currentTimeMillis());
            jsonData.put("accountId", accountId);
            data.put(tubeId + personId, jsonData.toString());
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断是否锁定
     *
     * @param key
     * @return
     * @throws JSONException
     */
    public static boolean isLockDataUtil(String key) throws JSONException {
        return data.containsKey(key);
    }

    /**
     * 解除锁定
     *
     * @param key
     * @throws JSONException
     */
    public static void unLockDataUtil(String key) throws JSONException {
        data.remove(key);
    }
}
