package com.patrick.springboot.service.impl;

import com.patrick.springboot.entity.ConsumRecords;
import com.patrick.springboot.mapper.ConsumRecordsMapper;
import com.patrick.springboot.service.IConsumRecordsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Patrick
 * @since 2022-06-18
 */
@Service
public class ConsumRecordsServiceImpl extends ServiceImpl<ConsumRecordsMapper, ConsumRecords> implements IConsumRecordsService {

}
