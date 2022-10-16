package com.sakuraio.test.entity;

import com.sakuraio.nk.auth.api.contract.JwtSubject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>User</p>
 *
 * @author nekoimi 2022/10/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class User implements JwtSubject {
    private String id;
    private String username;

    @Override
    public String getIdentifier() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
