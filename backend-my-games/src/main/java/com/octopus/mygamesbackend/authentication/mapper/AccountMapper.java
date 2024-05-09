package com.octopus.mygamesbackend.authentication.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.octopus.mygamesbackend.authentication.entity.AccountEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author dekopon
 * @since 2023/6/18 01:58
 */
@Mapper
public interface AccountMapper extends BaseMapper<AccountEntity> {

}
