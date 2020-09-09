/*
 * MIT License
 *
 * Copyright (c) 2018 The Cotton Project
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.github.cottonmc.cotton.logging;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModLogger {
    private Logger log;
    
    private String prefix;
    
    private final boolean isDev = true; // FIXME
    
    public ModLogger(Class<?> clazz) {
        this(clazz.getSimpleName());
    }
    
    public ModLogger(String name) {
        this(name, name);
    }
    
    public ModLogger(Class<?> clazz, String prefix) {
        this(clazz.getSimpleName(), prefix);
    }
    
    public ModLogger(String name, String prefix) {
        this.log = LogManager.getFormatterLogger(name);
        setPrefix(prefix);
    }
    
    private void setPrefix(String prefix) {
        if(prefix.length()>0){
            this.prefix="["+prefix+"]: ";
        } else {
            this.prefix="";
        }
    }
    
    public void retarget(Logger to) {
        log = to;
    }
    
    public void log(Level level, String msg, Object... data) {
        log.log(level, prefix + msg, data);
    }
    
    /**
     * @deprecated Use {@link #log(Level, String, Object...)} instead with the throwable as the last object
     *             after the rest of the data.
     */
    @Deprecated
    public void log(Level level, Throwable ex, String msg, Object... data) {
        log(level, msg, ArrayUtils.add(data, ex));
    }
    
    public void error(String msg, Object... data) {
        log(Level.ERROR, msg, data);
    }
    
    public void warn(String msg, Object... data) {
        log(Level.WARN, msg, data);
    }
    
    public void info(String msg, Object... data) {
        log(Level.INFO, msg, data);
    }
    
    /**
     * Only log this error in a dev enviroment.
     */
    public void devError(String msg, Object... data) {
        if (isDev) error(msg, data);
    }
    
    /**
     * Only log this warning in a dev environment.
     */
    public void devWarn(String msg, Object... data) {
        if (isDev) warn(msg, data);
    }
    
    /**
     * Only log this info in a dev environment.
     */
    public void devInfo(String msg, Object... data) {
        if (isDev) info(msg, data);
    }
    
    public void debug(String msg, Object... data) {
        log(Level.DEBUG, msg, data);
    }
    
    public void trace(String msg, Object... data) {
        log(Level.TRACE, msg, data);
    }
    
    public void all(String msg, Object... data) {
        log(Level.ALL, msg, data);
    }
}
