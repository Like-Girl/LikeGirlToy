package cn.likegirl;

import cn.likegirl.utils.ThreadPoolManager;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.apache.commons.collections4.MapUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LikeGirl
 * @version v1.0
 * @title: ThreadLocalTest
 * @description: TODO
 * @date 2018/12/24 17:05
 */
public class ThreadLocalTest {

    @Test
    public void test01() throws InterruptedException {
        Subject subject = new Subject(1L, "cc");
        ThreadContext.bind(subject);
        System.out.println(Thread.currentThread().getName());
        System.out.println(subject.toString());
        ThreadPoolManager.newInstance().addExecuteTask(()->{
            System.err.println(Thread.currentThread().getName());
            System.err.println(ThreadContext.getSubject());
        });
        Thread.sleep(2000L);
    }
}


class Subject {
    public Subject(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

abstract class ThreadContext {

    /**
     * Private internal log instance.
     */
    private static final Logger log = LoggerFactory.getLogger(ThreadContext.class);

    public static final String SECURITY_MANAGER_KEY = ThreadContext.class.getName() + "_SECURITY_MANAGER_KEY";
    public static final String SUBJECT_KEY = ThreadContext.class.getName() + "_SUBJECT_KEY";

    private static final ThreadLocal<Map<Object, Object>> resources = new InheritableThreadLocalMap<Map<Object, Object>>();

    /**
     * Default no-argument constructor.
     */
    protected ThreadContext() {
    }

    public static Map<Object, Object> getResources() {
        if (resources.get() == null) {
            return Collections.emptyMap();
        } else {
            return new HashMap<Object, Object>(resources.get());
        }
    }

    public static void setResources(Map<Object, Object> newResources) {
        if (MapUtils.isEmpty(newResources)) {
            return;
        }
//        if (CollectionUtils.isEmpty(newResources)) {
//            return;
//        }
        ensureResourcesInitialized();
        resources.get().clear();
        resources.get().putAll(newResources);
    }

    private static Object getValue(Object key) {
        Map<Object, Object> perThreadResources = resources.get();
        return perThreadResources != null ? perThreadResources.get(key) : null;
    }

    private static void ensureResourcesInitialized() {
        if (resources.get() == null) {
            resources.set(new HashMap<Object, Object>());
        }
    }

    public static Object get(Object key) {
        if (log.isTraceEnabled()) {
            String msg = "get() - in thread [" + Thread.currentThread().getName() + "]";
            log.trace(msg);
        }

        Object value = getValue(key);
        if ((value != null) && log.isTraceEnabled()) {
            String msg = "Retrieved value of type [" + value.getClass().getName() + "] for key [" +
                    key + "] " + "bound to thread [" + Thread.currentThread().getName() + "]";
            log.trace(msg);
        }
        return value;
    }

    public static void put(Object key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        if (value == null) {
            remove(key);
            return;
        }

        ensureResourcesInitialized();
        resources.get().put(key, value);

        if (log.isTraceEnabled()) {
            String msg = "Bound value of type [" + value.getClass().getName() + "] for key [" +
                    key + "] to thread " + "[" + Thread.currentThread().getName() + "]";
            log.trace(msg);
        }
    }

    public static Object remove(Object key) {
        Map<Object, Object> perThreadResources = resources.get();
        Object value = perThreadResources != null ? perThreadResources.remove(key) : null;

        if ((value != null) && log.isTraceEnabled()) {
            String msg = "Removed value of type [" + value.getClass().getName() + "] for key [" +
                    key + "]" + "from thread [" + Thread.currentThread().getName() + "]";
            log.trace(msg);
        }

        return value;
    }

    public static void remove() {
        resources.remove();
    }

    public static Subject getSubject() {
        return (Subject) get(SUBJECT_KEY);
    }


    public static void bind(Subject subject) {
        if (subject != null) {
            put(SUBJECT_KEY, subject);
        }
    }

    public static Subject unbindSubject() {
        return (Subject) remove(SUBJECT_KEY);
    }

    private static final class InheritableThreadLocalMap<T extends Map<Object, Object>> extends InheritableThreadLocal<Map<Object, Object>> {

        /**
         * This implementation was added to address a
         * <a href="http://jsecurity.markmail.org/search/?q=#query:+page:1+mid:xqi2yxurwmrpqrvj+state:results">
         * user-reported issue</a>.
         *
         * @param parentValue the parent value, a HashMap as defined in the {@link #initialValue()} method.
         * @return the HashMap to be used by any parent-spawned child threads (a clone of the parent HashMap).
         */
        @SuppressWarnings({"unchecked"})
        protected Map<Object, Object> childValue(Map<Object, Object> parentValue) {
            if (parentValue != null) {
                return (Map<Object, Object>) ((HashMap<Object, Object>) parentValue).clone();
            } else {
                return null;
            }
        }
    }
}

