package cn.peregrine.trigger.api;

import cn.peregrine.trigger.api.response.Response;

public interface IDCCService {
    Response<Boolean> updateConfig(String key, String value);
}
