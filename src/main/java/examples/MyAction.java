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

import org.eclipse.che.api.promises.client.Operation;
import org.eclipse.che.api.promises.client.OperationException;
import org.eclipse.che.api.promises.client.PromiseError;
import org.eclipse.che.ide.api.action.Action;
import org.eclipse.che.ide.api.action.ActionEvent;

import com.google.inject.Inject;
import org.eclipse.che.ide.api.notification.NotificationManager;
import org.eclipse.che.ide.api.notification.StatusNotification;

public class MyAction extends Action {

    private final NotificationManager notificationManager;
    private final MyServiceClient serviceClient;

    @Inject
    public MyAction(MyResources resources, NotificationManager notificationManager, MyServiceClient serviceClient) {
        super("My Action", "My Action Description", null, resources.MyProjectTypeIcon());
        this.notificationManager = notificationManager;
        this.serviceClient = serviceClient;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // This calls the service in the workspace.
        // This method is in our MyServiceClient class
        // This is a Promise, so the .then() method is invoked after the response is made
        serviceClient.getHello("CheTheAllPowerful!")
                .then(new Operation<String>() {
                    @Override
                    public void apply(String arg) throws OperationException {
                        // This passes the response String from the method return to the notification manager.
                        notificationManager.notify(arg, StatusNotification.Status.SUCCESS, true);
                    }
                })
                .catchError(new Operation<PromiseError>() {
                    @Override
                    public void apply(PromiseError arg) throws OperationException {
                        notificationManager.notify("Fail", StatusNotification.Status.FAIL, true);
                    }
                });
    }
}