package com.jbobby.shardingsphere.sdtca.algorithm;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Range;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * 自定义标准分片算法
 */
public class UserStandardCustomAlgorithm implements StandardShardingAlgorithm<Long> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserStandardCustomAlgorithm.class);

    /**
     * 进入 sql中有 = 和 in 等操作符会执行精准分片
     *
     * 另外插入语句会进入精准分片算法
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> preciseShardingValue) {
        /**
         * 分库策略使用时：availableTargetNames 参数数据为分片库的集合 ["ds_0","ds_1"]
         * 分表策略使用时：availableTargetNames 参数数据为分片库的集合 ["t_user_0","t_user_1","t_user_2"]
         */
        LOGGER.info("进入精准分片 precise availableTargetNames:{}", JSON.toJSONString(availableTargetNames));

        /**
         * 分库策略使用时： shardingValue 参数数据：{"columnName":"user_id","dataNodeInfo":{"paddingChar":"0","prefix":"ds_","suffixMinLength":1},"logicTableName":"t_user","value":11}
         * 分表策略使用时： shardingValue 参数数据：{"columnName":"addr_id","dataNodeInfo":{"paddingChar":"0","prefix":"t_user_","suffixMinLength":1},"logicTableName":"t_user","value":999}
         */
        LOGGER.info("进入精准分片 preciseShardingValue:{}", JSON.toJSONString(preciseShardingValue));

        int targetCount = availableTargetNames.size();
        // 真实表的前缀
        String targetPrefix = preciseShardingValue.getDataNodeInfo().getPrefix();
        // 分片健的值
        long realValue = preciseShardingValue.getValue();
        // 对分片健取模后确定位置
        long index = realValue % targetCount;
        return targetPrefix + index;
    }

    /**
     * 进入 sql中有 between 和  < > 等操作符会执行范围分片
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> rangeShardingValue) {
        /**
         * 分库策略使用时：availableTargetNames 参数数据为分片库的集合 ["ds_0","ds_1"]
         * 分表策略使用时：availableTargetNames 参数数据为分片库的集合 ["t_user_0","t_user_1","t_user_2"]
         */
        LOGGER.info("进入范围分片：range availableTargetNames:{}", JSON.toJSONString(availableTargetNames));

        /**
         * 分库策略使用时 shardingValue 参数数据：{"columnName":"user_id","dataNodeInfo":{"paddingChar":"0","prefix":"ds_","suffixMinLength":1},"logicTableName":"t_user","valueRange":{"empty":false}}
         * 分表策略使用时 shardingValue 参数数据：{"columnName":"addr_id","dataNodeInfo":{"paddingChar":"0","prefix":"t_user_","suffixMinLength":1},"logicTableName":"t_user","valueRange":{"empty":false}}
         */
        LOGGER.info("进入范围分片：rangeShardingValue:{}", JSON.toJSONString(rangeShardingValue));

        // 分片健值的下边界
        Range<Long> valueRange = rangeShardingValue.getValueRange();
        Long lower = valueRange.lowerEndpoint();
        // 分片健值的上边界
        Long upper = valueRange.upperEndpoint();
        // 真实数据库或表的前缀
        String targetPrefix = rangeShardingValue.getDataNodeInfo().getPrefix();
        if (lower != null && upper != null) {
            // 分片健的值, 此处只是演示简单的逻辑
            long mockValue = upper - lower;
            // 对分片健取模后确定位置
            long mod = mockValue % availableTargetNames.size();
            return Arrays.asList(targetPrefix + mod);
        }
        return Collections.singletonList("t_user_0");  // 或者Collections.singletonList("ds_0");
    }
}
