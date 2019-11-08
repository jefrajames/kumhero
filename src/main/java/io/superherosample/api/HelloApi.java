/*
 * Copyright 2019 jefrajames.
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
package io.superherosample.api;

import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * This is a demo service to assess AMEX Node
 *
 * @author jefrajames
 */
@ApplicationScoped
@GraphQLClass
public class HelloApi {

    String welcomeMessage = "Hello GraphQL!";

    @GraphQLQuery(description = "Say Hello")
    public String hello() {
        return "Hello";
    }

    @GraphQLQuery(description = "Say hello with a name", name = "helloWithName")
    public String hello(@GraphQLArgument(name = "name") String name) {
        return "Hello " + name;
    }

    @GraphQLMutation(description = "Change the welcome message", name = "changeWelcomeMessage")
    public String changeWelcomeMessage(@GraphQLArgument(name="newWelcomeMessage") String newWelcomeMessage) {
        this.welcomeMessage = newWelcomeMessage;
        return this.welcomeMessage;
    }

}
