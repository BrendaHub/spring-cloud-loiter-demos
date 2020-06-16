package com.wx.builder;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * @Fun Description //TODO
 * @Date 2020/5/28 17:04 28
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@Slf4j
public abstract class AbstractBuilder {
    public abstract WxMpXmlOutMessage build(String content,
                                            WxMpXmlMessage wxMessage,
                                            WxMpService service);
}
