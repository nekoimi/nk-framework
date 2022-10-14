package com.sakuraio.nk.auth.security.factory;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * <p>DefaultJwtSubjectFactory</p>
 * <p>
 * 适用于jwt的SubjectFactory，不需要session
 *
 * @author nekoimi 2022/10/13
 */
public class DefaultJwtSubjectFactory extends DefaultWebSubjectFactory {

    @Override
    public Subject createSubject(SubjectContext context) {
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }
}
