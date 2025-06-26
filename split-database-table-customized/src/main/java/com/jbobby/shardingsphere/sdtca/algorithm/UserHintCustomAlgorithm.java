package com.jbobby.shardingsphere.sdtca.algorithm;

import com.jbobby.shardingsphere.sdtca.entity.User;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 自定义强制路由分片算法
 *
 * UserServiceImpl#insertSingleWithHint方法中设置hint
 * @see com.jbobby.shardingsphere.sdtca.service.impl.UserServiceImpl#insertSingleWithHint(long, long, User)
 */
public class UserHintCustomAlgorithm implements HintShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<Long> shardingValue) {
        /**
         * 获取到设置的分表或者分库的分片值
         * 指定分表时的分片值  hintManager.addTableShardingValue("t_user",2L);
         * 指定分库时的分片值  hintManager.addDatabaseShardingValue("t_user", 100L);
         */
        Collection<Long> values = shardingValue.getValues();
        Collection<String> result = new ArrayList<>();
        // 从所有分片表中得到合适的分片表
        for (String each : availableTargetNames) {
            for (Long value : values) {
                Long mod = value % availableTargetNames.size();
                if (each.endsWith(String.valueOf(mod))) {
                    result.add(each);
                }
            }
        }
        return result;
    }
}
