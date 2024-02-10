package com.octopus.mygamesbackend.service.impl;

import com.octopus.mygamesbackend.mapper.ChatRoomRecordMapper;
import com.octopus.mygamesbackend.pojo.dao.ChatRoomRecord;
import com.octopus.mygamesbackend.pojo.vo.ChatRoomRecordVo;
import com.octopus.mygamesbackend.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    @Autowired
    private ChatRoomRecordMapper mapper;
    @Autowired
    private SimpleDateFormat simpleDateFormat;

    @Override
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
