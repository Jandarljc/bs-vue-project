package com.hzvtc.lujc.moudules.bsUser.service.impl;

import com.hzvtc.lujc.moudules.bsUser.controller.CoinDOController;
import com.hzvtc.lujc.moudules.bsUser.mapper.UserDOMapper;
import com.hzvtc.lujc.moudules.bsUser.pojo.UserDO;
import com.hzvtc.lujc.moudules.bsUser.pojo.UserDOExample;
import com.hzvtc.lujc.moudules.bsUser.pojo.UserDOExample.Criteria;
import com.hzvtc.lujc.moudules.bsUser.service.UserDOService;
import com.hzvtc.lujc.utils.CookieUtils;
import com.hzvtc.lujc.utils.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author lujc
 * @ClassName: UserDOServiceImpl
 * @Description: 用户信息
 * @date: 2019/12/13 14:34
 */
@Service
public class UserDOServiceImpl implements UserDOService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDOServiceImpl.class);
    private static final String REDIS_USER_SESSION_KEY = "REDIS_BS_USER_SESSION_KEY";
    private static final String BS_TOKEN = "BS_TOKEN";
    private static final String SPLIT = ":";
    private static final String ERROR_TIP = "用户名或密码错误";
    private static final String VIP = "会员";
    private static final String AVATAR = "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png";

    @Autowired
    private UserDOMapper mapper;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public void delete(String id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public ResponseEntity<String> login(UserDO userDO,
                                                    HttpServletRequest request, HttpServletResponse response) {
        UserDOExample example = new UserDOExample();
        Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(userDO.getUsername());
        List<UserDO> list = mapper.selectByExample(example);
        String str = "";
        UserDO user;
        // 如果没有此用户名
        if (null == list || list.size() == 0) {
            userDO.setId(dateToStamp(new Date()));
            userDO.setPassword(DigestUtils.md5DigestAsHex(userDO.getPassword().getBytes()));
            userDO.setRoles(VIP);
            userDO.setAvatar(AVATAR);
            if(mapper.insert(userDO)==1){
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("调用用户注册接口");
                }
                list.add(userDO);
            }else {
                str="注册失败!";
                return ResponseEntity.badRequest().body(str);
            }
            user =user = list.get(0);
            // 比对密码
            if (!userDO.getPassword().equals(user.getPassword())) {
                return ResponseEntity.badRequest().body(ERROR_TIP);
            }
        }else {
            user = list.get(0);
            // 比对密码
            if (!DigestUtils.md5DigestAsHex(userDO.getPassword().getBytes()).equals(user.getPassword())) {
                return ResponseEntity.badRequest().body(ERROR_TIP);
            }
        }

        // 生成token
        String token = UUID.randomUUID().toString();
        // 保存用户之前，把用户对象中的密码清空。
        user.setPassword(null);
        // 把用户信息写入redis
        redisTemplate.opsForValue().set(REDIS_USER_SESSION_KEY + SPLIT + token, JsonUtils.objectToJson(user));
        // 设置session的过期时间
        redisTemplate.expire(REDIS_USER_SESSION_KEY + SPLIT + token, 10, TimeUnit.MINUTES);
        // 返回token
        CookieUtils.setCookie(request, response, BS_TOKEN, token);
        return ResponseEntity.ok(token);
    }

    @Override
    public ResponseEntity<UserDO> getUserByToken(String token) {
        // 根据token从redis中查询用户信息
        String json = redisTemplate.opsForValue().get(REDIS_USER_SESSION_KEY + SPLIT + token);
        // 判断是否为空
        if (StringUtils.isBlank(json)) {
            //此session已经过期，请重新登录
            return ResponseEntity.ok().build();
        }
        // 更新过期时间
        redisTemplate.expire(REDIS_USER_SESSION_KEY + SPLIT + token, 30, TimeUnit.MINUTES);
        // 返回用户信息
        return ResponseEntity.ok(JsonUtils.jsonToPojo(json, UserDO.class));
    }

    @Override
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response, String token) {
        //redis清除缓存
        redisTemplate.delete(REDIS_USER_SESSION_KEY + SPLIT + token);
        CookieUtils.deleteCookie(request, response, BS_TOKEN);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<List<String>> getUserList() {
        UserDOExample example = new UserDOExample();
        return ResponseEntity.ok(mapper.selectByExample(example).stream().map(UserDO::getUsername).collect(Collectors.toList()));
    }

    @Override
    public String updateInfo(String token,String avatar) {
        ResponseEntity<UserDO> userDOResponseEntity = this.getUserByToken(token);
        UserDO userDO = userDOResponseEntity.getBody();
        userDO.setAvatar(avatar);
        String str = "";
        if(mapper.updateByPrimaryKey(userDO)==1){
            str = "头像更新成功！";
        }else {
            str = "头像更新失败";
        }
        return str;
    }

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String res = "",s = sdf.format(date);
        if (!"".equals(s)) {
            try {
                res = String.valueOf(sdf.parse(s).getTime()/1000);
            } catch (Exception e) {
                System.out.println("传入了null值");
            }
        }else {
            long time = System.currentTimeMillis();
            res = String.valueOf(time/1000);
        }
        return res;
    }
}
