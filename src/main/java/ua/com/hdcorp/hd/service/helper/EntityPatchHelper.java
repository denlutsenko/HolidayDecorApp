package ua.com.hdcorp.hd.service.helper;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public class EntityPatchHelper{

    private static final String SETTER_PREFIX = "set";

    public <T extends Serializable> void patch(T entity, Map<String, String> patch) {
        Method[] entityMethods = entity.getClass().getMethods();
        patch.forEach((propertyName, propertyValue) -> {
            Optional<Method> setterMethod = Stream.of(entityMethods)
                    .filter(method -> constructSetterName(propertyName).equals(method.getName()))
                    .findFirst();
            setterMethod.ifPresent(setter -> invokeMethod(setter, entity, propertyValue));
        });
    }

    private String constructSetterName(String propertyName) {
        return SETTER_PREFIX.concat(StringUtils.capitalize(propertyName));
    }

    private void invokeMethod(Method method, Object object, Object... args) {
        try {
            method.invoke(object, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}