/*
 * Copyright 2007-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springbyexample.web.service;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;


/**
 * Flex internationalization service.
 * 
 * @author David Winterfeldt
 */
@Service("i18nService")
@RemotingDestination
public class InternationalizationService {

    /**
     * Gets current locale.
     */
    public String getLocale() {
        return LocaleContextHolder.getLocale().toString();
    }
    
}
