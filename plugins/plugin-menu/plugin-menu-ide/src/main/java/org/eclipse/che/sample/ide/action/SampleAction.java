/*
 * Copyright (c) 2012-2018 Red Hat, Inc.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.che.sample.ide.action;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.eclipse.che.ide.api.action.ActionEvent;
import org.eclipse.che.ide.api.action.BaseAction;
import org.eclipse.che.ide.api.notification.NotificationManager;
import org.eclipse.che.ide.api.notification.StatusNotification;

/** Sample action. */
@Singleton
public class SampleAction extends BaseAction {

  private final NotificationManager notificationManager;

  @Inject
  public SampleAction(NotificationManager notificationManager) {
    super("Say Hello", "Sample action");
    this.notificationManager = notificationManager;
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    notificationManager.notify(
        "Hello form Che!!!",
        StatusNotification.Status.SUCCESS,
        StatusNotification.DisplayMode.FLOAT_MODE);
  }
}
