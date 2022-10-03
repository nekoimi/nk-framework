package com.sakuraio.nk.core.contract;

/**
 * <p>ErrorDetails</p>
 *
 * @author nekoimi 2022/10/03
 */
public interface ErrorDetails {
    Integer code();
    String message();
    String trace();
}
