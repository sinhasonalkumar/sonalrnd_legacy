/*
 * Copyright 2004-2009 the original author or authors.
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

package org.springmodules.validation.valang.functions;


/**
 * <p>Container class for a literal function. Stricly speaking this is not a function
 * as it does not manipulate data.
 *
 * @author Steven Devijver
 * @since Apr 23, 2005
 */
public class LiteralFunction implements Function {

    private Object literal;

    public LiteralFunction(Object literal) {
        this.literal = literal;
    }

    public Object getResult(Object target) {
        return literal;
    }

}
