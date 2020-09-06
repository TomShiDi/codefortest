package com.demo.annotationParser;

import java.util.Set;

public interface AnnotationScannedMeta {

    Set<Class<?>> scannedCandidates(String pkgName);

}
