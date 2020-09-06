package com.demo;

import com.demo.annotationParser.*;

import java.util.Set;

public class ApplicationRun {

    private Class<?> resource;

    private AnnotationParserContext context;

    public ApplicationRun(Class<?> resource) {
        this.resource = resource;
        this.context = new AnnotationParserContext();
    }

    public ApplicationRun run() {
        init();
        return this;
    }

    private void init() {
        AnnotationScannedMeta annotationScannedMeta = new AnnotationScannedFullAchieve();
        Set<Class<?>> scannedCandidates = annotationScannedMeta.scannedCandidates(resource.getPackage().getName());
        AnnotationParserMeta annotationParserMeta = new ProxyMethodLogAnnotationParser(scannedCandidates,MethodLog.class);
        ProxyFactoryMeta proxyFactoryMeta = new DefaultProxyFactory(annotationParserMeta);
        context.setAnnotationScannedMeta(annotationScannedMeta);
        context.setAnnotationParserMeta(annotationParserMeta);
        context.setProxyFactoryMeta(proxyFactoryMeta);
    }

    public Class<?> getResource() {
        return resource;
    }

    public void setResource(Class<?> resource) {
        this.resource = resource;
    }

    public AnnotationParserContext getContext() {
        return context;
    }

    public void setContext(AnnotationParserContext context) {
        this.context = context;
    }
}
