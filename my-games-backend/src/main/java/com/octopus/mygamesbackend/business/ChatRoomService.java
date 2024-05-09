package com.octopus.mygamesbackend.business;

import com.octopus.mygamesbackend.business.pojo.dao.ChatRoomRecord;
import com.octopus.mygamesbackend.business.pojo.vo.ChatRoomRecordVo;
import com.octopus.mygamesbackend.business.mapper.ChatRoomRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatRoomService {
    @Autowired
    private ChatRoomRecordMapper mapper;
    @Autowired
    private SimpleDateFormat simpleDateFormat;

    public List<ChatRoomRecordVo> getHistoryRecords() {
        List<ChatRoomRecord> historyRecords = mapper.getHistoryRecords();
        return historyRecords.stream().map(
                        item ->
                                new ChatRoomRecordVo(
                                        item.getContent(),
                                        item.getUsername(),
                                        simpleDateFormat.format(item.getCreateTime())))
                .collect(Collectors.toList());
    }
}
