/*******************************************************************************
 * Copyright (c) 2012-2015 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/

package examples;

import org.eclipse.che.api.promises.client.Promise;
import org.eclipse.che.ide.api.app.AppContext;
import org.eclipse.che.ide.rest.AsyncRequestFactory;
import org.eclipse.che.ide.rest.StringUnmarshaller;
import org.eclipse.che.ide.ui.loaders.request.LoaderFactory;

import javax.inject.Inject;
import javax.inject.Named;


public class MyServiceClient {
    private final AsyncRequestFactory    asyncRequestFactory;
    private final String                 helloPath;
    private final LoaderFactory loaderFactory;

    @Inject
    public MyServiceClient(@Named("cheExtensionPath") String extPath,
                           AsyncRequestFactory asyncRequestFactory,
                           AppContext appContext, LoaderFactory loaderFactory) {
        this.asyncRequestFactory = asyncRequestFactory;
        this.loaderFactory = loaderFactory;
        helloPath = extPath + "/hello/" + appContext.getWorkspace().getId();
    }

    public Promise<String> getHello(String name) {
        return asyncRequestFactory.createGetRequest(helloPath + "/" + name)
                .loader(loaderFactory.newLoader("Loading your response..."))
                .send(new StringUnmarshaller());
    }

}