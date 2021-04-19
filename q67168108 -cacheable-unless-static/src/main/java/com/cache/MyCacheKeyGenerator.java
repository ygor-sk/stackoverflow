package com.cache;

import sk.ygor.stackoverflow.q67168108.QueryBuilder;

public class MyCacheKeyGenerator {

    public static boolean isQueryNotCacheable(QueryBuilder queryBuilder) {
        System.out.println("MyCacheKeyGenerator.isQueryNotCacheable");
        return true;
    }
}
