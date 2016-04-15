package com.damon.spring.annotaion;

/**
 * 业务标识
 * 如果一种业务包含很多种不同的对接方
 * 每一种对接方可能需要不同的请求参数，不同的具体处理逻辑
 * 那我弄一个标识贴在请求类和处理类上
 * 然后当一个请求url（包含能够区分业务的参数）打过来时
 * Created by dongjun.wei on 16/3/21.
 */
public @interface Business {
}
