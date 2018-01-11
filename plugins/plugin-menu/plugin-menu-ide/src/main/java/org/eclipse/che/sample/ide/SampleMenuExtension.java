/*
 * Copyright (c) 2012-2018 All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.che.sample.ide;

import static org.eclipse.che.ide.api.action.IdeActions.GROUP_HELP;
import static org.eclipse.che.ide.api.action.IdeActions.GROUP_MAIN_MENU;
import static org.eclipse.che.ide.api.constraints.Anchor.AFTER;

import com.google.inject.Inject;
import org.eclipse.che.ide.api.action.ActionManager;
import org.eclipse.che.ide.api.action.DefaultActionGroup;
import org.eclipse.che.ide.api.constraints.Constraints;
import org.eclipse.che.ide.api.extension.Extension;
import org.eclipse.che.sample.ide.action.SampleAction;

/** */
@Extension(title = "Sample Menu")
public class SampleMenuExtension {

  private static final String SAMPLE_GROUP_MAIN_MENU = "Sample";

  @Inject
  private void prepareActions(SampleAction sampleAction, ActionManager actionManager) {

    DefaultActionGroup mainMenu = (DefaultActionGroup) actionManager.getAction(GROUP_MAIN_MENU);

    DefaultActionGroup sampleGroup =
        new DefaultActionGroup(SAMPLE_GROUP_MAIN_MENU, true, actionManager);
    actionManager.registerAction("sampleGroup", sampleGroup);
    mainMenu.add(sampleGroup, new Constraints(AFTER, GROUP_HELP));

    actionManager.registerAction("sayHello", sampleAction);
    sampleGroup.add(sampleAction, Constraints.FIRST);
  }
}
