package com.wx.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMenuService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpGetSelfMenuInfoResult;
import me.chanjar.weixin.mp.bean.menu.WxMpSelfMenuInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Fun Description //TODO jsapi 接口演示
 * @Date 2020/5/28 17:56 28
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@AllArgsConstructor
@RestController
@RequestMapping("/wx/jsapi/{appid}")
@Slf4j
public class WxJsapiController {
    private final WxMpService wxService;

    @GetMapping("/getJsapiTicket")
    public String getJsapiTicket(@PathVariable String appid) throws WxErrorException {
        // 进行相应的公众号切换.
        // 参数:
        //mpId - 公众号标识
        //返回:
        //切换成功，则返回当前对象，方便链式调用，否则抛出异常
        //
        //创建调用jsapi时所需要的签名.
//         详情请见：http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141115&token=&lang=zh_CN
        final WxJsapiSignature jsapiSignature = this.wxService.switchoverTo(appid).createJsapiSignature("111");
        log.info("jsapiSignature is {}", jsapiSignature);
        log.info(this.wxService.getAccessToken(false));
        // 偿试着调jsapi看看
        String shortUrl =  wxService.shortUrl("http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141115&token=&lang=zh_CN");
        log.info("shorUrl is {}", shortUrl);
        WxMpMenuService wxMpMenuService = wxService.getMenuService();
        WxMpGetSelfMenuInfoResult selfMenuInfo = wxMpMenuService.getSelfMenuInfo();
        WxMpSelfMenuInfo selfMenuInfo1 = selfMenuInfo.getSelfMenuInfo();
        List<WxMpSelfMenuInfo.WxMpSelfMenuButton> buttons = selfMenuInfo1.getButtons();
        buttons.stream().forEach(System.out::println);
        return this.wxService.getJsapiTicket(false);
    }
}
