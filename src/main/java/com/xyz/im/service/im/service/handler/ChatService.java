package com.xyz.im.service.im.service.handler;

import com.xyz.im.base.log.ImLogUtils;
import com.xyz.im.domain.GroupMember;
import com.xyz.im.service.group.service.GroupService;
import com.xyz.im.service.im.dto.MsgReqBody;
import com.xyz.im.service.im.dto.MsgRespBody;
import com.xyz.im.service.im.enums.MsgType;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 聊天
 *
 * @author xyz
 * @date 2020-08-22
 */
@Service
public class ChatService {

    @Resource
    private GroupService groupService;

    public static Map<Long, ChannelHandlerContext> onlineUserMap = new ConcurrentHashMap<>();
    public static Map<ChannelHandlerContext, Long> channelMap = new ConcurrentHashMap<>();

    /**
     * im上线
     *
     * @param msgReqBody 消息体
     * @param ctx        用户本次连接上下文
     */
    public void register(MsgReqBody msgReqBody, ChannelHandlerContext ctx) {
        long uid = NumberUtils.toLong(msgReqBody.getFrom());
        onlineUserMap.put(uid, ctx);
        channelMap.put(ctx, uid);
        MsgRespBody msgRespBody = MsgRespBody.builder()
                .msgType(MsgType.REGISTER)
                .build();
        MsgSendUtils.sendMessage(ctx, msgRespBody);
        ImLogUtils.info("uid={} 登记到在线用户表，当前在线人数为：{}"
                , msgReqBody.getFrom(), onlineUserMap.size());
    }

    /**
     * im 下线
     *
     * @param ctx 用户本次连接上下文
     */
    public void remove(ChannelHandlerContext ctx) {
        Long uid = channelMap.get(ctx);
        if (Objects.isNull(uid)) {
            ImLogUtils.warn("未找到连接 下线操作失败 {}", ctx);
            return;
        }

        channelMap.remove(ctx);
        onlineUserMap.remove(uid);
    }

    /**
     * 发送群消息
     *
     * @param msgReqBody 消息体
     * @param ctx        用户本次连接上下文
     */
    public void groupSend(MsgReqBody msgReqBody, ChannelHandlerContext ctx) {
        long groupId = NumberUtils.toLong(msgReqBody.getTo());
        List<GroupMember> groupMembers = groupService.queryGroupMembers(groupId);
        if (CollectionUtils.isEmpty(groupMembers)) {
            MsgSendUtils.sendErrorMessage(ctx, "群没有成员 异常");
            return;
        }

        MsgRespBody msgRepsBody = MsgRespBody.builder()
                .msgType(MsgType.SEND_GROUP)
                .msg(msgReqBody.getMsg())
                .from(msgReqBody.getFrom())
                .to(msgReqBody.getTo())
                .build();
        long to = NumberUtils.toLong(msgReqBody.getTo());
        groupMembers.forEach(item -> {
            ChannelHandlerContext toCtx = onlineUserMap.get(item.getUid());
            if (toCtx != null && item.getUid() != to) {
                MsgSendUtils.sendMessage(toCtx, msgRepsBody);
            }
        });
    }

}
