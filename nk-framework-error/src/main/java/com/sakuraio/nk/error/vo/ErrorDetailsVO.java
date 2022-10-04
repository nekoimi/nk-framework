package com.sakuraio.nk.error.vo;

import com.sakuraio.nk.core.contract.ErrorDetails;
import lombok.*;

/**
 * <p>ErrorDetailsVO</p>
 *
 * @author nekoimi 2022/10/04
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ErrorDetailsVO implements ErrorDetails {
    private Integer code;
    private String message;
    private String trace;

    @Override
    public Integer code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public String trace() {
        return trace;
    }
}
