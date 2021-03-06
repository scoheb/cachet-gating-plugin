package com.redhat.jenkins.plugins.cachet;

import hudson.Extension;
import hudson.ExtensionList;

import javax.annotation.Nonnull;

import jenkins.model.GlobalConfiguration;
import net.sf.json.JSONObject;

import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.StaplerRequest;

/*
 * The MIT License
 *
 * Copyright (c) Red Hat, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

@Extension
public final class GlobalCachetConfiguration extends GlobalConfiguration {

    private String cachetUrl;

    public GlobalCachetConfiguration() {
        load();
    }

    public String getCachetUrl() {
        return cachetUrl;
    }

    @DataBoundSetter
    public void setCachetUrl(String cachetUrl) {
        this.cachetUrl = cachetUrl;
    }

    @Override
    public String getDisplayName() {
        return Messages.pluginName();
    }

    @Override
    public boolean configure(StaplerRequest req, JSONObject json) {
        req.bindJSON(this, json);
        save();
        return true;
    }

    public static @Nonnull GlobalCachetConfiguration get() {
        ExtensionList<GlobalCachetConfiguration> extensions = ExtensionList.lookup(GlobalCachetConfiguration.class);
        assert extensions.size() == 1: "One cachet configuration expected, got " + extensions.size();
        return extensions.get(0);
    }
}
