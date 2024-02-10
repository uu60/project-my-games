package com.octopus.mygamesbackend.controller.game;

import com.octopus.mygamesbackend.pojo.vo.ChatRoomRecordVo;
import com.octopus.mygamesbackend.service.ChatRoomService;
import com.octopus.mygamesbackend.utils.http.R;
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
    public R getHistory() {
        List<ChatRoomRecordVo> historyRecords = service.getHistoryRecords();
        return R.ok().put("historyRecords", historyRecords);
    }
}
