package com.example.ruslanio.experienceexchange.utils.rxbus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ruslanio on 06.11.2017.
 */

public class RxBus {
    private Map<String, List<Object>> mSubscribeObjectMap
            = new HashMap<>();

    public void subscribe(Object object) {
        Class clazz = object.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Subscriber.class)) {
                Subscriber subscriber = method.getAnnotation(Subscriber.class);

                List<Object> objects = mSubscribeObjectMap.get(subscriber.tag());
                if (objects == null) {
                    objects = new ArrayList<>();
                    mSubscribeObjectMap.put(subscriber.tag(), objects);
                }
                objects.add(object);
            }
        }
    }

    public void unsubscribe(Object object) {
        Class clazz = object.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Subscriber.class)) {
                Subscriber subscriber = method.getAnnotation(Subscriber.class);
                String tag=subscriber.tag();
                List<Object> list = mSubscribeObjectMap.get(tag);
                list.remove(object);
                if(list.isEmpty())
                    mSubscribeObjectMap.remove(tag);
            }
        }
    }

    public void publish(String tag) {
        send(tag, null);
    }

    public void publish(String tag, Object data) {
        if (data == null)
            return;
        send(tag, data);
    }

    private void send(String tag, Object data) {
        List<Object> objects = mSubscribeObjectMap.get(tag);

        if (objects == null || objects.isEmpty())
            return;

        for (Object object : objects) {

            Class objectClass = object.getClass();

            Method[] methods = objectClass.getMethods();

            for (Method method : methods) {
                if (method.isAnnotationPresent(Subscriber.class)) {

                    Subscriber subscriber = method.getAnnotation(Subscriber.class);

                    if(!subscriber.tag().equals(tag))
                        continue;

                    Class[] parameters = method.getParameterTypes();

                    if (data == null) {
                        if (parameters.length == 0) {
                            Observable.create(e -> e.onComplete())
                                    .observeOn(subscriber.thread() == Subscriber.IO_THREAD ?
                                            Schedulers.io() : AndroidSchedulers.mainThread())
                                    .doOnComplete(() -> method.invoke(object))
                                    .subscribe();
                        }
                    } else {
                        if (parameters.length == 1) {
                            Class paramType = parameters[0];
                            if (data.getClass() == paramType) {
                                Observable.just(data)
                                        .observeOn(subscriber.thread() == Subscriber.IO_THREAD ?
                                                Schedulers.io() : AndroidSchedulers.mainThread())
                                        .subscribe(dataObject -> method.invoke(object, dataObject));
                            }
                        }
                    }
                }
            }
        }
    }
}
