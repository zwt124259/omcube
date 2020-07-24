package com.omcube.authserver.contoller;

import com.google.code.kaptcha.Producer;
import com.omcube.authserver.constant.Constant;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 验证码
 */
@Controller
@RequestMapping("/code")
public class CaptchaController {

    @Resource
    private Producer producer;

    @Resource
    private RedissonClient redissonClient;

    @RequestMapping("/img/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

    @ResponseBody
    @PostMapping(path = "/dataBase64/captcha")
    public Map<String,Object> captcha1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> resultMap = new HashMap<>();
        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存session
        String uuid = UUID.randomUUID().toString();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        // 对字节数组Base64编码
        RMapCache rMapCache =redissonClient.getMapCache(Constant.AUTH_CAPTCHA_CACHE_KEY);
        rMapCache.put(uuid,text);
        // 生成captcha的token
        resultMap.put("sessionKey",uuid);
        resultMap.put("img", Base64Utils.encodeToString(outputStream.toByteArray()));
        return resultMap;
    }


}
