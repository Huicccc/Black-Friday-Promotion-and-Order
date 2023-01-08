-- check key existing
if redis.call('exists', KEYS[1]) == 1
    -- get available_stock
    then local stock = tonumber(redis.call('get', KEYS[1]));
    if (stock > 0)
    -- update available_stock = available_stock - 1
        then redis.call('set', KEYS[1], stock - 1);
        return stock - 1;
    end
    -- available_stock <= 0
    return -1;
end
return -2;