package com.jbobby.shardingsphere.sdtca.algorithm;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Range;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 自定义复合分片算法
 */
public class UserComplexCustomAlgorithm implements ComplexKeysShardingAlgorithm<Long> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserComplexCustomAlgorithm.class);

    /**
     * 复合分片算法进入，支持>，>=, <=，<，=，IN 和 BETWEEN AND 等操作符
     *
     * @param availableTargetNames available data sources or table names
     * @param shardingValue sharding value
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Long> shardingValue) {
        LOGGER.info("进入复合分片：complex availableTargetNames:{}", JSON.toJSONString(availableTargetNames));

        // 多分片健和其对应的分片健范围值
        Map<String, Range<Long>> columnNameAndRangeValuesMap = shardingValue.getColumnNameAndRangeValuesMap();
        LOGGER.info("进入复合分片：columnNameAndRangeValuesMap:{}", JSON.toJSONString(columnNameAndRangeValuesMap));

        columnNameAndRangeValuesMap.forEach((columnName, range) -> {
            // 分片健
            LOGGER.info("进入复合分片：columnName:{}", columnName);
            // 分片健范围值
            LOGGER.info("进入复合分片：range:{}", JSON.toJSONString(range));
        });

        // 多分片健和其对应的分片健值
        Map<String, Collection<Long>> columnNameAndShardingValuesMap = shardingValue.getColumnNameAndShardingValuesMap();
        LOGGER.info("进入复合分片：columnNameAndShardingValuesMap:{}", JSON.toJSONString(columnNameAndShardingValuesMap));
        columnNameAndShardingValuesMap.forEach((columnName, shardingValues) -> {
            // 分片健 及 键值
            LOGGER.info("进入复合分片：columnName:{}, shardingValues:{}", columnName, JSON.toJSONString(shardingValues));
        });

        // 测试用
        return List.of("t_user_0");
    }
}
