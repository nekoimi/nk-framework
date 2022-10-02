package com.sakuraio.nk.util.collection;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>CollectionSafeUtils</p>
 *
 * @author nekoimi 2022/10/02
 */
public class CollectionSafeUtils {

    private CollectionSafeUtils() {
    }

    /**
     * <p>wrapper collection</p>
     *
     * @param collection
     * @param <E>
     * @return
     */
    public static <E> Collection<E> wrapperCollection(Collection<E> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return Collections.emptyList();
        }
        return collection.stream().filter(Objects::nonNull).collect(Collectors.toSet());
    }

    /**
     * <p>获取List</p>
     *
     * @param collection
     * @return
     */
    public static List<String> getList(Collection<String> collection) {
        return wrapperCollection(collection).stream().filter(StringUtils::isNotBlank).distinct().collect(Collectors.toList());
    }
}
