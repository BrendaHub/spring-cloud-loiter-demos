package com.wx.handler;

import com.wx.builder.TextBuilder;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Fun Description //TODO 地址位置消息实现类
 * @Date 2020/5/28 17:16 28
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@Slf4j
@Component
public class LocationHandler extends AbstractHandler {
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        if (wxMessage.getMsgType().equals(WxConsts.XmlMsgType.LOCATION)) {
            //TODO 接收处理用户发送的地理位置消息
            try {
                Map<String, Object> allFieldsMap = wxMessage.getAllFieldsMap();
                allFieldsMap.forEach((k,v) -> {
                    log.info("key is {}, value is {}", k, (String)v);
                });
                String content = "感谢反馈，您的的地理位置已收到！";
                return new TextBuilder().build(content, wxMessage, null);
            } catch (Exception e) {
                log.error("位置消息接收处理失败", e);
                return null;
            }
        }

        //上报地理位置事件
        log.info("上报地理位置，纬度 : {}，经度 : {}，精度 : {}",
                wxMessage.getLatitude(), wxMessage.getLongitude(), String.valueOf(wxMessage.getPrecision()));

        //TODO  可以将用户地理位置信息保存到本地数据库，以便以后使用

        return null;
    }
}
