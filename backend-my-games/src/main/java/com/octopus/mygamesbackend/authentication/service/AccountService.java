package com.octopus.mygamesbackend.authentication.service;

import com.octopus.mygamesbackend.authentication.entity.AccountEntity;
import com.octopus.mygamesbackend.authentication.vo.UsernamePasswordVO;

/**
 * @author dekopon
 * @since 2023/6/18 18:18
 */
public interface AccountService {
    AccountEntity getAccountEntity(String username);

    boolean addAccount(UsernamePasswordVO vo);
}
