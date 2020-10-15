package cn.iwyu.domain;/**
 * Created by Chester on 15/10/2020.
 */

/**
 * @ClassName RequestHolder
 * @Description
 * @Author XiaoMao
 * @Date 15/10/2020 下午10:40
 * @Version 1.0
 **/

public class RequestHolder {
    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();
    public static void add(Long id) {
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }

}
