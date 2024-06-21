package utils.analyzer;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;
import org.testng.annotations.Ignore;
import org.testng.internal.reflect.ReflectionHelper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryListener implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        IRetryAnalyzer retry = annotation.getRetryAnalyzer();
        ignoreTest(annotation, ReflectionHelper.findAnnotation(testClass, Ignore.class));
        if (retry == null) {
            annotation.setRetryAnalyzer(Retry.class);
        }
    }

    private static void ignoreTest(ITestAnnotation annotation, Ignore ignore) {
        if (ignore == null) {
            return;
        }
        annotation.setEnabled(false);
        updateDescription(annotation, ignore);
    }

    private static void updateDescription(ITestAnnotation annotation, Ignore ignore) {
        if (ignore.value().isEmpty()) {
            return;
        }
        String ignoredDescription;
        if (annotation.getDescription() == null || annotation.getDescription().isEmpty()) {
            ignoredDescription = ignore.value();
        } else {
            ignoredDescription = ignore.value() + ": " + annotation.getDescription();
        }
        annotation.setDescription(ignoredDescription);
    }
}
