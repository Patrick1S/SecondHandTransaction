package com.patrick.springboot.service.impl;

import com.patrick.springboot.entity.Evaluate;
import com.patrick.springboot.mapper.EvaluateMapper;
import com.patrick.springboot.service.IEvaluateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Patrick
 * @since 2022-06-17
 */
@Service
public class EvaluateServiceImpl extends ServiceImpl<EvaluateMapper, Evaluate> implements IEvaluateService {

}
