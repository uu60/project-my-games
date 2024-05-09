package com.octopus.mygamesbackend.handler.http.game;

import com.octopus.mygamesbackend.business.pojo.vo.ChatRoomRecordVo;
import com.octopus.mygamesbackend.business.ChatRoomService;
import com.octopus.mygamesbackend.utils.http.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat_room")
public class ChatRoomController {

    @Autowired
    private ChatRoomService service;

    @GetMapping("/get_history")
    public Resp getHistory() {
        List<ChatRoomRecordVo> historyRecords = service.getHistoryRecords();
        return Resp.ok().put("historyRecords", historyRecords);
    }
}
