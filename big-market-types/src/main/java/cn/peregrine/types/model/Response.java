package cn.peregrine.types.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @projectName: big-market
 * @package: cn.peregrine.types.model
 * @className: Response
 * @author: Peregrine Calder
 * @description: TODO
 * @date: 2024/4/18 17:50
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> implements Serializable {
    private String code;
    private String info;
    private T data;

}
