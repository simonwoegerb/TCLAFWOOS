package com.auster.thatcrapluxiandfischwantedonourserver.utils;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

public class ReflectionUtils {
    public static final Reflections reflections = new Reflections(new ConfigurationBuilder().addScanners(new SubTypesScanner()).setUrls(ClasspathHelper.forPackage("com.auster.thatcrapluxiandfischwantedonourserver")));

}
