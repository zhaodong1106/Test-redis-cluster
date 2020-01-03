--
-- Created by IntelliJ IDEA.
-- User: use
-- Date: 2019/12/9
-- Time: 10:51
-- To change this template use File | Settings | File Templates.
--
local count=tonumber(ARGV[1])
local key='coupon_to_count:'..KEYS[1]
local keyDuration='coupon_to_duration:'..KEYS[1]
local flag=redis.call('exists',key)
local now=redis.call('TIME')[1]
local duration=redis.call('get',keyDuration);
if flag then
    local startTime=cjson.decode(duration)['startTime'];
    local endTime=cjson.decode(duration)['endTime'];
    if startTime>tonumber(now) then
        -- not start
        return "-4";
    end
    if endTime<tonumber(now) then
        -- already end
        return "-5";
    end
    local num=redis.call("llen",key);
    if tonumber(num)>=count then
        local result=redis.call("rpop",key);
        return result;
    else
        --        not enough
        return "-3";
    end
else
    return "-2";
end


