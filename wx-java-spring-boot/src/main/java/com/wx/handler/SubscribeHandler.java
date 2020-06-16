package com.wx.handler;

import com.wx.builder.TextBuilder;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Fun Description //TODO
 * @Date 2020/5/28 17:35 28
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@Component
@Slf4j
public class SubscribeHandler extends  AbstractHandler {
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        log.info("新关注用户 OPENID: " + wxMessage.getFromUser());

        // 获取微信用户基本信息
//        try {
            WxMpUser userWxInfo = null;//weixinService.getUserService().userInfo(wxMessage.getFromUser(), null);
            if (userWxInfo != null) {
                // TODO 可以添加关注用户到本地数据库
            }
//        } catch (WxErrorException e) {
//            if (e.getError().getErrorCode() == 48001) {
//                log.info("该公众号没有获取用户信息权限！");
//            }
//        }


        WxMpXmlOutMessage responseResult = null;
        try {
            responseResult = this.handleSpecial(wxMessage);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        if (responseResult != null) {
            return responseResult;
        }

        try {
            return new TextBuilder().build("感谢关注", wxMessage, null);//, weixinService);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

        /**
         * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
         */
        private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
                throws Exception {
            //TODO
            return null;
        }

}
